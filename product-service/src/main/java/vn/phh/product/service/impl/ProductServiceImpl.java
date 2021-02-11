package vn.phh.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.phh.commons.calendar.DateTimeUtils;
import vn.phh.commons.exception.ResourceNotFoundException;
import vn.phh.product.converter.ProductConverter;
import vn.phh.product.dto.ProductDTO;
import vn.phh.product.dto.RequestFilterProduct;
import vn.phh.product.dto.TrackingAction;
import vn.phh.product.dto.TrackingDTO;
import vn.phh.product.model.Product;
import vn.phh.product.repository.product.ProductHistoryRepository;
import vn.phh.product.repository.product.ProductRepository;
import vn.phh.product.service.ProductService;
import vn.phh.product.service.TrackingService;

import java.util.List;
import java.util.Optional;

import static vn.phh.product.utils.Constants.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ProductHistoryRepository productHistoryRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TrackingService trackingService;

    @Autowired
    private ClientServiceImpl clientService;


    @Override
    public ProductDTO add(ProductDTO dto) {
        dto.setCreatedDate(DateTimeUtils.now());
        Product product = productRepository.save(productConverter.convertToEntity(dto));
        addProductHistory(product);
        return productConverter.convertToDTO(product);
    }

    @Override
    public ProductDTO update(String id, ProductDTO dto) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent())
            throw new ResourceNotFoundException("Product is not exist");
        Product product = productRepository.save(productConverter.convertToEntity(dto));
        if(productOptional.get().getPrice() != dto.getPrice()) {
            addProductHistory(product);
        }
        return productConverter.convertToDTO(product);
    }

    @Override
    public ProductDTO findById(String id) {
        TrackingDTO tracking = new TrackingDTO();
        tracking.setCustomerId(clientService.get().getId());
        tracking.setActionKey(TrackingAction.VIEWER.getKey());
        tracking.setActionName(TrackingAction.VIEWER.getValue());
        tracking.setInfo(id);
        trackingService.add(tracking);
        Optional<Product> Product = productRepository.findById(id);
        if (!Product.isPresent())
            throw new ResourceNotFoundException("Product is not exist");
        return productConverter.convertToDTO(Product.get());
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOS = productConverter.convertEntitiesToDTOs(productRepository.findAll());
        return productDTOS;
    }

    @Override
    public boolean deleteById(String id) {
        Optional<Product> Product = productRepository.findById(id);
        if (!Product.isPresent())
            throw new ResourceNotFoundException("Product is not exist");
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<ProductDTO> filterProduct(RequestFilterProduct requestFilterProduct, int page, int size, String direction, String attribute) {
        TrackingDTO tracking = new TrackingDTO();
        tracking.setCustomerId(clientService.get().getId());
        tracking.setActionKey(TrackingAction.FILTER.getKey());
        tracking.setActionName(TrackingAction.FILTER.getValue());
        tracking.setInfo(requestFilterProduct.toString());
        trackingService.add(tracking);

        Sort sortable = Sort.by(Sort.Direction.fromString(direction),attribute);
        Pageable pageable = PageRequest.of(page, size, sortable);
        Query query = new Query();
        if(!StringUtils.isEmpty(requestFilterProduct.getName())) {
            query.addCriteria(Criteria.where(FILTER_PRODUCT_NAME).is(requestFilterProduct.getName()));
        }
        if(!StringUtils.isEmpty(requestFilterProduct.getBrand())) {
            query.addCriteria(Criteria.where(FILTER_PRODUCT_BRAND).is(requestFilterProduct.getBrand()));
        }
        if(requestFilterProduct.getMaxPrice() != 0 && requestFilterProduct.getMinPrice() != 0) {
            query.addCriteria(Criteria.where(FILTER_PRODUCT_PRICE).lte(requestFilterProduct.getMaxPrice()).gte(requestFilterProduct.getMinPrice()));
        }
        if(requestFilterProduct.getMaxPrice() != 0 && requestFilterProduct.getMinPrice() == 0) {
            query.addCriteria(Criteria.where(FILTER_PRODUCT_PRICE).lte(requestFilterProduct.getMaxPrice()));
        }
        if(requestFilterProduct.getMaxPrice() == 0 && requestFilterProduct.getMinPrice() != 0) {
            query.addCriteria(Criteria.where(FILTER_PRODUCT_PRICE).gte(requestFilterProduct.getMinPrice()));
        }
        query.with(pageable);
        List<Product> list = mongoTemplate.find(query, Product.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Product.class)
        ).map(product -> productConverter.convertToDTO(product));
    }

    @Override
    public Page<ProductDTO> searchProduct(String content, int page, int size) {
        TrackingDTO tracking = new TrackingDTO();
        tracking.setCustomerId(clientService.get().getId());
        tracking.setActionKey(TrackingAction.SEARCH.getKey());
        tracking.setActionName(TrackingAction.SEARCH.getValue());
        tracking.setInfo(content);
        trackingService.add(tracking);
        Pageable pageable = PageRequest.of(page, size);
        TextCriteria criteria = TextCriteria.forDefaultLanguage()
                .matchingAny(content);
        Query query = TextQuery.queryText(criteria).sortByScore();
        List<Product> list = mongoTemplate.find(query, Product.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Product.class)
        ).map(product -> productConverter.convertToDTO(product));
    }

    @Override
    @Async
    public void addProductHistory(Product product) {
        productHistoryRepository.save(productConverter.convertToProductHistory(product));
    }

}
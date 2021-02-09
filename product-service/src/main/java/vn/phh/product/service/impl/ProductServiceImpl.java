package vn.phh.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.phh.commons.calendar.DateTimeUtils;
import vn.phh.commons.exception.ResourceNotFoundException;
import vn.phh.product.converter.ProductConverter;
import vn.phh.product.dto.ProductDTO;
import vn.phh.product.dto.RequestFilterProduct;
import vn.phh.product.model.Product;
import vn.phh.product.repository.ProductRepository;
import vn.phh.product.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    public static final String FILTER_PRODUCT_NAME = "name";
    public static final String FILTER_PRODUCT_BRAND = "brand";
    public static final String FILTER_PRODUCT_PRICE = "price";
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public ProductDTO add(ProductDTO dto) {
        dto.setCreatedDate(DateTimeUtils.now());
        return productConverter.convertToDTO(productRepository.save(productConverter.convertToEntity(dto)));
    }

    @Override
    public ProductDTO update(String id, ProductDTO dto) {
        Optional<Product> Product = productRepository.findById(id);
        if (!Product.isPresent())
            throw new ResourceNotFoundException("Product is not exist");
        return productConverter.convertToDTO(productRepository.save(productConverter.convertToEntity(dto)));
    }

    @Override
    public ProductDTO findById(String id) {
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

}
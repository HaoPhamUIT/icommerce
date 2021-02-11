package vn.phh.product.service.impl;

import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.phh.commons.exception.BusinessException;
import vn.phh.commons.exception.ResourceNotFoundException;
import vn.phh.commons.kafka.producer.Producer;
import vn.phh.commons.model.enums.ErrorMessage;
import vn.phh.kafka.message.OrderAvro;
import vn.phh.kafka.message.ProductAvro;
import vn.phh.product.converter.CartConverter;
import vn.phh.product.dto.CartDTO;
import vn.phh.product.dto.enums.CartStatus;
import vn.phh.product.model.Cart;
import vn.phh.product.model.Product;
import vn.phh.product.model.Profile;
import vn.phh.product.repository.product.CartRepository;
import vn.phh.product.repository.product.ProductRepository;
import vn.phh.product.repository.profile.ProfileRepository;
import vn.phh.product.service.CartService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static vn.phh.commons.constants.CommonConstants.KAFKA_TOPIC_ORDER;
import static vn.phh.product.utils.Constants.CART_IS_NOT_EXIST;
import static vn.phh.product.utils.Constants.COULD_NOT_FOUND_PRODUCT;


@Service
public class CartServiceImpl implements CartService {



    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartConverter cartConverter;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    Producer<OrderAvro> orderAvroProducer;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ProductRepository productRepository;


    @Override
    public CartDTO add(CartDTO dto) {
        Cart cart = cartConverter.convertToEntity(dto);
        Optional<Cart> cartOptional = cartRepository.findByProductIdAndCustomerId(
                cart.getProductId(), cart.getCustomerId());
        if(cartOptional.isPresent()){
            cartOptional.get().setProductNumber(cartOptional.get().getProductNumber() + cart.getProductNumber());
            return cartConverter.convertToDTO(cartRepository.save(cartOptional.get()));
        }else return cartConverter.convertToDTO(cartRepository.save(cart));
    }

    @Override
    public CartDTO update(String id, CartDTO dto) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (!cartOptional.isPresent())
            throw new ResourceNotFoundException(CART_IS_NOT_EXIST);
        return cartConverter.convertToDTO(cartRepository.save(cartConverter.convertToEntity(dto)));
    }

    @Override
    public CartDTO findById(String id) {
        Optional<Cart> Cart = cartRepository.findById(id);
        if (!Cart.isPresent())
            throw new ResourceNotFoundException(CART_IS_NOT_EXIST);
        return cartConverter.convertToDTO(Cart.get());
    }

    @Override
    public List<CartDTO> findAll() {
        return cartConverter.convertEntitiesToDTOs(
                cartRepository.findAllByCustomerId(clientService.get().getId()));
    }

    @Override
    public boolean deleteById(String id) {
        Optional<Cart> Cart = cartRepository.findById(id);
        if (!Cart.isPresent())
            throw new ResourceNotFoundException(CART_IS_NOT_EXIST);
        cartRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean orderProducts(List<String> cartId) {
        Optional<Profile> profile = profileRepository.findById(clientService.get().getId());
        if(!profile.isPresent() ||
                StringUtils.isEmpty(profile.get().getPhone()) ||
                StringUtils.isEmpty(profile.get().getAddress()) ||
                StringUtils.isEmpty(profile.get().getFullName())){
            throw new BusinessException(ErrorMessage.ERROR_MISSING_DATA_PROFILE);
        }
        OrderAvro order = new OrderAvro();
        order.setCustomerId(profile.get().getId());
        order.setFullName(profile.get().getFullName());
        order.setPhone(profile.get().getPhone());
        order.setAddress(profile.get().getAddress());
        List<ProductAvro> productAvros = new ArrayList<>();
        Iterable<Cart> carts = cartRepository.findAllByIdInAndStatus(cartId, CartStatus.IN_CART.getKey());
        double amount = 0;
        if(Iterables.size(carts) != 0) {
            Iterator iterator = carts.iterator();
            while (iterator.hasNext()) {
                Cart cart = (Cart) iterator.next();
                Optional<Product> product = productRepository.findById(cart.getProductId());
                if (product.isPresent()) {
                    ProductAvro productAvro = new ProductAvro();
                    productAvro.setBrand(product.get().getBrand());
                    productAvro.setPrice(product.get().getPrice());
                    productAvro.setColour(product.get().getColour());
                    productAvro.setId(product.get().getId());
                    productAvro.setName(product.get().getName());
                    productAvro.setProductNumber(cart.getProductNumber());
                    amount = amount + product.get().getPrice()*cart.getProductNumber();
                    productAvros.add(productAvro);
                    //update status
                    cart.setStatus(CartStatus.ORDERED.getKey());
                    cartRepository.saveAll(carts);
                    iterator.remove();
                }
            }
        }else throw new ResourceNotFoundException(COULD_NOT_FOUND_PRODUCT);
        order.setAmount(amount);
        order.setProducts(productAvros);
        orderAvroProducer.send(order, KAFKA_TOPIC_ORDER);
        return true;
    }

}
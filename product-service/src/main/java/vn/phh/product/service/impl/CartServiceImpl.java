package vn.phh.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.phh.commons.exception.BusinessException;
import vn.phh.commons.exception.ResourceNotFoundException;
import vn.phh.commons.kafka.producer.Producer;
import vn.phh.commons.model.enums.ErrorMessage;
import vn.phh.kafka.message.OrderAvro;
import vn.phh.product.converter.CartConverter;
import vn.phh.product.dto.CartDTO;
import vn.phh.product.model.Cart;
import vn.phh.product.model.Profile;
import vn.phh.product.repository.CartRepository;
import vn.phh.product.repository.profile.ProfileRepository;
import vn.phh.product.service.CartService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static vn.phh.commons.constants.CommonConstants.KAFKA_TOPIC_ORDER;
import static vn.phh.product.utils.Constants.CART_IS_NOT_EXIST;


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
        orderAvroProducer.send(order, KAFKA_TOPIC_ORDER);
        return false;
    }

}
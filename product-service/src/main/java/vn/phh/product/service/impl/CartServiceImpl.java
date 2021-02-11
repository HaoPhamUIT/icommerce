package vn.phh.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import vn.phh.commons.exception.ResourceNotFoundException;
import vn.phh.product.converter.CartConverter;
import vn.phh.product.dto.CartDTO;
import vn.phh.product.model.Cart;
import vn.phh.product.repository.CartRepository;
import vn.phh.product.service.CartService;

import java.util.List;
import java.util.Optional;

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

}
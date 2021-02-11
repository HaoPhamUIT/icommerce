package vn.phh.product.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.phh.product.dto.CartDTO;
import vn.phh.product.model.Cart;
import vn.phh.product.service.impl.ClientServiceImpl;

import java.time.LocalDateTime;


/**
 * @author haoph
 */
@Component("cartConverter")
public class CartConverter extends SuperConverter<CartDTO, Cart> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientServiceImpl clientService;

    @Override
    public CartDTO convertToDTO(Cart entity) {
        return modelMapper.map(entity, CartDTO.class);
    }

    @Override
    public Cart convertToEntity(CartDTO dto) {
        Cart cart = modelMapper.map(dto, Cart.class);
        cart.setId(null);
        cart.setCreatedDate(LocalDateTime.now());
        cart.setCreatedBy(clientService.get().getId());
        cart.setCustomerId(clientService.get().getId());
        return cart;
    }


}

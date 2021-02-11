package vn.phh.product.service;


import vn.phh.product.dto.CartDTO;

import java.util.List;

public interface CartService {

   CartDTO add(CartDTO dto);

   CartDTO update(String id, CartDTO dto);

   CartDTO findById(String id);

    List<CartDTO> findAll();

    boolean deleteById(String id);
    
}

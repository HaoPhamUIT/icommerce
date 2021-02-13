package vn.phh.order.service;


import vn.phh.order.dto.OrderInfoDTO;

import java.util.List;

public interface OrderInfoService {

   OrderInfoDTO add(OrderInfoDTO dto);

   OrderInfoDTO updateStatusOrder(int status);

   OrderInfoDTO findById(String id);

    List<OrderInfoDTO> findAll();

    boolean deleteById(String id);

    
}

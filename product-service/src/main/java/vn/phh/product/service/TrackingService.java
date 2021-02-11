package vn.phh.product.service;


import vn.phh.product.dto.TrackingDTO;

import java.util.List;

public interface TrackingService {

   TrackingDTO add(TrackingDTO dto);

   TrackingDTO update(String id, TrackingDTO dto);

   TrackingDTO findById(String id);

    List<TrackingDTO> findAll();

    boolean deleteById(String id);
    
}

package vn.phh.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.phh.commons.exception.ResourceNotFoundException;
import vn.phh.product.converter.TrackingConverter;
import vn.phh.product.dto.TrackingDTO;
import vn.phh.product.model.Tracking;
import vn.phh.product.repository.TrackingRepository;
import vn.phh.product.service.TrackingService;
import vn.phh.product.service.TrackingService;

import java.util.List;
import java.util.Optional;

import static vn.phh.product.utils.Constants.CART_IS_NOT_EXIST;


@Service
public class TrackingServiceImpl implements TrackingService {


    @Autowired
    private TrackingRepository trackingRepository;

    @Autowired
    private TrackingConverter trackingConverter;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    @Async
    public TrackingDTO add(TrackingDTO dto) {
        return trackingConverter.convertToDTO(trackingRepository.save(trackingConverter.convertToEntity(dto)));
    }

    @Override
    public TrackingDTO update(String id, TrackingDTO dto) {
        Optional<Tracking> trackingOptional = trackingRepository.findById(id);
        if (!trackingOptional.isPresent())
            throw new ResourceNotFoundException(CART_IS_NOT_EXIST);
        return trackingConverter.convertToDTO(trackingRepository.save(trackingConverter.convertToEntity(dto)));
    }

    @Override
    public TrackingDTO findById(String id) {
        Optional<Tracking> tracking = trackingRepository.findById(id);
        if (!tracking.isPresent())
            throw new ResourceNotFoundException(CART_IS_NOT_EXIST);
        return trackingConverter.convertToDTO(tracking.get());
    }

    @Override
    public List<TrackingDTO> findAll() {
        return trackingConverter.convertEntitiesToDTOs(
                trackingRepository.findAllByCustomerId(clientService.get().getId()));
    }

    @Override
    public boolean deleteById(String id) {
        Optional<Tracking> tracking = trackingRepository.findById(id);
        if (!tracking.isPresent())
            throw new ResourceNotFoundException(CART_IS_NOT_EXIST);
        trackingRepository.deleteById(id);
        return true;
    }

}
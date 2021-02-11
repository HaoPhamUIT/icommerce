package vn.phh.product.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.phh.product.dto.TrackingDTO;
import vn.phh.product.model.Tracking;
import vn.phh.product.service.impl.ClientServiceImpl;

import java.time.LocalDateTime;


/**
 * @author haoph
 */
@Component("trackingConverter")
public class TrackingConverter extends SuperConverter<TrackingDTO, Tracking> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientServiceImpl clientService;

    @Override
    public TrackingDTO convertToDTO(Tracking entity) {
        return modelMapper.map(entity, TrackingDTO.class);
    }

    @Override
    public Tracking convertToEntity(TrackingDTO dto) {
        Tracking tracking = modelMapper.map(dto, Tracking.class);
        tracking.setId(null);
        tracking.setCreatedDate(LocalDateTime.now());
        tracking.setCreatedBy(clientService.get().getId());
        return tracking;
    }


}

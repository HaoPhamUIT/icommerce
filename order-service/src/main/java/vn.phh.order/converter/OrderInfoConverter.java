package vn.phh.order.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.phh.kafka.message.OrderAvro;
import vn.phh.order.dto.OrderInfoDTO;
import vn.phh.order.model.OrderInfo;
import vn.phh.order.service.impl.ClientServiceImpl;

import java.time.LocalDateTime;


/**
 * @author haoph
 */
@Component("orderInfoConverter")
public class OrderInfoConverter extends SuperConverter<OrderInfoDTO, OrderInfo> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientServiceImpl clientService;

    @Override
    public OrderInfoDTO convertToDTO(OrderInfo entity) {
        return modelMapper.map(entity, OrderInfoDTO.class);
    }

    @Override
    public OrderInfo convertToEntity(OrderInfoDTO dto) {
        OrderInfo orderInfo = modelMapper.map(dto, OrderInfo.class);
        orderInfo.setId(null);
        orderInfo.setCreatedDate(LocalDateTime.now());
        orderInfo.setCreatedBy(orderInfo.getCustomerId());
        return orderInfo;
    }

    public OrderInfoDTO avroCovertToDTO(OrderAvro avro){
        return modelMapper.map(avro, OrderInfoDTO.class);
    }

}

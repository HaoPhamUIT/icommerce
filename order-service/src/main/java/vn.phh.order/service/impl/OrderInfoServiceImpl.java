package vn.phh.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import vn.phh.commons.exception.ResourceNotFoundException;
import vn.phh.commons.kafka.producer.Producer;
import vn.phh.kafka.message.OrderAvro;
import vn.phh.order.converter.OrderInfoConverter;
import vn.phh.order.dto.OrderInfoDTO;
import vn.phh.order.model.OrderInfo;
import vn.phh.order.repository.OrderInfoRepository;
import vn.phh.order.service.OrderInfoService;

import java.util.List;
import java.util.Optional;

import static vn.phh.order.utils.Constants.CART_IS_NOT_EXIST;


@Service
public class OrderInfoServiceImpl implements OrderInfoService {



    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderInfoConverter orderInfoConverter;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    Producer<OrderAvro> orderAvroProducer;



    @Override
    public OrderInfoDTO add(OrderInfoDTO dto) {
        OrderInfo orderInfo = orderInfoConverter.convertToEntity(dto);
        return orderInfoConverter.convertToDTO(orderInfoRepository.save(orderInfo));
    }

    public OrderInfoDTO updateStatusOrder( int status) {
        Optional<OrderInfo> orderInfoOptional = orderInfoRepository.findById(clientService.get().getId());
        if (!orderInfoOptional.isPresent())
            throw new ResourceNotFoundException(CART_IS_NOT_EXIST);
        orderInfoOptional.get().setOrderStatus(status);
        return orderInfoConverter.convertToDTO(orderInfoRepository.save(orderInfoOptional.get()));
    }

    @Override
    public OrderInfoDTO findById(String id) {
        Optional<OrderInfo> OrderInfo = orderInfoRepository.findById(id);
        if (!OrderInfo.isPresent())
            throw new ResourceNotFoundException(CART_IS_NOT_EXIST);
        return orderInfoConverter.convertToDTO(OrderInfo.get());
    }

    @Override
    public List<OrderInfoDTO> findAll() {
        return orderInfoConverter.convertEntitiesToDTOs(
                orderInfoRepository.findAllByCustomerId(clientService.get().getId()));
    }

    @Override
    public boolean deleteById(String id) {
        Optional<OrderInfo> OrderInfo = orderInfoRepository.findById(id);
        if (!OrderInfo.isPresent())
            throw new ResourceNotFoundException(CART_IS_NOT_EXIST);
        orderInfoRepository.deleteById(id);
        return true;
    }


}
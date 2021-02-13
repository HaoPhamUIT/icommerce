package vn.phh.order.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import vn.phh.commons.constants.CommonConstants;
import vn.phh.commons.kafka.consummer.ConsumerListener;
import vn.phh.kafka.message.OrderAvro;
import vn.phh.order.converter.OrderInfoConverter;
import vn.phh.order.dto.OrderInfoDTO;
import vn.phh.order.service.OrderInfoService;

@Service
public class OrderConsumer extends ConsumerListener<OrderAvro> {


    protected OrderConsumer() {
        super(OrderAvro.class);
    }

    @Autowired
    OrderInfoConverter orderInfoConverter;

    @Autowired
    OrderInfoService orderInfoService;

    @Override
    @KafkaListener(topics = {CommonConstants.KAFKA_TOPIC_ORDER}, groupId = "order", clientIdPrefix = "order")
    protected void listen(ConsumerRecord<String, OrderAvro> message) {
        OrderAvro orderAvro = message.value();
        if (message.value() == null) return;
        System.out.println("value 1: " +orderAvro.toString());
        OrderInfoDTO orderInfoDTO = orderInfoConverter.avroCovertToDTO(orderAvro);
        System.out.println("value DTo: " +orderInfoDTO.toString());
        orderInfoService.add(orderInfoDTO);
    }


}

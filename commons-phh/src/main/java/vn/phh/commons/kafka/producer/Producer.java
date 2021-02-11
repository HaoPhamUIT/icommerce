package vn.phh.commons.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import vn.phh.commons.exception.ThirdPartyException;

@Service
public class Producer<T> {

    private KafkaTemplate<String, T> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public SendResult<String, T> send(T message, String topic) {
        SendResult<String, T> result = null;
        try {
            result = this.kafkaTemplate.send(topic, null, message).get();
            System.out.println("Send: " +message);
        } catch (Exception e) {
            throw new ThirdPartyException(e);
        }
        return result;
    }

}

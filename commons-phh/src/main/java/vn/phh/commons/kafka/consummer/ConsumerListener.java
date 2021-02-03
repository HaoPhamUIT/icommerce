package vn.phh.commons.kafka.consummer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import vn.phh.commons.kafka.AvroDeserializer;

public abstract class ConsumerListener<T> {

    protected Class<T> t;

    protected ConsumerListener(Class<T> messageType) {
        this.t = messageType;
    }

    public ConsumerFactory<String, Object> consumerFactory(KafkaProperties properties) {
        return new DefaultKafkaConsumerFactory<String, Object>(properties.buildConsumerProperties(),
                new StringDeserializer(), new AvroDeserializer(t));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(KafkaProperties properties) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new
                ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(properties));
        return factory;
    }

    protected abstract void listen(ConsumerRecord<String, T> message);
}

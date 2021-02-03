package vn.phh.commons.configuration;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import vn.phh.commons.kafka.AvroSerializer;

@Configuration
public class KafkaProducerConfiguration {

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(KafkaProperties properties) {
        return new KafkaTemplate<>(producerFactory(properties));
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory(KafkaProperties properties) {
        return new DefaultKafkaProducerFactory<String, Object>(properties.buildProducerProperties(),
                new StringSerializer(), new AvroSerializer());
    }

}

package com.example.config;

import com.example.entity.Greeting;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

//@EnableKafka
//@Configuration
public class KafkaConsumerConfig {


//    @Value(value = "${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
//    @Value(value = "${spring.kafka.consumer.group-id}")
//    private String groupId;
//
//
//
//    @Bean
//    public  ConsumerFactory<String, Greeting> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, this.groupId);
//        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100);
//        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 20000);
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 60000);
//        props.put(
//                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                bootstrapAddress);
//        props.put(
//                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//        props.put(
//                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(
//                props,
//                new StringDeserializer(),
//                new JsonDeserializer<>(Greeting.class));
//    }


}



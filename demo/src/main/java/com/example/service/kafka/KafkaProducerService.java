package com.example.service.kafka;

import com.example.entity.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService {

   @Autowired
   KafkaTemplate<String, Greeting> kafkaTemplate;



    public void sendMessage(String topic, String message) {
        Greeting greeting = new Greeting();
        greeting.setMsg("fadgsdgsd");
//        kafkaTemplate.send(topic,0,"my-group", greeting);
    }

////groupId = "my-group"
//    @KafkaListener(
//            topicPartitions = @TopicPartition(topic = "demo1",
//                    partitionOffsets = {
//                            @PartitionOffset(partition = "0", initialOffset = "0")
//                            }),
//            containerFactory = "partitionsKafkaListenerContainerFactory",
//            groupId = "my-group") // Đặt groupId ở đây)
//    public void listenToPartition(
//            @Payload Greeting message,
//            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        System.out.println( "Received Message: " + message.getMsg()+ "from partition: " + partition);
//
//
//    }
}

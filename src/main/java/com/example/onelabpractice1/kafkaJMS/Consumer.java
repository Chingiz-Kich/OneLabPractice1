package com.example.onelabpractice1.kafkaJMS;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void consumeFromTopic(String message) {
        System.out.println("Consumed message: " + message);
    }
}

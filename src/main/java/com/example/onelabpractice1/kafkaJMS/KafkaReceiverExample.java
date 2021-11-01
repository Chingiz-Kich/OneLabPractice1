package com.example.onelabpractice1.kafkaJMS;

import com.example.onelabpractice1.models.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

// @Component
public class KafkaReceiverExample {

    /*
    private final Logger LOG = Logger.getLogger(KafkaSenderExample.class.getName());

    @KafkaListener(
            topics = "topic-1",
            groupId="user-1",
            containerFactory="userKafkaListenerContainerFactory")
    void listener(User user) {
        LOG.info("CustomUserListener [{}]");
    }

     */
}

package com.example.onelabpractice1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class OneLabPractice1 {
    public static void main(String[] args) {
        Application.start();
        KafkaTest kafkaTest = new KafkaTest();
        kafkaTest.execute();
    }
}

package com.example.onelabpractice1;

import com.example.onelabpractice1.kafkaJMS.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class KafkaTest {

    private Producer producer;

    @Autowired
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public void execute() {
        this.producer.sendMessage("hello Kafka");
    }
}

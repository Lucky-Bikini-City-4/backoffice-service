package com.dayaeyak.backofficservice.backoffice.kafka.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class ConsumerKafkaConfig {
    private final String SERVER = "localhost:9092";


}

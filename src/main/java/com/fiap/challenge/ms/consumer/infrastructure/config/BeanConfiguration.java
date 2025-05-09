package com.fiap.challenge.ms.consumer.infrastructure.config;

import com.fiap.challenge.ms.consumer.domain.DomainConsumerService;
import com.fiap.challenge.ms.consumer.domain.ports.inbound.ConsumerService;
import com.fiap.challenge.ms.consumer.domain.ports.outbound.ConsumerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ConsumerService consumerService(ConsumerRepository consumerRepository) {
        return new DomainConsumerService(consumerRepository);
    }
}

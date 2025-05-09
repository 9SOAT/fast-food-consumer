package com.fiap.challenge.ms.consumer.infrastructure.mapper;

import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;
import com.fiap.challenge.ms.consumer.infrastructure.entity.ConsumerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public ConsumerEntity toConsumerEntity(Consumer consumer) {
        if (consumer == null) {
            return null;
        }

        return ConsumerEntity.builder()
                .name(consumer.getName())
                .email(consumer.getEmail())
                .cpf(consumer.getCpf())
                .build();
    }

    public Consumer toConsumer(ConsumerEntity consumerEntity) {
        if (consumerEntity == null) {
            return null;
        }

        return Consumer.builder()
                .cpf(consumerEntity.getCpf())
                .name(consumerEntity.getName())
                .email(consumerEntity.getEmail())
                .build();
    }
}

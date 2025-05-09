package com.fiap.challenge.ms.consumer.infrastructure.mapper;

import com.fiap.challenge.ms.consumer.application.request.ConsumerMutation;
import com.fiap.challenge.ms.consumer.application.response.ConsumerView;
import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class ViewMapper {

    public ConsumerView toConsumerView(Consumer consumer) {
        if (consumer == null) {
            return null;
        }

        return ConsumerView.builder()
                .name(consumer.getName())
                .email(consumer.getEmail())
                .cpf(consumer.getCpf())
                .build();
    }

    public Consumer toConsumer(ConsumerMutation consumerMutation) {
        if (consumerMutation == null) {
            return null;
        }

        return Consumer.builder()
                .cpf(consumerMutation.cpf())
                .name(consumerMutation.name())
                .email(consumerMutation.email())
                .build();
    }
}
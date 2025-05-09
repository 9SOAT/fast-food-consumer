package com.fiap.challenge.ms.consumer.infrastructure.mapper;

import com.fiap.challenge.ms.consumer.application.request.ConsumerMutation;
import com.fiap.challenge.ms.consumer.application.response.ConsumerView;
import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ViewMapperTest {

    private final ViewMapper viewMapper = new ViewMapper();

    @Test
    void shouldMapConsumerToConsumerView() {
        Consumer consumer = Consumer.builder()
                .id(1L)
                .name("João")
                .email("joao@example.com")
                .cpf("12345678900")
                .build();

        ConsumerView view = viewMapper.toConsumerView(consumer);

        assertThat(view).isNotNull();
        assertThat(view.id()).isEqualTo(1L);
        assertThat(view.name()).isEqualTo("João");
        assertThat(view.email()).isEqualTo("joao@example.com");
        assertThat(view.cpf()).isEqualTo("12345678900");
    }

    @Test
    void shouldReturnNullWhenConsumerIsNull() {
        ConsumerView view = viewMapper.toConsumerView(null);
        assertThat(view).isNull();
    }

    @Test
    void shouldMapConsumerMutationToConsumer() {
        ConsumerMutation mutation = new ConsumerMutation("João", "joao@example.com", "12345678900");

        Consumer consumer = viewMapper.toConsumer(mutation);

        assertThat(consumer).isNotNull();
        assertThat(consumer.getName()).isEqualTo("João");
        assertThat(consumer.getEmail()).isEqualTo("joao@example.com");
        assertThat(consumer.getCpf()).isEqualTo("12345678900");
    }

    @Test
    void shouldReturnNullWhenConsumerMutationIsNull() {
        Consumer consumer = viewMapper.toConsumer(null);
        assertThat(consumer).isNull();
    }
}

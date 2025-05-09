package com.fiap.challenge.ms.consumer.infrastructure.mapper;

import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;
import com.fiap.challenge.ms.consumer.infrastructure.entity.ConsumerEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EntityMapperTest {

    private final EntityMapper entityMapper = new EntityMapper();

    @Test
    void shouldMapConsumerToConsumerEntity() {
        Consumer consumer = Consumer.builder()
                .id(1L)
                .name("Maria")
                .email("maria@example.com")
                .cpf("98765432100")
                .build();

        ConsumerEntity entity = entityMapper.toConsumerEntity(consumer);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getName()).isEqualTo("Maria");
        assertThat(entity.getEmail()).isEqualTo("maria@example.com");
        assertThat(entity.getCpf()).isEqualTo("98765432100");
    }

    @Test
    void shouldReturnNullWhenConsumerIsNull() {
        ConsumerEntity entity = entityMapper.toConsumerEntity(null);
        assertThat(entity).isNull();
    }

    @Test
    void shouldMapConsumerEntityToConsumer() {
        ConsumerEntity entity = ConsumerEntity.builder()
                .id(1L)
                .name("Carlos")
                .email("carlos@example.com")
                .cpf("11223344556")
                .build();

        Consumer consumer = entityMapper.toConsumer(entity);

        assertThat(consumer).isNotNull();
        assertThat(consumer.getId()).isEqualTo(1L);
        assertThat(consumer.getName()).isEqualTo("Carlos");
        assertThat(consumer.getEmail()).isEqualTo("carlos@example.com");
        assertThat(consumer.getCpf()).isEqualTo("11223344556");
    }

    @Test
    void shouldReturnNullWhenConsumerEntityIsNull() {
        Consumer consumer = entityMapper.toConsumer(null);
        assertThat(consumer).isNull();
    }
}

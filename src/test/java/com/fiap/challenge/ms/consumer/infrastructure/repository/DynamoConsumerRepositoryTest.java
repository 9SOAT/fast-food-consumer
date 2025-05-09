package com.fiap.challenge.ms.consumer.infrastructure.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;
import com.fiap.challenge.ms.consumer.infrastructure.entity.ConsumerEntity;
import com.fiap.challenge.ms.consumer.infrastructure.mapper.EntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DynamoConsumerRepositoryTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private EntityMapper entityMapper;

    @Mock
    private PaginatedScanList scanList;

    private DynamoConsumerRepository repository;

    private ConsumerEntity entity;
    private Consumer consumer;

    @BeforeEach
    void setUp() {
        entity = ConsumerEntity.builder()
                .id(1L)
                .name("João")
                .email("joao@example.com")
                .cpf("12345678900")
                .build();

        consumer = Consumer.builder()
                .id(1L)
                .name("João")
                .email("joao@example.com")
                .cpf("12345678900")
                .build();

        repository = new DynamoConsumerRepository(dynamoDBMapper, entityMapper);
    }

    @Test
    void shouldSaveConsumer() {
        when(entityMapper.toConsumerEntity(consumer)).thenReturn(entity);
        when(entityMapper.toConsumer(entity)).thenReturn(consumer);

        Consumer saved = repository.save(consumer);

        verify(dynamoDBMapper).save(entity);
        assertThat(saved).isEqualTo(consumer);
    }

    @Test
    void shouldFindByCpf() {
        when(dynamoDBMapper.load(ConsumerEntity.class, "12345678900")).thenReturn(entity);
        when(entityMapper.toConsumer(entity)).thenReturn(consumer);

        Optional<Consumer> result = repository.findByCpf("12345678900");

        assertThat(result).isPresent().contains(consumer);
    }

    @Test
    void shouldReturnEmptyWhenCpfNotFound() {
        when(dynamoDBMapper.load(ConsumerEntity.class, "12345678900")).thenReturn(null);

        Optional<Consumer> result = repository.findByCpf("12345678900");

        assertThat(result).isEmpty();
    }

}
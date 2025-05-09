package com.fiap.challenge.ms.consumer.domain;

import com.fiap.challenge.ms.consumer.domain.model.PageResult;
import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;
import com.fiap.challenge.ms.consumer.domain.model.exception.NotFoundException;
import com.fiap.challenge.ms.consumer.domain.ports.outbound.ConsumerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DomainConsumerServiceTest {

    @Mock
    private ConsumerRepository consumerRepository;

    @InjectMocks
    private DomainConsumerService domainConsumerService;

    private Consumer consumer;

    @BeforeEach
    void setup() {
        consumer = Consumer.builder()
                .name("João")
                .email("joao@example.com")
                .cpf("12345678900")
                .build();
    }

    @Test
    void shouldReturnAllConsumers() {
        PageResult<Consumer> pageResult = PageResult.<Consumer>builder()
                .content(List.of(consumer))
                .pageNumber(1)
                .pageSize(10)
                .totalElements(1)
                .totalPages(1)
                .build();

        when(consumerRepository.findAll(1, 10)).thenReturn(pageResult);

        PageResult<Consumer> result = domainConsumerService.findAllConsumer(1, 10);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getCpf()).isEqualTo("12345678900");
        verify(consumerRepository).findAll(1, 10);
    }

    @Test
    void shouldCreateConsumer() {
        when(consumerRepository.save(consumer)).thenReturn(consumer);

        Consumer result = domainConsumerService.create(consumer);

        assertThat(result).isNotNull();
        verify(consumerRepository).save(consumer);
    }

    @Test
    void shouldFindConsumerByCpf() {
        when(consumerRepository.findByCpf("12345678900")).thenReturn(Optional.of(consumer));

        Consumer result = domainConsumerService.findByCpf("123.456.789-00");

        assertThat(result).isNotNull();
        assertThat(result.getCpf()).isEqualTo("12345678900");
        verify(consumerRepository).findByCpf("12345678900");
    }

    @Test
    void shouldThrowNotFoundExceptionWhenCpfNotFound() {
        when(consumerRepository.findByCpf("12345678900")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> domainConsumerService.findByCpf("123.456.789-00"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Consumer not found cpf=123.456.789-00");
    }
}

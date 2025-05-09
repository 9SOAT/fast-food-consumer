package com.fiap.challenge.ms.consumer.application.controller;

import com.fiap.challenge.ms.consumer.application.request.ConsumerMutation;
import com.fiap.challenge.ms.consumer.application.response.ConsumerView;
import com.fiap.challenge.ms.consumer.domain.model.PageResult;
import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;
import com.fiap.challenge.ms.consumer.domain.ports.inbound.ConsumerService;
import com.fiap.challenge.ms.consumer.infrastructure.mapper.ViewMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ConsumerControllerTest {

    @Mock
    private ConsumerService consumerService;

    @Mock
    private ViewMapper viewMapper;

    @InjectMocks
    private ConsumerController consumerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetConsumerByCpf() {
        String cpf = "12345678900";
        Consumer consumer = Consumer.builder().cpf(cpf).name("João").email("joao@example.com").build();
        ConsumerView consumerView = ConsumerView.builder().cpf(cpf).name("João").email("joao@example.com").build();

        when(consumerService.findByCpf(cpf)).thenReturn(consumer);
        when(viewMapper.toConsumerView(consumer)).thenReturn(consumerView);

        ConsumerView response = consumerController.getConsumerByCpf(cpf);

        assertThat(response).isNotNull();
        assertThat(response.cpf()).isEqualTo(cpf);

        verify(consumerService).findByCpf(cpf);
        verify(viewMapper).toConsumerView(consumer);
    }

    @Test
    void shouldGetAllConsumers() {
        int page = 1;
        int size = 10;
        Consumer consumer = Consumer.builder().cpf("12345678900").name("João").email("joao@example.com").build();
        ConsumerView consumerView = ConsumerView.builder().cpf("12345678900").name("João").email("joao@example.com").build();

        PageResult<Consumer> pageResult = PageResult.<Consumer>builder()
                .content(List.of(consumer))
                .pageNumber(page)
                .pageSize(size)
                .totalElements(1)
                .totalPages(1)
                .build();

        when(consumerService.findAllConsumer(page, size)).thenReturn(pageResult);
        when(viewMapper.toConsumerView(consumer)).thenReturn(consumerView);

        PageResult<ConsumerView> response = consumerController.getAllConsumers(page, size);

        assertThat(response).isNotNull();
        assertThat(response.getContent()).hasSize(1);
        assertThat(response.getContent().get(0).cpf()).isEqualTo("12345678900");

        verify(consumerService).findAllConsumer(page, size);
        verify(viewMapper).toConsumerView(consumer);
    }

    @Test
    void shouldCreateConsumer() {
        ConsumerMutation mutation = new ConsumerMutation("João", "joao@example.com", "12345678900");
        Consumer consumer = Consumer.builder().cpf(mutation.cpf()).name(mutation.name()).email(mutation.email()).build();
        Consumer savedConsumer = Consumer.builder().cpf(mutation.cpf()).name(mutation.name()).email(mutation.email()).build();
        ConsumerView consumerView = ConsumerView.builder().cpf(mutation.cpf()).name(mutation.name()).email(mutation.email()).build();

        when(viewMapper.toConsumer(mutation)).thenReturn(consumer);
        when(consumerService.create(consumer)).thenReturn(savedConsumer);
        when(viewMapper.toConsumerView(savedConsumer)).thenReturn(consumerView);

        ConsumerView response = consumerController.createConsumer(mutation);

        assertThat(response).isNotNull();
        assertThat(response.cpf()).isEqualTo(mutation.cpf());

        verify(viewMapper).toConsumer(mutation);
        verify(consumerService).create(consumer);
        verify(viewMapper).toConsumerView(savedConsumer);
    }
}

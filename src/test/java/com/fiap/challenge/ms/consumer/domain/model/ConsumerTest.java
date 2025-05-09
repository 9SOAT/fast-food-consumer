package com.fiap.challenge.ms.consumer.domain.model;

import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsumerTest {

    @Test
    void shouldCreateConsumerWithBuilder() {
        Consumer consumer = Consumer.builder()
                .id(1L)
                .name("João")
                .email("joao@example.com")
                .cpf("12345678900")
                .build();

        assertThat(consumer.getId()).isEqualTo(1L);
        assertThat(consumer.getName()).isEqualTo("João");
        assertThat(consumer.getEmail()).isEqualTo("joao@example.com");
        assertThat(consumer.getCpf()).isEqualTo("12345678900");
    }

    @Test
    void shouldNormalizeCpfByRemovingNonDigits() {
        Consumer consumer = new Consumer();
        consumer.setCpf("123.456.789-00");

        assertThat(consumer.getCpf()).isEqualTo("12345678900");
    }

    @Test
    void shouldCreateAndSetFieldsViaConstructors() {
        Consumer consumer = new Consumer(2L, "Maria", "maria@example.com", "98765432100");

        assertThat(consumer.getId()).isEqualTo(2L);
        assertThat(consumer.getName()).isEqualTo("Maria");
        assertThat(consumer.getEmail()).isEqualTo("maria@example.com");
        assertThat(consumer.getCpf()).isEqualTo("98765432100");
    }

    @Test
    void shouldSetAndGetAllFields() {
        Consumer consumer = new Consumer();
        consumer.setId(1L);
        consumer.setName("João");
        consumer.setEmail("joao@example.com");
        consumer.setCpf("12345678900");

        assertThat(consumer.getId()).isEqualTo(1L);
        assertThat(consumer.getName()).isEqualTo("João");
        assertThat(consumer.getEmail()).isEqualTo("joao@example.com");
        assertThat(consumer.getCpf()).isEqualTo("12345678900");
    }

    @Test
    void shouldCreateConsumerWithAllArgsConstructor() {
        Consumer consumer = new Consumer(2L, "Maria", "maria@example.com", "98765432100");

        assertThat(consumer.getId()).isEqualTo(2L);
        assertThat(consumer.getName()).isEqualTo("Maria");
        assertThat(consumer.getEmail()).isEqualTo("maria@example.com");
        assertThat(consumer.getCpf()).isEqualTo("98765432100");
    }

    @Test
    void shouldCreateConsumerUsingBuilder() {
        Consumer consumer = Consumer.builder()
                .id(3L)
                .name("Pedro")
                .email("pedro@example.com")
                .cpf("11122233344")
                .build();

        assertThat(consumer.getId()).isEqualTo(3L);
        assertThat(consumer.getName()).isEqualTo("Pedro");
        assertThat(consumer.getEmail()).isEqualTo("pedro@example.com");
        assertThat(consumer.getCpf()).isEqualTo("11122233344");
    }

}

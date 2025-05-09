package com.fiap.challenge.ms.consumer.infrastructure.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ConsumerEntityTest {

    @Test
    void shouldCreateConsumerEntityUsingBuilder() {
        ConsumerEntity entity = ConsumerEntity.builder()
                .cpf("12345678900")
                .name("João")
                .email("joao@example.com")
                .build();

        assertThat(entity.getCpf()).isEqualTo("12345678900");
        assertThat(entity.getName()).isEqualTo("João");
        assertThat(entity.getEmail()).isEqualTo("joao@example.com");
    }

    @Test
    void shouldCreateConsumerEntityUsingConstructorAndSetters() {
        ConsumerEntity entity = new ConsumerEntity();
        entity.setCpf("98765432100");
        entity.setName("Maria");
        entity.setEmail("maria@example.com");

        assertThat(entity.getCpf()).isEqualTo("98765432100");
        assertThat(entity.getName()).isEqualTo("Maria");
        assertThat(entity.getEmail()).isEqualTo("maria@example.com");
    }

    @Test
    void shouldCreateConsumerEntityWithAllArgsConstructor() {
        ConsumerEntity entity = new ConsumerEntity("11122233344", "Pedro", "pedro@example.com");

        assertThat(entity.getCpf()).isEqualTo("11122233344");
        assertThat(entity.getName()).isEqualTo("Pedro");
        assertThat(entity.getEmail()).isEqualTo("pedro@example.com");
    }

    @Test
    void shouldSetAndGetAllFields() {
        ConsumerEntity entity = new ConsumerEntity();

        entity.setCpf("12345678900");
        entity.setName("João");
        entity.setEmail("joao@example.com");

        assertThat(entity.getCpf()).isEqualTo("12345678900");
        assertThat(entity.getName()).isEqualTo("João");
        assertThat(entity.getEmail()).isEqualTo("joao@example.com");
    }

    @Test
    void shouldCreateInstanceUsingNoArgsConstructor() {
        ConsumerEntity entity = new ConsumerEntity();

        entity.setCpf("00011122233");
        entity.setName("Teste");
        entity.setEmail("teste@example.com");

        assertThat(entity.getCpf()).isEqualTo("00011122233");
        assertThat(entity.getName()).isEqualTo("Teste");
        assertThat(entity.getEmail()).isEqualTo("teste@example.com");
    }

}

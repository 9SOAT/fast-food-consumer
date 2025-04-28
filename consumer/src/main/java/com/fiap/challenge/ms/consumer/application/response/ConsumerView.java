package com.fiap.challenge.ms.consumer.application.response;

import lombok.Builder;

@Builder
public record ConsumerView (
    Long id,
    String name,
    String email,
    String cpf
){}

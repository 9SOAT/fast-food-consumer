package com.fiap.challenge.ms.consumer.application.response;

import lombok.Builder;

@Builder
public record ConsumerView (
    String name,
    String email,
    String cpf
){}

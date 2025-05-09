package com.fiap.challenge.ms.consumer.domain.model.consumer;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {

    private String cpf;
    private String name;
    private String email;

    public void setCpf(String cpf) {
        this.cpf = cpf.replaceAll("\\D", "");
    }
}


package com.fiap.challenge.ms.consumer.domain.model.consumer;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {
    private Long id;
    private String name;
    private String email;
    private String cpf;

    public void setCpf(String cpf) {
        this.cpf = cpf.replaceAll("\\D", "");
    }
}


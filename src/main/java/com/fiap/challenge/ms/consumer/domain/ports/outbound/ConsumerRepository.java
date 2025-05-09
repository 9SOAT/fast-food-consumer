package com.fiap.challenge.ms.consumer.domain.ports.outbound;

import com.fiap.challenge.ms.consumer.domain.model.PageResult;
import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;

import java.util.Optional;

public interface ConsumerRepository {

    public Consumer save(Consumer consumer);

    public Optional<Consumer> findByCpf(String cpf);

    public PageResult<Consumer> findAll(int page, int size);

}

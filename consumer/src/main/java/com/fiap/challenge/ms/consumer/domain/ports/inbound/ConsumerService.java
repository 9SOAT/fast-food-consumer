package com.fiap.challenge.ms.consumer.domain.ports.inbound;


import com.fiap.challenge.ms.consumer.domain.model.PageResult;
import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;

public interface ConsumerService {
    public PageResult<Consumer> findAllConsumer(int page, int size);
    public Consumer create(Consumer consumer);
    public Consumer findByCpf(String cpf);
}

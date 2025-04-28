package com.fiap.challenge.ms.consumer.domain;

import com.fiap.challenge.ms.consumer.domain.model.PageResult;
import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;
import com.fiap.challenge.ms.consumer.domain.model.exception.NotFoundException;
import com.fiap.challenge.ms.consumer.domain.ports.inbound.ConsumerService;
import com.fiap.challenge.ms.consumer.domain.ports.outbound.ConsumerRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainConsumerService implements ConsumerService {

    private final ConsumerRepository consumerRepository;

    public DomainConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @Override
    public PageResult<Consumer> findAllConsumer(int page, int size) {
        return consumerRepository.findAll(page, size);
    }

    @Override
    public Consumer create(Consumer consumer) {
        log.info("Creating consumer {}", consumer);
        return consumerRepository.save(consumer);
    }

    @Override
    public Consumer findByCpf(String cpf) {
        return consumerRepository.findByCpf(cpf.replaceAll("\\D", ""))
                .orElseThrow(() ->
                        new NotFoundException(String.format("Consumer not found cpf=%s", cpf), "CONSUMER_NOT_FOUND"));
    }
}
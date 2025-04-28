package com.fiap.challenge.ms.consumer.application.controller;

import com.fiap.challenge.ms.consumer.application.request.ConsumerMutation;
import com.fiap.challenge.ms.consumer.application.response.ConsumerView;
import com.fiap.challenge.ms.consumer.domain.model.PageResult;
import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;
import com.fiap.challenge.ms.consumer.domain.ports.inbound.ConsumerService;
import com.fiap.challenge.ms.consumer.infrastructure.mapper.PageResultMapper;
import com.fiap.challenge.ms.consumer.infrastructure.mapper.ViewMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerService consumerService;
    private final ViewMapper viewMapper;

    public ConsumerController(ConsumerService consumerService, ViewMapper viewMapper) {
        this.consumerService = consumerService;
        this.viewMapper = viewMapper;
    }

    @GetMapping("/{cpf}")
    public ConsumerView getConsumerByCpf(@Valid @CPF @PathVariable String cpf) {
        return viewMapper.toConsumerView(consumerService.findByCpf(cpf));
    }

    @GetMapping(params = {"page", "size"})
    public PageResult<ConsumerView> getAllConsumers(@RequestParam @Min(1) int page, @Max(50) @RequestParam int size) {
        PageResult<Consumer> consumers = consumerService.findAllConsumer(page, size);
        return PageResultMapper.transformContent(consumers, viewMapper::toConsumerView);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ConsumerView createConsumer(@Valid @RequestBody ConsumerMutation consumerMutation) {
        Consumer consumer = viewMapper.toConsumer(consumerMutation);
        return viewMapper.toConsumerView(consumerService.create(consumer));
    }
}

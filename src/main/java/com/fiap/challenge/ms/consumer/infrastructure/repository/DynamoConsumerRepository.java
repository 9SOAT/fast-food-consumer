package com.fiap.challenge.ms.consumer.infrastructure.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.fiap.challenge.ms.consumer.domain.model.PageResult;
import com.fiap.challenge.ms.consumer.domain.model.consumer.Consumer;
import com.fiap.challenge.ms.consumer.domain.ports.outbound.ConsumerRepository;
import com.fiap.challenge.ms.consumer.infrastructure.entity.ConsumerEntity;
import com.fiap.challenge.ms.consumer.infrastructure.mapper.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DynamoConsumerRepository implements ConsumerRepository {

    private final DynamoDBMapper dynamoDBMapper;
    private final EntityMapper entityMapper;

    public DynamoConsumerRepository(DynamoDBMapper dynamoDBMapper, EntityMapper entityMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.entityMapper = entityMapper;
    }

    @Override
    public Consumer save(Consumer consumer) {
        ConsumerEntity consumerEntity = entityMapper.toConsumerEntity(consumer);
        dynamoDBMapper.save(consumerEntity);
        return entityMapper.toConsumer(consumerEntity);
    }

    @Override
    public Optional<Consumer> findByCpf(String cpf) {
        ConsumerEntity consumerEntity = dynamoDBMapper.load(ConsumerEntity.class, cpf);
        return Optional.ofNullable(consumerEntity).map(entityMapper::toConsumer);
    }

    @Override
    public PageResult<Consumer> findAll(int page, int size) {
        List<ConsumerEntity> consumers = dynamoDBMapper.scan(ConsumerEntity.class, new DynamoDBScanExpression());

        List<Consumer> consumerList = consumers.stream()
                .sorted((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName())) // ordenação por nome
                .skip((long) (page - 1) * size)
                .limit(size)
                .map(entityMapper::toConsumer)
                .collect(Collectors.toList());

        long totalElements = consumers.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        return PageResult.<Consumer>builder()
                .content(consumerList)
                .pageNumber(page)
                .pageSize(size)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }

}

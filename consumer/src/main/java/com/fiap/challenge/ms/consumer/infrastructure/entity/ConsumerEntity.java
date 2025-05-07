package com.fiap.challenge.ms.consumer.infrastructure.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "consumer")
public class ConsumerEntity {

    @DynamoDBHashKey(attributeName = "cpf")
    private String cpf;

    @DynamoDBAttribute(attributeName = "id")
    private Long id;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "email")
    private String email;

}

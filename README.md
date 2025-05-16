# Consumer Microservice

**Consumer Microservice** é um serviço desenvolvido com **Java 21** e **Spring Boot** e faz parte da Fase 4 da Postech SOAT9 da FIAP. O serviço permite:

- Criar consumidores 
- Consultar consumidores por CPF
- Listar consumidores com paginação
- Persistir dados em **AWS DynamoDB**

---

## Tecnologias utilizadas

- Java 21
- Spring Boot 3.4
- Maven
- Lombok
- GitHub Actions (CI/CD Pipeline)

---

## Como rodar localmente

### Pré-requisitos

- Java 21 instalado
- Maven 3.8+ instalado
- Docker instalado
- AWS CLI (opcional, para criar a tabela no DynamoDB Local)

### Subir o DynamoDB Local

```bash
docker run -p 8000:8000 amazon/dynamodb-local
```

### Criar a tabela `consumer`

```bash
aws dynamodb create-table \
  --table-name consumer \
  --attribute-definitions AttributeName=cpf,AttributeType=S \
  --key-schema AttributeName=cpf,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=20,WriteCapacityUnits=20 \
  --endpoint-url http://localhost:8000 \
  --region us-east-1
```

### Rodar o projeto

```bash
mvn clean install
mvn spring-boot:run
```

Aplicação rodando em:  
`http://localhost:8080`

---

## API Endpoints

| Método | Rota | Descrição |
|:-------|:-----|:----------|
| `GET` | `/consumers/{cpf}` | Buscar consumidor por CPF |
| `GET` | `/consumers?page={page}&size={size}` | Listar consumidores com paginação |
| `POST` | `/consumers` | Criar novo consumidor |

### Exemplo de payload (POST /consumers)

```json
{
  "name": "João da Silva",
  "email": "joao.silva@example.com",
  "cpf": "12345678900"
}
```

---

## Testes

Para rodar os testes:

```bash
mvn test
```

O GitHub Actions executa:

- Análise estática de código (Codacy)
- Testes unitários (JUnit + Mockito)
- Validação de Qualidade e Cobertura de Código pelo SonarQube

---

## SonarCloud - Cobertura de código/testes
![image](https://github.com/user-attachments/assets/f390f294-0c0a-4bd0-b2dd-b879dba736ae)

## Extras

### DynamoDB Admin UI (opcional)

Para visualizar dados do DynamoDB Local com interface gráfica:

```bash


docker run -p 8001:8001 --env DYNAMO_ENDPOINT=http://host.docker.internal:8000 aaronshaf/dynamodb-admin
```

Acesse em: [http://localhost:8001](http://localhost:8001)

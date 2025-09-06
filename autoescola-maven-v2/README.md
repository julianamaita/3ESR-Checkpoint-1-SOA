# Autoescola API (Maven / Java 17 / Spring Boot 3)

Checkpoint 1 – SOA e WebServices — **API ReST** para cadastro/listagem/atualização/exclusão (soft delete) de **Instrutores** e **Alunos**, e **agendamento/cancelamento** de instruções.

- **Java** 17
- **Spring Boot** 3.3.x
- **Maven**
- **JPA/Hibernate**
- **Bean Validation**
- **Flyway** (migrações)
- **H2** em memória

## Como rodar
```bash
mvn spring-boot:run
# base path
# http://localhost:8080/api
# H2 console: http://localhost:8080/api/h2  (JDBC: jdbc:h2:mem:autoescola)
```

## Endpoints principais
- `POST /instructors` — cria instrutor (telefone oculto inicialmente)
- `GET /instructors?page=0&size=10` — lista ativos (ordenado por nome, paginação 10)
- `PUT /instructors/{id}` — atualiza **apenas**: nome, telefone, endereço
- `DELETE /instructors/{id}` — inativa (soft delete)

- `POST /students` — cria aluno
- `GET /students?page=0&size=10` — lista ativos (ordenado por nome, paginação 10)
- `PUT /students/{id}` — atualiza **apenas**: nome, telefone, endereço
- `DELETE /students/{id}` — inativa (soft delete)

- `POST /appointments` — agenda (duração fixa 1h; validações)
- `POST /appointments/{id}/cancel` — cancela (motivo obrigatório; 24h antecedência)

## Regras de negócio implementadas
- Horário de funcionamento: **Seg–Sáb, 06:00–21:00** (America/Sao_Paulo)
- Agendamento com **mín. 30 min** de antecedência
- **Máx. 2** instruções por dia por aluno
- Proíbe agendar com **aluno/instrutor inativos**
- Evita conflito do **instrutor** no mesmo horário
- Se `instructorId` **não** informado, o sistema escolhe **aleatoriamente** um instrutor ativo disponível
- Cancelamento com **mín. 24h**; motivo: `ALUNO_DESISTIU`, `INSTRUTOR_CANCELOU`, `OUTROS`

## DTO / VO / Entidades
- **DTOs**: `InstructorCreateDTO`, `InstructorUpdateDTO`, `StudentCreateDTO`, `StudentUpdateDTO`, `AppointmentCreateDTO`, `AppointmentCancelDTO`, `AddressDTO`
- **VOs (View Objects)**: `InstructorListVO`, `StudentListVO`
- **Entidades**: `Instructor`, `Student`, `Appointment`, `Address` (embeddable), `Specialty`, `CancellationReason`

## Alunos do grupo
- **Luana Cabezaolias (RM 99320)**
- **Juliana Maita (RM 99224)**
- **Pedro Henrique Farath (RM 98608)**
- **Joao Victor Morais (RM 550453)**
- **Lucca Vilaça (RM 551538)**


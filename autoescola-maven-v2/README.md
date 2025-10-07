
# Autoescola API (Maven / Java 17 / Spring Boot 3)

**Checkpoint 1 + Checkpoint 2 – SOA e WebServices**  
API ReST para cadastro de **Instrutores** e **Alunos**, **agendamento/cancelamento** de instruções e **gestão de usuários autenticados via JWT**.

---

## 🚀 Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.3.x**
- **Spring Security + JWT**
- **Maven**
- **JPA/Hibernate**
- **Bean Validation**
- **Flyway** (migrações)
- **Banco H2 em memória**

---

## ⚙️ Como rodar o projeto

```bash
mvn spring-boot:run
````

Acesse:

* API base: [http://localhost:8080/api](http://localhost:8080/api)
* Console H2: [http://localhost:8080/api/h2](http://localhost:8080/api/h2)
  JDBC: `jdbc:h2:mem:autoescola`

---

## 📚 Endpoints Principais (Checkpoint 1)

### Instrutores

| Método   | Endpoint                      | Descrição                          |
| :------- | :---------------------------- | :--------------------------------- |
| `POST`   | `/instructors`                | Cria instrutor                     |
| `GET`    | `/instructors?page=0&size=10` | Lista instrutores ativos           |
| `PUT`    | `/instructors/{id}`           | Atualiza nome, telefone e endereço |
| `DELETE` | `/instructors/{id}`           | Inativa (soft delete)              |

### Alunos

| Método   | Endpoint                   | Descrição                          |
| :------- | :------------------------- | :--------------------------------- |
| `POST`   | `/students`                | Cria aluno                         |
| `GET`    | `/students?page=0&size=10` | Lista alunos ativos                |
| `PUT`    | `/students/{id}`           | Atualiza nome, telefone e endereço |
| `DELETE` | `/students/{id}`           | Inativa (soft delete)              |

### Agendamentos

| Método | Endpoint                    | Descrição                                   |
| :----- | :-------------------------- | :------------------------------------------ |
| `POST` | `/appointments`             | Agenda instrução (1 h)                      |
| `POST` | `/appointments/{id}/cancel` | Cancela com motivo (≥ 24 h de antecedência) |

---

## 🧠 Regras de Negócio (Checkpoint 1)

* Funcionamento: **Seg–Sáb 06:00–21:00** (America/Sao_Paulo)
* Agendamento com **mínimo 30 min** de antecedência
* **Máx. 2** aulas por dia por aluno
* Proíbe agendar com **instrutor/aluno inativos**
* Evita **conflitos de horário** com o mesmo instrutor
* Se `instructorId` não for informado, o sistema escolhe **instrutor disponível** aleatoriamente
* Cancelamento com **mín. 24 h**, motivos: `ALUNO_DESISTIU`, `INSTRUTOR_CANCELOU`, `OUTROS`

---

## 🧩 DTO / VO / Entidades

* **DTOs:** `InstructorCreateDTO`, `InstructorUpdateDTO`, `StudentCreateDTO`, `StudentUpdateDTO`, `AppointmentCreateDTO`, `AppointmentCancelDTO`, `AddressDTO`
* **VOs:** `InstructorListVO`, `StudentListVO`
* **Entidades:** `Instructor`, `Student`, `Appointment`, `Address` (embeddable), `Specialty`, `CancellationReason`, `User`, `Role`

---

## 🔐 Checkpoint 2 – Gestão de Usuários (JWT + RBAC)

### 🧱 Funcionalidades

* Cadastro de usuários com **senha criptografada (BCrypt)**
* **Login via JWT** (`POST /api/auth/login`)
* Controle de acesso: apenas **ADMIN** pode criar, listar e atualizar usuários
* Usuário autenticado pode **alterar a própria senha**

---

### 👤 Login

**POST /api/auth/login**

**Body:**

```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Response:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer"
}
```

---

### 👥 Usuários (ADMIN)

| Método   | Endpoint                | Descrição                    |
| :------- | :---------------------- | :--------------------------- |
| `POST`   | `/api/admin/users`      | Cria novo usuário            |
| `GET`    | `/api/admin/users`      | Lista usuários               |
| `PATCH`  | `/api/admin/users/{id}` | Atualiza `role` ou `enabled` |
| `DELETE` | `/api/admin/users/{id}` | Exclui usuário               |

**Body (POST):**

```json
{
  "username": "professor",
  "password": "senha123",
  "role": "ADMIN"
}
```

**Header:**
`Authorization: Bearer <token>`

---

### 🔄 Alterar Senha (usuário autenticado)

**POST /api/profile/change-password**

**Body:**

```json
{
  "oldPassword": "admin123",
  "newPassword": "novaSenhaSegura!"
}
```

**Header:**
`Authorization: Bearer <token>`

---

### 🗃️ Migration `V2__users.sql`

```sql
CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(120) NOT NULL UNIQUE,
  password VARCHAR(200) NOT NULL,
  role VARCHAR(20) NOT NULL DEFAULT 'USER',
  enabled BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Usuário inicial: admin / admin123 (BCrypt)
INSERT INTO users (username, password, role, enabled)
VALUES ('admin', '$2a$10$6a5cF1L2tO0G4n9i3E0C3u0c6b1pZc5e5k9Kf3wB7pOQvYk9V3OQy', 'ADMIN', TRUE);
```

---

## ⚙️ Configuração

Arquivo `application.yml`:

```yaml
security:
  jwt:
    secret: super-secret-key-change-me-please-32-chars
    expirationMillis: 86400000
```

> **Dica:** altere o segredo para produção e use variáveis de ambiente.

---

## 👨‍💻 Grupo

* **Luana Cabezaolias (RM 99320)**
* **Juliana Maita (RM 99224)**
* **Pedro Henrique Farath (RM 98608)**
* **Joao Victor Morais (RM 550453)**
* **Lucca Vilaça (RM 551538)**

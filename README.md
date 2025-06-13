# 🔐 API Segura

Este projeto é uma API RESTful em Java com Spring Boot que implementa autenticação baseada em JWT (JSON Web Token), utilizando Spring Security. Ele serve como base para sistemas que precisam proteger endpoints com tokens e seguir boas práticas de segurança em APIs.

---

## 🧰 Tecnologias

- Java 17
- Spring Boot 3.5.0
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- JWT (jjwt)
- BCrypt

---

## 📌 Funcionalidades

- Registro e autenticação de usuários
- Geração de token JWT ao autenticar
- Proteção de endpoints com autenticação via token
- Filtro customizado `JwtAuthFilter` para validar tokens em todas as requisições
- Controle de acesso baseado em roles

---

## 📁 Estrutura de pacotes

```
com.dandaraemiliano.api_segura
├── config         
├── controller    
├── dto            
├── model          
├── repository     
├── util          
└── ApiSeguraApplication.java
```

---

## 🛠️ Como rodar

1. Configure o banco de dados PostgreSQL com:

```sql
CREATE DATABASE apisegura;
CREATE USER seu_usuario WITH PASSWORD 'sua_senha';
```

2. Altere `application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/apisegura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

3. Rode o projeto:

```bash
./mvnw spring-boot:run
```

---

## 🔐 Exemplo de autenticação (login)

### Requisição
```http
POST /auth/login
Content-Type: application/json

{
  "username": "seu_usuario",
  "password": "sua_senha"
}
```

### Resposta
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## 🧪 Endpoint protegido

### Requisição

```http
GET /usuarios/me
Authorization: Bearer <token>
```

---

## 👩🏻‍💻 Autora

**Dandara Emiliano**  
[GitHub](https://github.com/DandaraEmiliano) · [LinkedIn](https://linkedin.com/in/dandaraemiliano)

---

## 📄 Licença

Projeto sob licença MIT.

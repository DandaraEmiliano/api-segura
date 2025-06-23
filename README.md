# API Segura

API REST em Java com Spring Boot, autenticação JWT e boas práticas de segurança.

## Visão Geral

Esta API demonstra uma arquitetura limpa, autenticação segura com JWT, validação de dados, tratamento global de exceções e documentação automática com Swagger.

## Funcionalidades
- Cadastro e autenticação de usuários
- Proteção de endpoints com JWT
- Retorno de dados do usuário autenticado
- Validação de dados de entrada
- Tratamento padronizado de erros
- Documentação interativa via Swagger

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Token)
- Swagger (springdoc-openapi)
- JUnit & Mockito (testes)

## Requisitos
- Java 17+
- Maven 3.8+
- PostgreSQL

## Configuração

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/api-segura.git
   cd api-segura
   ```

2. **Configure o banco de dados:**
   - Crie um banco PostgreSQL chamado `apisegura`.
   - Crie um usuário e senha para acesso.

3. **Crie o arquivo de propriedades:**
   - Copie o arquivo de exemplo:
     ```bash
     cp src/main/resources/application-example.properties src/main/resources/application.properties
     ```
   - Edite `src/main/resources/application.properties` com seus dados reais:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/apisegura
     spring.datasource.username=SEU_USUARIO
     spring.datasource.password=SUA_SENHA
     jwt.secret=SUA_CHAVE_SECRETA
     ```

4. **Instale as dependências:**
   ```bash
   ./mvnw clean install
   ```

## Executando a Aplicação

```bash
./mvnw spring-boot:run
```

Acesse a API em: `http://localhost:8080`

## Documentação Swagger

Acesse a documentação interativa em:
- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- ou [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Endpoints Principais

- `POST /auth/register` — Cadastro de usuário
- `POST /auth/login` — Autenticação (retorna JWT)
- `GET /usuarios/me` — Dados do usuário autenticado (JWT obrigatório)

## Exemplos de Uso

### Cadastro de Usuário
**POST** `/auth/register`
```json
{
  "username": "maria",
  "password": "SenhaForte123"
}
```

### Login
**POST** `/auth/login`
```json
{
  "username": "maria",
  "password": "SenhaForte123"
}
```
**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

### Endpoint Protegido
**GET** `/usuarios/me`
- Header: `Authorization: Bearer SEU_TOKEN_AQUI`

## Testes

Execute os testes automatizados com:
```bash
./mvnw test
```

## Segurança
- Nunca versionar o arquivo `application.properties` (já está no `.gitignore`).
- Nunca compartilhe sua chave JWT ou senha do banco.
- Use variáveis de ambiente ou secrets em produção.

## Boas Práticas
- Utilize DTOs para entrada e saída de dados.
- Centralize regras de negócio em serviços.
- Trate exceções de forma global.
- Valide todos os dados de entrada.
- Documente e teste sua API.

## Licença
[MIT](LICENSE)

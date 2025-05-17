# Challenge OdontoPrev

## Integrantes e Responsabilidades

### Eduardo Rodrigues (RM553705)
- **Responsabilidade**:
  - Disruptive Architectures: IoT, IOB & Generative AI
  - Compliance & Quality Assurance

### Giovani Borba (RM553724)
- **Responsabilidade**:
  - Java Advanced
  - Mobile Development Application
  - Mastering Relational and Non-relational Database

### Matheus Silva (RM553180)
- **Responsabilidade**:
  - Advanced Business Development with .NET
  - DevOps Tools and Cloud Computing


## 🚀 Como executar o projeto localmente

### Pré-requisitos

- Java 17+
- Maven
- Docker Desktop (instalado e em execução)
- IDE como IntelliJ ou VSCode

---

### 1. Iniciar o Docker e o RabbitMQ

Certifique-se de que o Docker Desktop está **aberto e em execução**.

Depois, execute o seguinte comando no terminal (CMD):

```bash
docker run -d --hostname rabbitmq --name rabbitmq-container -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

#### A interface de gerenciamento estará acessível em:
👉 http://localhost:15672
- Usuário padrão: guest
- Senha padrão: guest

### 2. Configurar o `application.properties`

- Edite o arquivo `src/main/resources/application.properties` com as seguintes informações:

#### ✅ API Key da OpenAI e Banco de Dados

```properties
spring.ai.openai.api-key=sk-sua-chave-aqui
spring.ai.openai.model=gpt-3.5-turbo

spring.datasource.url=jdbc:oracle:thin:@//host:porta/serviço
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
spring.jpa.hibernate.ddl-auto=update
```
- Na configuração do banco de dados você pode optar por colocar seu login do Oracle ou utilizar o H2 localmente.

---


## Sprint 4
### Este projeto foi desenvolvido como parte do desafio acadêmico "Challenge OdontoPrev", com foco na criação de uma aplicação backend em Java usando o ecossistema Spring Boot. A aplicação se conecta a uma base de dados, integra mensageria com RabbitMQ, oferece recursos de inteligência artificial com Spring AI, inclui segurança com Spring Security e possui suporte para autenticação, internacionalização e monitoramento.

---

## Vídeo do projeto: https://www.youtube.com/watch?v=Xs5JSb0sboM

---

### ✅ Funcionalidades implementadas

- **Autenticação e Autorização** com Spring Security, incluindo gestão de perfis (roles);
- **Internacionalização (i18n)** com suporte para português e inglês;
- **Mensageria com RabbitMQ** (produtores e consumidores configurados);
- **Monitoramento com Spring Boot Actuator**;
- **Integração com OpenAI via Spring AI**, utilizando prompt customizado para respostas odontológicas.



## Sprint 3

### Endpoints para verificação Web
- GET /
  1. Página inicial do sistema.

Usuários
- GET /web/usuarios
  1. Lista todos os usuários cadastrados.

Tratamentos
- GET /web/tratamentos
  1. Lista todos os tratamentos cadastrados.

## Link: https://www.youtube.com/watch?v=XS3RI5sCbp4


---


## Sprint 2

## Imagens dos Diagramas

![c4fcf2ec-1b3b-45e0-8fc2-58e329fe3bf9](https://github.com/user-attachments/assets/519b0b79-3c3a-4a8d-a9b7-f822d84266aa)

![diagramaSprint2](https://github.com/user-attachments/assets/38935dda-6087-4e4d-a555-2ae2df88e41b)


![diagramSprint2](https://github.com/user-attachments/assets/b5247b41-70de-4fad-97ff-ac445616317d)


## Vídeo Apresentando a Proposta Tecnológica

### Link do Vídeo: https://www.youtube.com/watch?v=1LRfoJNFMBQ

## Documentação da API

### Endpoints de Usuários

- **POST /usuarios**
    - Cria um novo usuário.

- **GET /usuarios**
    - Recupera todos os usuários.

- **GET /usuarios/{id}**
    - Recupera um usuário específico pelo ID.

- **DELETE /usuarios/{id}**
    - Remove um usuário específico pelo ID.

### Endpoints de Tratamentos

- **POST /tratamentos**
    - Cria um novo tratamento.

- **GET /tratamentos**
    - Recupera todos os tratamentos.

- **GET /tratamentos/{id}**
    - Recupera um tratamento específico pelo ID.

- **DELETE /tratamentos/{id}**
    - Remove um tratamento específico pelo ID.

### Endpoints de Consultas

- **POST /consultas**
    - Cria uma nova consulta.

- **GET /consultas**
    - Recupera todas as consultas.

- **GET /consultas/{id}**
    - Recupera uma consulta específica pelo ID.

- **DELETE /consultas/{id}**
    - Remove uma consulta específica pelo ID.

- **GET /consultas/usuario/{usuarioId}**
    - Recupera todas as consultas de um usuário específico pelo ID.


## Exemplos de Requisições

### Usuários

#### Criar Usuário (POST /usuarios)

**URL:** `http://localhost:8080/usuarios`

**Método:** `POST`

**Body (JSON):**
```json
{
    "nome": "João Silva",
    "email": "joao.silva@example.com",
    "senha": "senhaSegura123"
}
```

### Tratamentos

#### Criar Tratamento (POST /tratamentos)

**URL:** `http://localhost:8080/tratamentos`

**Método:** `POST`

**Body (JSON):**
```json
{
    "nome": "Limpeza dental"
}
```

### Consultas

#### Criar Consulta (POST /consultas)

**URL:** `http://localhost:8080/consultas`

**Método:** `POST`

**Body (JSON):**
```json
{
    "nome": "Consulta",
    "data": "2024-09-03T20:30:00", 
    "tratamentos": [
        {
            "id": "(UUID do tratamento)",
            "nome": "(Nome do tratamento)"
        }
    ],
    "usuarioId": "(UUID do usuário)" 
}
```


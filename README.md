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


## Instruções para Rodar a Aplicação

Para executar a aplicação, siga os passos abaixo:

1. **Clone o repositório**
2. **Navegue até a pasta do projeto**
3. **Execute o projeto**: 
   1. Abra o IntelliJ e importe o projeto como um projeto Maven.
   2. Execute a aplicação (ChallengeOdontoprevApplication)


## Sprint 3
### Sobre o projeto
- O projeto consiste em um sistema de gerenciamento de tratamentos e consultas médicas, com funcionalidades para cadastro de usuários, agendamento de consultas, lembretes de tratamentos e auditoria de operações no banco de dados. O sistema foi desenvolvido utilizando Java com Spring Boot para o backend, Oracle Database para o banco de dados e Thymeleaf para a camada de visualização.

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


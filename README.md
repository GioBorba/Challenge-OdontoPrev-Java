# Challenge OdontoPrev



## Integrantes e Responsabilidades

### Eduardo Rodrigues (RM 553705)
- **Responsabilidade**:
  - Disruptive Architectures: IoT, IOB & Generative AI
  - Mobile Development Application
- **Tarefas**:
  - Desenvolvimento das funcionalidades do aplicativo usando Kotlin.
  - Implementação de recursos de IoT no aplicativo.
  - Integração de inteligência artificial generativa para melhorar a experiência do usuário.

### Giovani Borba (RM 553724)
- **Responsabilidade**:
  - Java Advanced
  - Compliance & Quality Assurance
  - Mastering Relational and Non-relational Database
- **Tarefas**:
  - Desenvolvimento do backend com Spring Boot e JPA.
  - Implementação da gestão de banco de dados usando Oracle.
  - Garantia de conformidade e qualidade no processo de desenvolvimento de software.

### Matheus Silva (RM 553180)
- **Responsabilidade**:
  - Advanced Business Development with .NET
  - DevOps Tools and Cloud Computing
- **Tarefas**:
  - Supervisão das estratégias de implantação e integração em nuvem.
  - Desenvolvimento de serviços de backend usando .NET.
  - Implementação de práticas de DevOps para integração e entrega contínuas.


## Instruções para Rodar a Aplicação

Para executar a aplicação, siga os passos abaixo:

1. **Clone o repositório**
2. **Navegue até a pasta do projeto**
3. **Execute o projeto**: 
   1. Abra o IntelliJ e importe o projeto como um projeto Maven.
   2. Execute a aplicação (ChallengeOdontoprevApplication)

## Imagens dos Diagramas

### Diagrama de Classes

-diagrama 1-

### Diagrama de Sequência

-diagrama 2-

## Vídeo Apresentando a Proposta Tecnológica

### Link do Vídeo: 

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


# Sistema de Gerenciamento de Tarefas

## Funcionalidades

Esta API permite o gerenciamento de listas de tarefas, permitindo operações como criar, editar, visualizar, adicionar itens, atualizar itens, e remover itens de uma lista. As principais funcionalidades incluem:

- **Criação de Listas**: Criação de listas de tarefas para organizar itens.
- **Gerenciamento de Itens**: Adicionar, editar, e remover itens dentro de uma lista.
- **Alteração de Estado**: Alterar o estado de um item (por exemplo, "A Fazer", "Concluído").
- **Visualização de Listas**: Visualizar todas as listas e itens associados.
- **Remoção de Listas e Itens**: Excluir listas e itens específicos.

## Como Executar a API

### Requisitos
- Docker
- Docker Compose

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-repositorio/gerenciador-de-tarefas.git
   ```

2. Execute a aplicação com Docker Compose:
   ```bash
   docker-compose up -d --build
   ```

3. A API estará disponível em: `http://localhost:8080/api/v1/tarefas`.


---

## Exemplos de Requisições

### 1. Criar uma Lista
- **Endpoint**: `POST /api/v1/tarefas`
- **Request Body**:
  ```json
  {
    "nome": "Minha Lista"
  }
  ```
- **Response**: `201 Created`
  ```json
  {
    "id": 1,
    "nome": "Minha Lista",
    "itens": []
  }
  ```

### 2. Obter Todas as Listas
- **Endpoint**: `GET /api/v1/tarefas`
- **Response**: `200 OK`
  ```json
  [
    {
      "id": 1,
      "nome": "Minha Lista",
      "itens": []
    }
  ]
  ```

### 3. Obter Lista por ID
- **Endpoint**: `GET /api/v1/tarefas/{tarefaId}`
- **Response**: `200 OK`
  ```json
  {
    "id": 1,
    "nome": "Minha Lista",
    "itens": []
  }
  ```

### 4. Adicionar Item a uma Lista
- **Endpoint**: `POST /api/v1/tarefas/{tarefaId}/itens`
- **Request Body**:
  ```json
  {
    "titulo": "Novo Item",
    "estado": "FAZER",
    "destacado": false
  }
  ```
- **Response**: `200 OK`

### 5. Atualizar Item
- **Endpoint**: `PUT /api/v1/tarefas/{tarefaId}/itens/{itemId}`
- **Request Body**:
  ```json
  {
    "titulo": "Item Atualizado",
    "estado": "CONCLUIDO",
    "destacado": true
  }
  ```
- **Response**: `200 OK`
  ```json
  {
    "id": 1,
    "titulo": "Item Atualizado",
    "estado": "CONCLUIDO",
    "destacado": true,
    "tarefa": {
      "id": 1,
      "nome": "Minha Lista"
    }
  }
  ```

### 6. Alterar o Estado de um Item
- **Endpoint**: `PATCH /api/v1/tarefas/{tarefaId}/itens/{itemId}/estado`
- **Request Body**:
  ```json
  "CONCLUIDO"
  ```
- **Response**: `200 OK`
  ```json
  {
    "id": 1,
    "nome": "Minha Lista",
    "itens": [
      {
        "id": 1,
        "titulo": "Item Atualizado",
        "estado": "CONCLUIDO",
        "destacado": false
      }
    ]
  }
  ```

### 7. Remover Item de uma Lista
- **Endpoint**: `DELETE /api/v1/tarefas/{tarefaId}/itens/{itemId}`
- **Response**: `204 No Content`

### 8. Remover uma Lista
- **Endpoint**: `DELETE /api/v1/tarefas/{tarefaId}`
- **Response**: `204 No Content`


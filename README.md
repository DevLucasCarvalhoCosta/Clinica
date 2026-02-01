# 🏥 Sistema de Gerenciamento de Clínica

Sistema de gerenciamento clínico desenvolvido com **Spring Boot** (Backend) e **Vue.js** (Frontend).

---

## 📋 Sobre o Projeto

Este é um **projeto acadêmico** desenvolvido para fins de estudo e aprendizado de tecnologias web.

Trata-se de uma aplicação web para gerenciamento de uma clínica médica, permitindo o cadastro e controle de **pacientes**, **médicos** e **consultas**. O sistema foi desenvolvido utilizando arquitetura REST, seguindo boas práticas de desenvolvimento.

---

## 🛠️ Tecnologias Utilizadas

### Backend
| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| Java | 17 | Linguagem de programação |
| Spring Boot | 3.3.5 | Framework principal |
| Spring Data JPA | - | Persistência de dados |
| Spring Validation | - | Validação de dados |
| PostgreSQL | - | Banco de dados relacional |
| Lombok | 1.18.36 | Redução de boilerplate code |
| Maven | - | Gerenciador de dependências |

### Frontend
| Tecnologia | Descrição |
|------------|-----------|
| Vue.js | Framework JavaScript progressivo |

---

## 📁 Estrutura do Projeto

```
clinica/
├── src/main/java/com/clinica/
│   ├── ClinicaApplication.java      # Classe principal
│   ├── config/
│   │   ├── CorsConfig.java          # Configuração de CORS
│   │   └── JacksonConfig.java       # Configuração do Jackson (JSON)
│   ├── controller/
│   │   ├── PacienteController.java  # Endpoints de pacientes
│   │   ├── MedicoController.java    # Endpoints de médicos
│   │   └── ConsultaController.java  # Endpoints de consultas
│   ├── model/
│   │   ├── Paciente.java            # Entidade Paciente
│   │   ├── Medico.java              # Entidade Médico
│   │   └── Consulta.java            # Entidade Consulta
│   ├── repository/
│   │   ├── PacienteRepository.java  # Repositório de pacientes
│   │   ├── MedicoRepository.java    # Repositório de médicos
│   │   └── ConsultaRepository.java  # Repositório de consultas
│   └── services/
│       └── MedicoService.java       # Serviço de médicos
├── src/main/resources/
│   └── application.properties       # Configurações da aplicação
├── clinica-frontend/                # Frontend Vue.js
├── pom.xml                          # Dependências Maven
└── README.md
```

---

## 🗃️ Modelo de Dados

### Entidades e Relacionamentos

```
┌─────────────┐       ┌─────────────┐       ┌─────────────┐
│  PACIENTE   │       │  CONSULTA   │       │   MEDICO    │
├─────────────┤       ├─────────────┤       ├─────────────┤
│ id          │       │ id          │       │ id          │
│ nome        │       │ dataHora    │       │ nome        │
│ cpf         │◄──────│ paciente_id │       │ especialidade│
│ telefone    │  1:N  │             │  N:M  │             │
│             │       │   medicos   │───────►             │
└─────────────┘       └─────────────┘       └─────────────┘
```

### Paciente
| Campo | Tipo | Validação |
|-------|------|-----------|
| id | Long | Auto-gerado |
| nome | String | Obrigatório |
| cpf | String | 11 dígitos |
| telefone | String | Formato: (XX) XXXXX-XXXX |

### Médico
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | Long | Auto-gerado |
| nome | String | Nome do médico |
| especialidade | String | Especialidade médica |

### Consulta
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | Long | Auto-gerado |
| dataHora | LocalDateTime | Data e hora da consulta |
| paciente | Paciente | Paciente da consulta (N:1) |
| medicos | List\<Medico\> | Médicos da consulta (N:M) |

---

## 🔌 API Endpoints

### Pacientes (`/api/pacientes`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/pacientes` | Lista todos os pacientes |
| POST | `/api/pacientes` | Cadastra um novo paciente |

### Médicos (`/api/medicos`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/medicos` | Lista todos os médicos |
| POST | `/api/medicos` | Cadastra um novo médico |

### Consultas (`/api/consultas`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/consultas` | Lista todas as consultas |
| POST | `/api/consultas` | Cria uma nova consulta |
| PUT | `/api/consultas/{id}` | Atualiza uma consulta |
| DELETE | `/api/consultas/{id}` | Remove uma consulta |

---

## ⚙️ Configuração e Execução

### Pré-requisitos
- Java 17+
- PostgreSQL
- Maven
- Node.js (para o frontend)

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL chamado `clinica`:
```sql
CREATE DATABASE clinica;
```

2. Configure as credenciais em `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/clinica
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```

### Executando o Backend

```bash
# Clone o repositório
git clone <url-do-repositorio>

# Navegue até a pasta do projeto
cd clinica

# Execute com Maven Wrapper
./mvnw spring-boot:run

# Ou no Windows
mvnw.cmd spring-boot:run
```

O servidor será iniciado em `http://localhost:8080`

### Executando o Frontend

```bash
# Navegue até a pasta do frontend
cd clinica-frontend

# Instale as dependências
npm install

# Execute o servidor de desenvolvimento
npm run dev
```

---

## 📝 Exemplos de Uso da API

### Criar Paciente
```json
POST /api/pacientes
{
  "nome": "João Silva",
  "cpf": "12345678901",
  "telefone": "(11) 99999-9999"
}
```

### Criar Médico
```json
POST /api/medicos
{
  "nome": "Dra. Maria Santos",
  "especialidade": "Cardiologia"
}
```

### Criar Consulta
```json
POST /api/consultas
{
  "dataHora": "2026-02-15T14:30:00",
  "paciente": { "id": 1 },
  "medicos": [{ "id": 1 }, { "id": 2 }]
}
```

---

## 🔐 Configurações de CORS

O projeto possui configuração de CORS para permitir requisições do frontend Vue.js em desenvolvimento.

---

## 📄 Licença

Este projeto está sob a licença MIT.

---

## 👨‍💻 Autor

Desenvolvido como projeto de estudo para gerenciamento de clínicas médicas.

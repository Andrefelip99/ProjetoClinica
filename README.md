# 🏥 Saúde - Sistema de Gestão Clínica

## 📋 Sobre o Projeto

Sistema Back-End desenvolvido em Java utilizando Spring Boot e PostgreSQL para auxiliar na gestão de clínicas de pequeno e médio porte.

A aplicação foi projetada para organizar o fluxo de atendimento clínico, permitindo o gerenciamento de pacientes, consultas, médicos, enfermeiros e administradores em uma estrutura centralizada e escalável.

O projeto foi desenvolvido aplicando conceitos de Programação Orientada a Objetos, persistência de dados com JPA/Hibernate e arquitetura em camadas.

---

## 🚀 Tecnologias Utilizadas

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Lombok
* Bean Validation
* Maven
* Insomnia

---

## 🏗️ Arquitetura

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Model
```

A aplicação segue o padrão de arquitetura em camadas, promovendo organização, desacoplamento e facilidade de manutenção.

---

## 📦 Principais Entidades

### Administrador

Responsável pelo gerenciamento geral do sistema.

* Cadastro de pacientes
* Gerenciamento de registros
* Controle administrativo

### Paciente

Armazena informações clínicas e cadastrais:

* Nome
* Idade
* CPF
* Data de nascimento
* Telefone
* Endereço
* Histórico de consultas

### Médico

Responsável pelo atendimento clínico.

* Nome
* Especialidade
* CRM

### Enfermeiro

Responsável pelo suporte operacional durante os atendimentos.

* Nome
* COREN

### Consulta

Representa os atendimentos realizados na clínica.

* Data e horário
* Paciente
* Médico
* Enfermeiro
* Observações
* Status da consulta

### Cadastro Inicial

Entidade responsável pelo registro inicial de usuários no sistema.

---

## 🔗 Relacionamentos

### Paciente → Consultas

Um paciente pode possuir diversas consultas cadastradas.

```text
Paciente 1 ---- N Consultas
```

### Administrador → Pacientes

Cada paciente está associado a um administrador responsável pelo gerenciamento do cadastro.

```text
Administrador 1 ---- N Pacientes
```

---

## 📅 Status das Consultas

O sistema utiliza um Enum para controlar o ciclo de vida das consultas.

```text
AGENDADA
REALIZADA
CANCELADA
```

---

## ⚙️ Funcionalidades

### Gestão de Pacientes

* Cadastro
* Atualização
* Consulta de informações
* Histórico de atendimentos

### Gestão de Consultas

* Agendamento
* Cancelamento
* Controle de status
* Registro de observações

### Gestão de Profissionais

* Cadastro de médicos
* Cadastro de enfermeiros
* Controle de informações profissionais

### Administração

* Gerenciamento de pacientes
* Controle operacional da clínica

---

## 📚 Conceitos Aplicados

### Back-End

* API REST
* Arquitetura em Camadas
* Injeção de Dependência
* Programação Orientada a Objetos

### Persistência

* PostgreSQL
* JPA
* Hibernate
* Relacionamentos OneToMany
* Relacionamentos ManyToOne

### Validação

* Bean Validation
* Restrições de unicidade
* Campos obrigatórios

### Organização

* Separação de responsabilidades
* Modelagem de domínio
* Estrutura escalável

---

## ▶️ Como Executar

### Pré-requisitos

* Java 17+
* Maven
* PostgreSQL

### Clonar o Projeto

```bash
git clone <url-do-repositorio>
```

### Configurar Banco de Dados

Ajustar as configurações do banco PostgreSQL no arquivo:

```properties
application.properties
```

### Executar

```bash
mvn spring-boot:run
```

---

## 🎯 Objetivo do Projeto

Desenvolver uma solução Back-End para clínicas de pequeno e médio porte, aplicando conceitos de gestão de pacientes, consultas e profissionais da saúde em um ambiente realista.

O projeto foi criado para aprofundar conhecimentos em Spring Boot, PostgreSQL, JPA/Hibernate e modelagem de sistemas corporativos.

---

## 🚀 Melhorias Futuras

* Spring Security
* JWT
* DTOs
* Tratamento Global de Exceções
* Swagger/OpenAPI
* Docker
* Testes Automatizados
* Prontuário Eletrônico
* Controle de Exames
* Relatórios Gerenciais

---

## 👨‍💻 Autor

André Felipe

Estudante de Análise e Desenvolvimento de Sistemas, desenvolvedor Back-End Java.

# Springbank

Mini sistema bancário construído com Java e Spring Boot, como projeto de portfólio para aprendizado prático de backend, arquitetura em camadas e boas práticas de mercado.

## Objetivo

Simular as operações essenciais de um banco — criação de contas, depósito, saque, transferência e histórico de transações — usando um código simples, porém alinhado a práticas reais de desenvolvimento (arquitetura em camadas, segurança de credenciais, versionamento semântico, testes).

## Stack

- **Linguagem:** Java 17
- **Framework:** Spring Boot 4
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de dados:** MySQL 8.0 (containerizado via Docker)
- **Build:** Maven
- **Boilerplate:** Lombok
- **IDE:** IntelliJ IDEA
- **Cliente SQL:** PopSQL

## Arquitetura

O projeto segue uma separação em camadas, cada uma com uma responsabilidade única:

```
model       → entidades JPA (representação das tabelas)
repository  → interfaces Spring Data JPA (acesso ao banco)
service     → regras de negócio
controller  → endpoints REST (porta de entrada HTTP)
dto         → objetos de transporte de dados (entrada/saída da API)
```

Uma requisição percorre sempre o mesmo caminho: `Controller → Service → Repository → Model`. Nenhuma camada "pula" a camada abaixo dela.

## Estado atual do projeto

### Concluído

- **Infraestrutura**
  - MySQL 8.0 rodando via Docker (porta local `3307`)
  - Configuração via `application.yml`, com perfis Spring separando configuração base (versionada) de credenciais reais (`application-local.yml`, fora do Git)
  - Credenciais referenciadas via variáveis de ambiente (`${DB_USERNAME}`, `${DB_PASSWORD}`), nunca em texto puro no repositório
  - `.gitignore` configurado para proteger arquivos sensíveis

- **Domínio (`model`)**
  - `StatusConta` (enum): `ATIVA`, `BLOQUEADA`, `ENCERRADA`
  - `Conta` (entidade JPA): `id`, `numeroConta` (único), `nomeTitular`, `saldo` (`BigDecimal`, para evitar erros de arredondamento), `dataCriacao` (imutável), `dataEncerramento`, `status`
  - Setters aplicados seletivamente, protegendo campos que não deveriam ser alterados livremente (`id`, `saldo`, `dataCriacao`)

- **Persistência (`repository`)**
  - `ContaRepository`, estendendo `JpaRepository<Conta, Long>`

- **Regras de negócio (`service`)**
  - `ContaService.criarConta(nomeTitular, saldo)`: cria uma conta nova, gera um número de conta aleatório de 6 dígitos e persiste no banco

- **API REST (`controller` + `dto`)**
  - `ContaRequestDTO`: objeto de entrada para criação de conta, desacoplado da entidade
  - `POST /contas`: cria uma nova conta a partir de `nomeTitular` e `saldo`, retornando a conta criada (com `id`, `numeroConta`, `dataCriacao` e `status` preenchidos automaticamente)
  - Endpoint testado manualmente via Postman, com persistência confirmada no MySQL via PopSQL

### Em andamento / próximos passos imediatos

- `GET /contas/{id}` — buscar uma conta específica
- `GET /contas` — listar todas as contas

## Intenções futuras

- **Operações bancárias centrais**
  - Depósito
  - Saque (com validação de saldo)
  - Transferência entre contas
  - Histórico de transações (nova entidade `Transacao`)

- **Qualidade e robustez**
  - Validação de entrada com Bean Validation (`@NotNull`, etc.)
  - Tratamento de exceções (respostas de erro padronizadas)
  - Testes automatizados (JUnit)

- **Infraestrutura e entrega**
  - Deploy na AWS
  - Documentação da API (ex: Swagger/OpenAPI)

- **Possível expansão**
  - Front-end simples consumindo a API (se houver tempo/interesse após o core estar sólido)

## Convenções do projeto

- Commits seguem [Conventional Commits](https://www.conventionalcommits.org/) (`feat:`, `fix:`, `chore:`, etc.)
- Regras de negócio ficam isoladas na camada `service` — o `controller` nunca acessa o `repository` diretamente
- DTOs são usados para toda entrada/saída da API, evitando expor a estrutura interna das entidades

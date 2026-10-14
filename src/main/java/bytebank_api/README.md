# ByteBank API — Java & Spring Boot

API REST bancária desenvolvida em Java com Spring Boot para o desafio **Explorando Padrões de Projetos na Prática com Java**, da Digital Innovation One (DIO).

## Sobre o projeto

O ByteBank API simula operações bancárias por meio de endpoints HTTP, permitindo consultar o saldo de uma conta, realizar depósitos e efetuar saques.

## Tecnologias utilizadas

- Java 17
- Spring Boot 4
- Gradle
- Spring Web MVC
- API REST

## Design Patterns e conceitos

**Singleton:** o Spring gerencia o serviço bancário como um bean singleton por padrão.

**Facade simplificada:** a classe `ContaService` centraliza o acesso às operações bancárias.

**Injeção de dependência:** o Spring injeta `ContaService` no controller.

**Encapsulamento:** o saldo é mantido como atributo privado.

## Funcionalidades

- Consulta de saldo
- Depósitos
- Saques
- Validação de valores
- Tratamento de saldo insuficiente
- Respostas JSON

## Endpoints

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/conta/saldo` | Consultar saldo |
| POST | `/api/conta/depositar` | Realizar depósito |
| POST | `/api/conta/sacar` | Realizar saque |

### Exemplo de requisição

```json
{
  "valor": 150.00
}
```

## Executando o projeto

**Requisitos:** JDK 17 e conexão com a internet para baixar as dependências.

No Windows, execute:

```powershell
.\gradlew.bat bootRun
```

A API estará disponível em:

`http://localhost:8080/api/conta/saldo`

## Observações

Este projeto é educacional. O saldo é armazenado em memória, começa em R$ 1.000,00 e é reiniciado quando a aplicação é encerrada.

Não utiliza autenticação nem persistência em banco de dados.

## Autor

Murilo Oliveira Gomes

Projeto desenvolvido para aprendizado em Java, Spring Boot, APIs REST e padrões de projeto.
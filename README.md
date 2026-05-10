# 🏦 Bank App

![Java](https://img.shields.io/badge/Java-21-orange)
![Gradle](https://img.shields.io/badge/Gradle-8.x-02303A)
![Lombok](https://img.shields.io/badge/Lombok-1.18.42-bc4521)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![License](https://img.shields.io/badge/license-MIT-green)

> Aplicação de console em Java que simula operações bancárias: contas, depósitos, transferências, PIX e investimentos.

## Descrição

O Bank App é um projeto de estudo desenvolvido para praticar **Programação Orientada a Objetos**, **tratamento de exceções**, **uso de Streams** e **boas práticas em Java moderno (Java 21)**.

A interação acontece via terminal, com menus que permitem ao usuário gerenciar contas correntes e poupança, registrar chaves PIX, transferir valores e simular investimentos.

## Status do Projeto

🚧 Em desenvolvimento — funcionalidades principais implementadas, ajustes e novas features em andamento.

## Funcionalidades

- Criação de contas correntes e poupança
- Depósitos e saques
- Transferências entre contas
- Registro de chaves PIX e transferências via PIX
- Aplicação em investimentos
- Listagem de contas e histórico de transações

## Tecnologias

- **Java 21**
- **Gradle** (Kotlin DSL)
- **Lombok 1.18.42**

## Como Instalar e Rodar

### Pré-requisitos

- [Java 21](https://www.oracle.com/java/technologies/downloads/) ou superior
- [Gradle](https://gradle.org/install/) (opcional — o wrapper `gradlew` está incluído)
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

### Passos

```bash
# Clone o repositório
git clone https://github.com/AllanGiaretta26/bank-prj.git

# Acesse a pasta do projeto
cd bank-prj

# Compile o projeto
./gradlew build
```

### Executando

Abra o projeto na sua IDE, localize a classe `Main` em `src/main/java/br/com/bank/app/Main.java` e execute **Run → Run 'Main'**.

Alternativamente, via linha de comando:

```bash
./gradlew run
```

## Estrutura do Projeto

```
src/
└── main/
    └── java/
        └── br/com/bank/
            ├── app/
            │   └── Main.java
            ├── entity/
            │   ├── Account.java
            │   ├── CheckingAccount.java
            │   ├── SavingAccount.java
            │   ├── Transaction.java
            │   ├── Investment.java
            │   └── PixKey.java
            ├── service/
            │   ├── BankAccount.java
            │   ├── BankPix.java
            │   └── BankInvestment.java
            └── util/
                ├── AccountTypeEnum.java
                ├── TransactionTypeEnum.java
                ├── InvestmentTypeEnum.java
                └── exception/
                    ├── AccountNumberAlreadyRegisteredException.java
                    ├── AccountNumberNotFoundException.java
                    ├── PixKeyAlredyRegisteredException.java
                    ├── PixKeyNotFoundException.java
                    └── SufficientBalanceNotFoundException.java
```

## Conceitos Aplicados

- Abstração e herança (`Account` → `CheckingAccount`, `SavingAccount`)
- Encapsulamento
- Enumerações (`AccountTypeEnum`, `TransactionTypeEnum`, `InvestmentTypeEnum`)
- Exceções customizadas
- Java Streams e `Optional`
- Uso de `BigDecimal` para operações monetárias

## Licença

Distribuído sob a licença MIT.

---

Desenvolvido por **[Allan Giaretta](https://github.com/AllanGiaretta26)**.

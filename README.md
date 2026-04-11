# 🏦 Bank App

Aplicação em Java que simula operações bancárias como criação de contas, depósitos, transferências, PIX e investimentos.

## 📋 Descrição

O projeto foi desenvolvido com foco em praticar conceitos de **POO (Programação Orientada a Objetos)**, **tratamento de exceções**, **uso de Streams** e **boas práticas em Java moderno (Java 21)**.  

As operações são realizadas via terminal, com menus interativos para o usuário.

## ⚙️ Funcionalidades

- Criar contas correntes e poupança  
- Realizar depósitos e saques  
- Transferências entre contas  
- Registrar e transferir via PIX  
- Fazer investimentos  
- Listar contas e transações

## ▶️ Execução
Abra a classe Main e clique em Run → Run 'Main'.

## 🧩 Estrutura do Projeto
```
rc/
└── main/
└── java/
└── br/com/bank/
├── app/
│ └── Main.java
├── entity/
│ ├── Account.java
│ ├── CheckingAccount.java
│ ├── SavingAccount.java
│ ├── Transaction.java
│ ├── Investment.java
│ └── PixKey.java
├── service/
│ ├── BankAccount.java
│ ├── BankPix.java
│ └── BankInvestment.java
└── util/
├── enums
└── exceções personalizadas
```

## 🧠 Conceitos Utilizados

- Abstração e herança (`Account` → `CheckingAccount`, `SavingAccount`)  
- Encapsulamento  
- Enumerações (`AccountTypeEnum`, `TransactionTypeEnum`, `InvestmentTypeEnum`)  
- Exceções customizadas  
- Java Streams e Optionals  
- Imutabilidade e boas práticas com `BigDecimal`  

## 🚀 Tecnologias

- **Java 21**
- **Gradle**
- **Lombok 1.18.42**

## 🧑‍💻 Autor
Desenvolvido por Allan Giaretta

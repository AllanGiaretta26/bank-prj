package br.com.bank.app;

import br.com.bank.entity.Account;
import br.com.bank.entity.CheckingAccount;
import br.com.bank.entity.SavingAccount;
import br.com.bank.service.BankAccount;
import br.com.bank.service.BankInvestment;
import br.com.bank.service.BankPix;
import br.com.bank.util.AccountTypeEnum;
import br.com.bank.util.InvestmentTypeEnum;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static final BankAccount bankAccount = new BankAccount();
    private static final BankInvestment bankInvestment = new BankInvestment(bankAccount);
    private static final BankPix bankPix = new BankPix(bankAccount);

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var run = true;
        while (run) {
            printMenu();
            var option = scanner.nextLine();
            switch (option) {
                case "1" -> createAccount();
                case "2" -> deposit();
                case "3" -> withdraw();
                case "4" -> transfer();
                case "5" -> invest();
                case "6" -> registerPixKey();
                case "7" -> transferByPix();
                case "8" -> listAccounts();
                case "9" -> listPixKeys();
                case "10" -> listInvestments();
                case "11" -> listTransactions();
                case "0" -> {
                    run = false;
                    System.out.println("Fim do Programa");
                }
                default -> System.out.println("Opção invalida");
            }
        }
    }

    private static void printMenu() {
        System.out.println("======== Banco Menu ========");
        System.out.println("1 - Criar Conta");
        System.out.println("2 - Depositar");
        System.out.println("3 - Sacar");
        System.out.println("4 - Transferir");
        System.out.println("5 - Investir");
        System.out.println("6 - Registrar Pix");
        System.out.println("7 - Transferir Pix");
        System.out.println("8 - Listar Contas");
        System.out.println("9 - Listar Pix");
        System.out.println("10 - Listar investimentos");
        System.out.println("11 - Listar Transações");
        System.out.println("0 - Sair");
        System.out.print("Escolha: \n");
    }

    private static void createAccount() {
        System.out.println("Numero da Conta:");
        var number = scanner.nextLine();
        System.out.println("Nome do Cliente:");
        var name = scanner.nextLine();
        System.out.println("Tipo da Conta ('C' para corrente, 'S' para poupança)");
        var type = scanner.nextLine();
        var balance = BigDecimal.ZERO;
        Account account = getAccount(type, name, number, balance);
        if (account == null) {
            System.out.println("Tipo de conta inválido. Use 'C' ou 'S'.\n");
            return;
        }
        try {
            bankAccount.createAccount(account);
            System.out.println("Conta criada: " + number + "\n");
        }catch (Exception ex) {
            System.out.println("Houve um erro ao criar conta: " + ex.getMessage());
        }
    }

    private static Account getAccount(String type, String name, String number, BigDecimal balance) {
        return type.equals("C") || type.equals("c")
                ? new CheckingAccount(AccountTypeEnum.CHECKING_ACCOUNT, name, number, balance)
                : type.equals("S") || type.equals("s")
                ? new SavingAccount(AccountTypeEnum.SAVINGS_ACCOUNT, name, number, balance)
                : null;
    }

    private static void deposit() {
        System.out.println("Numero da Conta:");
        var number = scanner.nextLine();
        System.out.println("Valor para ser depositado");
        var amountInput = scanner.nextLine();
        try{
            var amount = new BigDecimal(amountInput);
            bankAccount.deposit(number, amount);
            System.out.println("Depositado com sucesso");
        }catch (Exception ex){
            System.out.println("Valor invalido ou erro: " + ex.getMessage());
        }
    }

    private static void withdraw() {
        System.out.println("Numero da Conta:");
        var number = scanner.nextLine();
        System.out.println("Valor para ser sacado");
        var amountInput = scanner.nextLine();
        try{
            var amount = new BigDecimal(amountInput);
            bankAccount.withdraw(number, amount);
            System.out.println("Sacado com sucesso");
        }catch (Exception ex){
            System.out.println("Valor invalido ou erro: " + ex.getMessage());
        }
    }

    private static void transfer() {
        System.out.println("Numero da conta que deseja realizar transferência (origem):");
        var from = scanner.nextLine();
        System.out.println("Numero da conta que sera o destino:");
        var to = scanner.nextLine();
        System.out.println("Valor para transferência:");
        var amountInput = scanner.nextLine();
        try{
            var amount = new BigDecimal(amountInput);
            bankAccount.transfer(from, to, amount);
            System.out.println("Transferência com sucesso");
        }catch (Exception ex){
            System.out.println("Valor invalido. Tente novamente: " + ex.getMessage());
        }
    }

    private static void invest() {
        System.out.println("Numero da Conta:");
        var number = scanner.nextLine();
        System.out.println("Valor para investir:");
        var amountInput = scanner.nextLine();
        System.out.println("Escolha uma opção para investir (C - CDB, T - Tesouro, B - Bolsa de Valores");
        var type = scanner.nextLine();
        var typeEnum = investmentTypeEnum(type);
        if (typeEnum == null) {
            System.out.println("Tipo de investimento inválido. Use 'C' ,'B' ou 'T'.\n");
            return;
        }
        try {
            var amount = new BigDecimal(amountInput);
            bankInvestment.invest(number, amount, typeEnum);
            System.out.println("Investimento realizado com sucesso");
        } catch (Exception ex) {
            System.out.println("Valor inválido ou erro: " + ex.getMessage());
        }
    }

    private static InvestmentTypeEnum investmentTypeEnum(String type) {
        return type.equals("C") || type.equals("c")
                ? InvestmentTypeEnum.CDB
                : type.equals("T") || type.equals("t")
                ? InvestmentTypeEnum.TESOURO
                : type.equals("B") || type.equals("b")
                ? InvestmentTypeEnum.BOLSA_DE_VALORES
                : null;
    }

    private static void registerPixKey() {
        System.out.println("Numero da Conta:");
        var number = scanner.nextLine();
        System.out.println("Digite um chave pix:");
        var pixKey = scanner.nextLine();
        try {
            bankPix.registerPixKey(pixKey, number);
            System.out.println("Pix registrado com sucesso");
        }catch (Exception ex){
            System.out.println("Valor invalido ou erro: " + ex.getMessage());
        }
    }

    private static void transferByPix(){
        System.out.println("Chave pix que deseja realizar transferência (origem):");
        var from = scanner.nextLine();
        System.out.println("Chave pix que sera o destino:");
        var to = scanner.nextLine();
        System.out.println("Valor para transferência:");
        var amountInput = scanner.nextLine();
        try {
            var amount = new BigDecimal(amountInput);
            bankPix.transferByPix(from, to, amount);
            System.out.println("Transferencia realizada com sucesso");
        }catch (Exception ex){
            System.out.println("Valor invalido ou erro: " + ex.getMessage());
        }
    }

    private static void listTransactions() {
        var listTransactions = bankAccount.listTransactions();
        System.out.println(listTransactions);
    }

    private static void listInvestments() {
        var listInvestments = bankInvestment.listInvestments();
        System.out.println(listInvestments);
    }

    private static void listPixKeys() {
        var listPixKeys = bankPix.listPixKeys();
        System.out.println(listPixKeys);
    }

    private static void listAccounts() {
        var listAccounts = bankAccount.listAccounts();
        System.out.println(listAccounts);
    }

}

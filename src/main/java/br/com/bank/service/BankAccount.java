package br.com.bank.service;

import br.com.bank.entity.Account;
import br.com.bank.entity.Transaction;
import br.com.bank.util.TransactionTypeEnum;
import br.com.bank.util.exception.AccountNumberAlreadyRegisteredException;
import br.com.bank.util.exception.AccountNumberNotFoundException;
import br.com.bank.util.exception.SufficientBalanceNotFoundException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private final List<Account> accounts = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    public void createAccount(Account account) {
        var exists = accounts.stream()
                .anyMatch(a -> a.getAccountNumber().equals(account.getAccountNumber()));
        if (exists) {
            throw new AccountNumberAlreadyRegisteredException("Já existe uma conta com o número: " + account.getAccountNumber());
        }
        accounts.add(account);
    }

    public List<Account> listAccounts() {
        return List.copyOf(accounts);
    }

    public Account findByNumberOrThrow(String number) {
        return accounts.stream()
                .filter(a -> a.getAccountNumber().equals(number))
                .findFirst()
                .orElseThrow(() -> new AccountNumberNotFoundException("Conta não encontrada: " + number));
    }

    public void deposit(String number, BigDecimal amount) {
        var acc = findByNumberOrThrow(number);
        validateAmount(amount);
        acc.setBalance(acc.getBalance().add(amount));
        transactions.add(new Transaction(TransactionTypeEnum.DEPOSIT, amount, OffsetDateTime.now(), number));
    }

    public void withdraw(String number, BigDecimal amount) {
        var acc = findByNumberOrThrow(number);
        validateAmount(amount);
        if (acc.getBalance().compareTo(amount) < 0) {
            throw new SufficientBalanceNotFoundException("Saldo insuficiente: " + number);
        }
        acc.setBalance(acc.getBalance().subtract(amount));
        transactions.add(new Transaction(TransactionTypeEnum.WITHDRAW, amount, OffsetDateTime.now(), number));
    }

    public void transfer(String from, String to, BigDecimal amount) {
        var aFrom = findByNumberOrThrow(from);
        var aTo = findByNumberOrThrow(to);
        validateAmount(amount);
        if (aFrom.getBalance().compareTo(amount) < 0) {
            throw new SufficientBalanceNotFoundException("Saldo insuficiente para transferencia: " + from);
        }
        aFrom.setBalance(aFrom.getBalance().subtract(amount));
        aTo.setBalance(aTo.getBalance().add(amount));
        transactions.add(new Transaction(TransactionTypeEnum.TRANSFER_SENT, amount, OffsetDateTime.now(), from));
        transactions.add(new Transaction(TransactionTypeEnum.TRANSFER_RECEIVED, amount, OffsetDateTime.now(), to));
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> listTransactions() {
        return List.copyOf(transactions);
    }

    public void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido: " + amount);
        }
    }
}

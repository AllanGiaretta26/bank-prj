package br.com.bank.service;

import br.com.bank.entity.Investment;
import br.com.bank.util.InvestmentTypeEnum;
import br.com.bank.util.TransactionTypeEnum;
import br.com.bank.util.exception.SufficientBalanceNotFoundException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankInvestment {

    private final BankAccount bankAccount;
    private final List<Investment> investments = new ArrayList<>();

    public BankInvestment(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void invest(String accountNumber, BigDecimal amount, InvestmentTypeEnum type) {
        bankAccount.validateAmount(amount);
        var acc = bankAccount.findByNumberOrThrow(accountNumber);
        if (acc.getBalance().compareTo(amount) < 0) {
            throw new SufficientBalanceNotFoundException("Saldo insuficiente para investir: " + accountNumber);
        }
        acc.setBalance(acc.getBalance().subtract(amount));
        investments.add(new Investment(amount, OffsetDateTime.now(), type));
        bankAccount.addTransaction(TransactionTypeEnum.INVESTMENT, amount, accountNumber);
    }

    public List<Investment> listInvestments() {
        return List.copyOf(investments);
    }
}

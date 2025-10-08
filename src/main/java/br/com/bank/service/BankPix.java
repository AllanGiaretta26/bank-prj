package br.com.bank.service;

import br.com.bank.entity.PixKey;
import br.com.bank.entity.Transaction;
import br.com.bank.util.TransactionTypeEnum;
import br.com.bank.util.exception.PixKeyAlredyRegisteredException;
import br.com.bank.util.exception.PixKeyNotFoundException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankPix {

    private final List<PixKey> pixKeys = new ArrayList<>();
    private final BankAccount bankAccount;

    public BankPix(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void registerPixKey(String key, String accountNumber) {
        if (pixKeys.stream().anyMatch(k -> k.getKey().equals(key))) {
            throw new PixKeyAlredyRegisteredException("Chave PIX já cadastrada: " + key);
        }
        bankAccount.findByNumberOrThrow(accountNumber);
        pixKeys.add(new PixKey(key, accountNumber));
    }

    public void transferByPix(String pixKeyFrom, String pixKeyTo, BigDecimal amount) {
        bankAccount.validateAmount(amount);
        var fromAccountNumber = findByNumberAccountPixKeyOrThrow(pixKeyFrom);
        var toAccount = findByNumberAccountPixKeyOrThrow(pixKeyTo);
        var from = bankAccount.findByNumberOrThrow(fromAccountNumber);
        var to = bankAccount.findByNumberOrThrow(toAccount);
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));
        bankAccount.addTransaction(TransactionTypeEnum.PIX_SENT, amount, fromAccountNumber);
        bankAccount.addTransaction(TransactionTypeEnum.PIX_RECEIVED, amount, toAccount);
    }

    public List<PixKey> listPixKeys() {
        return List.copyOf(pixKeys);
    }

    private String findByNumberAccountPixKeyOrThrow(String pixKey) {
        var pix = pixKeys.stream()
                .filter(p -> p.getKey().equals(pixKey))
                .findFirst()
                .orElseThrow(() -> new PixKeyNotFoundException("Chave PIX nao encontrada: " + pixKey));
        return pix.getAccountNumber();
    }
}

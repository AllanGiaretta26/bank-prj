package br.com.bank.util.exception;

public class AccountNumberNotFoundException extends RuntimeException {
    public AccountNumberNotFoundException(String message) {

        super(message);

    }
}

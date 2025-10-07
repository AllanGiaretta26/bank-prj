package br.com.bank.util.exception;

public class AccountNumberAlreadyRegisteredException extends RuntimeException {
    public AccountNumberAlreadyRegisteredException(String message) {

        super(message);

    }
}

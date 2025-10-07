package br.com.bank.util.exception;

public class SufficientBalanceNotFoundException extends RuntimeException {
  public SufficientBalanceNotFoundException(String message) {

      super(message);

  }
}

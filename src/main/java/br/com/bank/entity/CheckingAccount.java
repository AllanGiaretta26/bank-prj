package br.com.bank.entity;

import br.com.bank.util.AccountTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class CheckingAccount extends Account {

    public CheckingAccount(AccountTypeEnum type, String name, String accountNumber, BigDecimal balance) {
        super(type, name, accountNumber, balance);
    }

    @Override
    public String toString() {
        return String.format("%s: %s | Titular: %s | Saldo: R$ %.2f",
                getType(), getAccountNumber(), getName(), getBalance());
    }
}

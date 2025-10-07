package br.com.bank.entity;

import br.com.bank.util.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Account {

    private AccountTypeEnum type;

    private String name;

    private String accountNumber;

    private BigDecimal balance = BigDecimal.ZERO;

    public abstract String toString();

}

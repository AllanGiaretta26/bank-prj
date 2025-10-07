package br.com.bank.entity;

import br.com.bank.util.TransactionTypeEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record Transaction(

        TransactionTypeEnum type,
        BigDecimal amount,
        OffsetDateTime date,
        String accountNumber

)
{
}

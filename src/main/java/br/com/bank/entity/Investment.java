package br.com.bank.entity;

import br.com.bank.util.InvestmentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Investment {

    private BigDecimal value;

    private OffsetDateTime date;

    private InvestmentTypeEnum type;

}

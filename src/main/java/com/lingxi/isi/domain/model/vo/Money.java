package com.lingxi.isi.domain.model.vo;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

// 货币值对象
@Value
public class Money {
   
    @NonNull
    BigDecimal amount;
    @NonNull
    Currency currency;

    // 确保货币值不能为负
    public Money(BigDecimal amount, Currency currency) {
   
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
   
            throw new IllegalArgumentException("金额不能为负数");
        }
        this.amount = amount;
        this.currency = currency;
    }

    // 金额加法操作
    public Money add(Money other) {
   
        if (!this.currency.equals(other.currency)) {
   
            throw new IllegalArgumentException("货币类型不匹配");
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }
}

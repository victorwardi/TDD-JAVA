package guru.springframework;

import java.util.Objects;

public class Money implements Expression {

    final int amount;

    private final Currency currency;

    Currency currency(){
        return this.currency;
    }
    public Money(int amount, Currency  currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, Currency.DOLLAR );
    }

    public static Money franc(int amount) {
        return new Money(amount,  Currency.FRANC );
    }

    public static Money real(int amount) {
        return new Money(amount, Currency.REAL);
    }


    @Override
    public Expression times(int multiplier) {
        return new Money (this.amount * multiplier, this.currency);
    }

    @Override
    public Expression plus(Expression addend){
        return new Sum(this, addend);
    }

    @Override
    public Money convert(Bank bank, Currency toCurrency) {
        return new Money(amount/bank.rate(this.currency, toCurrency), toCurrency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Money money = (Money) o;
        return amount == money.amount && currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}



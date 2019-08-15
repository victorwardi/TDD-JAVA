package guru.springframework;

public interface Expression {
    Money convert(Bank bank, Currency toCurrency);

    Expression plus(Expression addend);

    Expression times(int multiplier);
}

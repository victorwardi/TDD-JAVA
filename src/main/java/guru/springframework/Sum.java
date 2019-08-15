package guru.springframework;

public class Sum implements Expression{

    final Expression augmend;
    final Expression addmend;

    public Sum(Expression augmend, Expression addmend) {
        this.augmend = augmend;
        this.addmend = addmend;
    }

    @Override
    public Money convert(Bank bank, Currency  toCurrency){
        int amount = augmend.convert(bank, toCurrency).amount + addmend.convert(bank, toCurrency).amount;
        return  new Money (amount, toCurrency);
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addmend);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(augmend.times(multiplier), augmend.times(multiplier));
    }
}

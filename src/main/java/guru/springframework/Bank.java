package guru.springframework;

import java.util.HashMap;

class Bank {

    private final HashMap<Pair, Integer> rates = new HashMap<>();


    public Money convert(Expression source, Currency toCurrency) {
        return source.convert(this, toCurrency);
    }

    public void addRate(Currency from, Currency to, int rate) {
        rates.put(new Pair(from, to), rate);
    }

    public int rate(Currency from, Currency to) {

        if(from.equals(to)){
            return 1;
        }

        return rates.get(new Pair(from, to));
    }
}

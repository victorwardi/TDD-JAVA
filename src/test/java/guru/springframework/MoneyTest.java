package guru.springframework;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void testMultiplication() {
        Money fiveDollar = Money.dollar(5);
        assertEquals(Money.dollar(10), fiveDollar.times(2));
        assertEquals(Money.dollar(15), fiveDollar.times(3));

        Money fiveFranc = Money.franc(5);
        assertEquals(Money.franc(10), fiveFranc.times(2));
        assertEquals(Money.franc(15), fiveFranc.times(3));

        Money fiveReal = Money.real(5);
        assertEquals(Money.real(10), fiveReal.times(2));
        assertEquals(Money.real(15), fiveReal.times(3));
    }

    @Test
    void testEquality() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(10));

        assertEquals(Money.franc(5), Money.franc(5));
        assertNotEquals(Money.franc(5), Money.franc(10));

        assertNotEquals(Money.franc(5), Money.real(5));

    }

    @Test
    void testSimpleAddition() {
        Money fiveDollar = Money.dollar(5);
        Expression sum = fiveDollar.plus(fiveDollar);
        Bank bank = new Bank();
        Money converted = bank.convert(sum, Currency.DOLLAR);
        assertEquals(Money.dollar(10), converted);
    }

    @Test
    void testPlusReturnsSum() {
        Money fiveDollar = Money.dollar(5);
        Expression result = fiveDollar.plus(fiveDollar);
        Sum sum = (Sum) result;
        assertEquals(fiveDollar, sum.augmend);
        assertEquals(fiveDollar, sum.addmend);
    }

    @Test
    void testConvertSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.convert(sum, Currency.DOLLAR);
        assertEquals(Money.dollar(7), result);
    }

    @Test
    void testConvertMoneyDifferentCurrency() {

        Bank bank = new Bank();
        bank.addRate(Currency.REAL, Currency.DOLLAR, 4);
        Money result = bank.convert(Money.real(4), Currency.DOLLAR);
        assertEquals(Money.dollar(1), result);

    }


    @Test
    void testIdentityRate() {
        assertEquals(1, new Bank().rate(Currency.DOLLAR, Currency.DOLLAR));
        assertEquals(1, new Bank().rate(Currency.REAL, Currency.REAL));
    }

    @Test
    void testCurrency() {
        assertEquals(Currency.DOLLAR, Money.dollar(1).currency());
        assertEquals(Currency.FRANC, Money.franc(1).currency());
    }


    @Test
    void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression twentyReais = Money.real(20);

        Bank bank = new Bank();
        bank.addRate(Currency.REAL, Currency.DOLLAR, 4);
        Money result = bank.convert(fiveBucks.plus(twentyReais), Currency.DOLLAR);
        assertEquals(Money.dollar(10), result);
    }

    @Test
    void testSumPlusMoney() {
        Expression fiveBucks = Money.dollar(5);
        Expression twentyReais = Money.real(20);

        Bank bank = new Bank();
        bank.addRate(Currency.REAL, Currency.DOLLAR, 4);

        Expression sum = new Sum(fiveBucks, twentyReais).plus(fiveBucks);
        Money result = bank.convert(sum, Currency.DOLLAR);
        assertEquals(Money.dollar(15), result);
    }
    @Test
    void testSumTimes() {
        Expression fiveBucks = Money.dollar(5);
        Expression twentyReais = Money.real(20);

        Bank bank = new Bank();
        bank.addRate(Currency.REAL, Currency.DOLLAR, 4);

        Expression sum = new Sum(fiveBucks, twentyReais).times(2);
        Money result = bank.convert(sum, Currency.DOLLAR);
        assertEquals(Money.dollar(20), result);
    }
}

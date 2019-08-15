package guru.springframework;

public enum Currency {

    DOLLAR("USD"), FRANC("CHF"), REAL("BRL");

    private final String code;

    Currency(String code) {
        this.code = code;
    }
}

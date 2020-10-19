package be.vdab.frida.domain;

import java.math.BigDecimal;

public class Snack {
    private final long id;
    private final String naam;
    private final BigDecimal prijs;

    public Snack(long id, String naam, BigDecimal prijs) {
        this.id = id;
        this.naam = naam;
        this.prijs = prijs;
    }
}

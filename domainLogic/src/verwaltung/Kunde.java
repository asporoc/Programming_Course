package verwaltung;

import administration.Customer;

import java.io.Serializable;

public class Kunde implements Customer, Serializable {
    private String name;
    public Kunde(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

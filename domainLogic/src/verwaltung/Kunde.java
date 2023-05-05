package verwaltung;

import administration.Customer;

public class Kunde implements Customer {
    private String name;
    public Kunde(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

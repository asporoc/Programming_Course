package verwaltung;

import administration.Customer;
import cargos.EinfuegenObject;

import java.io.Serializable;

public class Kunde implements Customer, Serializable, EinfuegenObject {
    private final String name;
    public Kunde(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

package cargos;

import java.io.Serializable;

public class UnitisedCargo_Beschreibung implements Serializable {
    private final boolean fragile;

    public UnitisedCargo_Beschreibung(boolean fragile) {
        this.fragile = fragile;
    }

    public boolean isFragile(){
        return fragile;
    }
}

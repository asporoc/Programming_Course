package cargos;

import cargo.UnitisedCargo;

import java.io.Serializable;

public class UnitisedCargo_Beschreibung implements Serializable {
    private boolean fragile;

    public UnitisedCargo_Beschreibung(boolean fragile) {
        this.fragile = fragile;
    }

    public boolean isFragile(){
        return fragile;
    }
}

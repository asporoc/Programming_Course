package cargos;

import cargo.UnitisedCargo;

public class UnitisedCargo_Beschreibung {
    private boolean fragile;

    public UnitisedCargo_Beschreibung(boolean fragile) {
        this.fragile = fragile;
    }

    public boolean isFragile(){
        return fragile;
    }
}

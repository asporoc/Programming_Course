package cargos;

import cargo.LiquidBulkCargo;

import java.io.Serializable;

public class LiquidBulkCargo_Beschreibung implements Serializable {
    private boolean pressurized;
    public LiquidBulkCargo_Beschreibung(boolean pressurized){
        this.pressurized=pressurized;
    }
    public boolean isPressurized(){
        return pressurized;
    }

}

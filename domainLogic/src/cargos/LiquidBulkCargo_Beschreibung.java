package cargos;

import cargo.LiquidBulkCargo;

public class LiquidBulkCargo_Beschreibung{
    private boolean pressurized;
    public LiquidBulkCargo_Beschreibung(boolean pressurized){
        this.pressurized=pressurized;
    }
    public boolean isPressurized(){
        return pressurized;
    }
}

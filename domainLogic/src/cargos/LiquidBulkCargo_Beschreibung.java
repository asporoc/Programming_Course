package cargos;

import java.io.Serializable;

public class LiquidBulkCargo_Beschreibung implements Serializable {
    private final boolean pressurized;
    public LiquidBulkCargo_Beschreibung(boolean pressurized){
        this.pressurized=pressurized;
    }
    public boolean isPressurized(){
        return pressurized;
    }

}

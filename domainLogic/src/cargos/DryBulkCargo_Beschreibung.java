package cargos;

import java.io.Serializable;

public class DryBulkCargo_Beschreibung implements Serializable {
    private final int grainSize;
    public DryBulkCargo_Beschreibung(int grainSize){
        this.grainSize=grainSize;
    }
    public int getGrainSize(){
        return grainSize;
    }
}

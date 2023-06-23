package cargos;

import cargo.DryBulkCargo;

import java.io.Serializable;

public class DryBulkCargo_Beschreibung implements Serializable {
    private int grainSize;
    public DryBulkCargo_Beschreibung(int grainSize){
        this.grainSize=grainSize;
    }
    public int getGrainSize(){
        return grainSize;
    }
}

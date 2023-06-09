package cargos;

import administration.Storable;
import cargo.Cargo;

public interface storableCargo extends Storable,Cargo{
    public String cargoToString();

}

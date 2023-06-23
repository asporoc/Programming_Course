package cargos;

import cargo.Cargo;
import cargo.Hazard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class Cargo_Beschreibung implements Cargo, Serializable {
    BigDecimal value;
    Collection<Hazard> hazards;
    public Cargo_Beschreibung(BigDecimal value,Collection<Hazard> hazards){
        this.value=value;
        this.hazards=hazards;
    }
    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Collection<Hazard> getHazards() {
        return hazards;
    }
}

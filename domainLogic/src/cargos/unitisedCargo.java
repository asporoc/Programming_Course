package cargos;

import administration.Customer;
import cargo.Hazard;
import cargo.UnitisedCargo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class unitisedCargo implements UnitisedCargo, storableCargo {
    @Override
    public Customer getOwner() {
        return null;
    }

    @Override
    public Duration getDurationOfStorage() {
        return null;
    }

    @Override
    public Date getLastInspectionDate() {
        return null;
    }

    @Override
    public int getStorageLocation() {
        return 0;
    }

    @Override
    public BigDecimal getValue() {
        return null;
    }

    @Override
    public Collection<Hazard> getHazards() {
        return null;
    }

    @Override
    public boolean isFragile() {
        return false;
    }
}

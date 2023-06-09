package cargos;

import administration.Customer;
import cargo.Hazard;
import cargo.LiquidBulkCargo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class liquidBulkCargo implements LiquidBulkCargo, storableCargo {
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
    public boolean isPressurized() {
        return false;
    }

    @Override
    public String cargoToString() {
        return null;
    }
}

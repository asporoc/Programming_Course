package cargos;

import administration.Customer;
import administration.Storable;
import cargo.Cargo;
import cargo.Hazard;
import cargo.LiquidBulkAndUnitisedCargo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class LiquidBulkAndUnitisedCargoImpl implements LiquidBulkAndUnitisedCargo, Cargo, Storable, storableCargo, Serializable {
    Cargo_Beschreibung cargoBeschreibung;
    Storable_Beschreibung storableBeschreibung;
    LiquidBulkCargo_Beschreibung liquidBulkCargoBeschreibung;
    private UnitisedCargo_Beschreibung unitisedCargoBeschreibung;

    public LiquidBulkAndUnitisedCargoImpl(Customer owner, BigDecimal value, Collection<Hazard> hazards, boolean fragile, boolean pressurized ){
        this.cargoBeschreibung = new Cargo_Beschreibung(value,hazards);
        this.storableBeschreibung = new Storable_Beschreibung(owner);
        this.liquidBulkCargoBeschreibung = new LiquidBulkCargo_Beschreibung(pressurized);
        this.unitisedCargoBeschreibung = new UnitisedCargo_Beschreibung(fragile);

    }
    @Override
    public Customer getOwner() {
        return storableBeschreibung.getOwner();
    }

    @Override
    public Duration getDurationOfStorage() {
        return storableBeschreibung.getDurationOfStorage();
    }

    @Override
    public Date getLastInspectionDate() {
        return storableBeschreibung.getLastInspectionDate();
    }

    @Override
    public int getStorageLocation() {
        return storableBeschreibung.getStorageLocation();
    }

    @Override
    public BigDecimal getValue() {
        return cargoBeschreibung.getValue();
    }

    @Override
    public Collection<Hazard> getHazards() {
        return cargoBeschreibung.getHazards();
    }

    @Override
    public boolean isPressurized() {
        return liquidBulkCargoBeschreibung.isPressurized();
    }

    @Override
    public boolean isFragile() {
        return unitisedCargoBeschreibung.isFragile();
    }
}

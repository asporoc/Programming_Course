package cargos;

import administration.Customer;
import administration.Storable;
import cargo.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class DryBulkAndUnitisedCargoImpl implements Cargo, Storable, DryBulkAndUnitisedCargo, storableCargo {
    Cargo_Beschreibung cargoBeschreibung;
    Storable_Beschreibung storableBeschreibung;
    DryBulkCargo_Beschreibung dryBulkCargoBeschreibung;
    private UnitisedCargo_Beschreibung unitisedCargoBeschreibung;
    public DryBulkAndUnitisedCargoImpl(Customer owner, BigDecimal value, Collection<Hazard> hazards, int grainSize, boolean fragile ){
        this.dryBulkCargoBeschreibung = new DryBulkCargo_Beschreibung(grainSize);
        this.cargoBeschreibung = new Cargo_Beschreibung(value);
        this.storableBeschreibung = new Storable_Beschreibung(owner);
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
    public int getGrainSize() {
        return dryBulkCargoBeschreibung.getGrainSize();
    }

    @Override
    public boolean isFragile() {
        return unitisedCargoBeschreibung.isFragile();
    }
    public void setStorageLocation(int location){
        storableBeschreibung.setStorageLocation(location);
    }
}

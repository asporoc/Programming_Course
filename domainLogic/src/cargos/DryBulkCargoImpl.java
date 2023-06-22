package cargos;

import administration.Customer;
import administration.Storable;
import cargo.Cargo;
import cargo.DryBulkCargo;
import cargo.Hazard;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class DryBulkCargoImpl implements Cargo, Storable, DryBulkCargo {
    Cargo_Beschreibung cargoBeschreibung;
    Storable_Beschreibung storableBeschreibung;
    DryBulkCargo_Beschreibung dryBulkCargoBeschreibung;
    public DryBulkCargoImpl(Customer owner, int storageLocation, BigDecimal value/*, Collection<Hazard> hazards*/, int grainSize ){
        this.dryBulkCargoBeschreibung = new DryBulkCargo_Beschreibung(grainSize);
        this.cargoBeschreibung = new Cargo_Beschreibung(value);
        this.storableBeschreibung = new Storable_Beschreibung(owner,storageLocation);
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
        return null;
    }

    @Override
    public int getGrainSize() {
        return dryBulkCargoBeschreibung.getGrainSize();
    }
}

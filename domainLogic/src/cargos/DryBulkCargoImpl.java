package cargos;

import administration.Customer;
import administration.Storable;
import cargo.Cargo;
import cargo.DryBulkCargo;
import cargo.Hazard;
import javafx.beans.property.IntegerProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class DryBulkCargoImpl implements Cargo, Storable, DryBulkCargo, storableCargo, Serializable {
    private StorableCargo_Beschreibung storableCargoBeschreibung;
    private Cargo_Beschreibung cargoBeschreibung;
    private Storable_Beschreibung storableBeschreibung;
    private DryBulkCargo_Beschreibung dryBulkCargoBeschreibung;
    public DryBulkCargoImpl(Customer owner, BigDecimal value, Collection<Hazard> hazards, int grainSize ){
        this.dryBulkCargoBeschreibung = new DryBulkCargo_Beschreibung(grainSize);
        this.cargoBeschreibung = new Cargo_Beschreibung(value, hazards);
        this.storableBeschreibung = new Storable_Beschreibung(owner);
        this.storableCargoBeschreibung = new StorableCargo_Beschreibung(owner,value,hazards);
    }

    @Override
    public Customer getOwner() {
        return storableCargoBeschreibung.getOwner();
    }

    @Override
    public Duration getDurationOfStorage() {
        return storableCargoBeschreibung.getDurationOfStorage();
    }

    @Override
    public Date getLastInspectionDate() {
        return storableCargoBeschreibung.getLastInspectionDate();
    }

    @Override
    public int getStorageLocation() {
        return storableCargoBeschreibung.getStorageLocation();
    }

    @Override
    public BigDecimal getValue() {
        return storableCargoBeschreibung.getValue();
    }

    @Override
    public Collection<Hazard> getHazards() {
        return storableCargoBeschreibung.getHazards();
    }

    @Override
    public int getGrainSize() {
        return dryBulkCargoBeschreibung.getGrainSize();
    }
    public void setStorageLocation(int location){
        storableCargoBeschreibung.setStorageLocation(location);
    }
    public void setLastInspectionDate(Date date){
        storableCargoBeschreibung.setLastInspectionDate(date);
    }
    public IntegerProperty storageLocationProperty() {
        return storableCargoBeschreibung.storageLocationProperty();
    }
}

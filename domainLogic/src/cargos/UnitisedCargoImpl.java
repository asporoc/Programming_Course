package cargos;

import administration.Customer;
import administration.Storable;
import cargo.Cargo;
import cargo.Hazard;
import cargo.UnitisedCargo;
import javafx.beans.property.IntegerProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class UnitisedCargoImpl implements Cargo, Storable, UnitisedCargo, storableCargo, Serializable {
    private Storable_Beschreibung storableBeschreibung;
    private Cargo_Beschreibung cargoBeschreibung;
    private UnitisedCargo_Beschreibung unitisedCargoBeschreibung;
    public UnitisedCargoImpl(Customer owner, BigDecimal value, Collection<Hazard> hazards,boolean fragile ){
        this.unitisedCargoBeschreibung = new UnitisedCargo_Beschreibung(fragile);
        this.cargoBeschreibung = new Cargo_Beschreibung(value,hazards);
        this.storableBeschreibung = new Storable_Beschreibung(owner);
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
    public boolean isFragile() {
        return unitisedCargoBeschreibung.isFragile();
    }
    public void setStorageLocation(int location){
        storableBeschreibung.setStorageLocation(location);
    }
    public void setLastInspectionDate(Date date){
        storableBeschreibung.setLastInspectionDate(date);
    }
    public IntegerProperty storageLocationProperty() {
        return storableBeschreibung.storageLocationProperty();
    }
}

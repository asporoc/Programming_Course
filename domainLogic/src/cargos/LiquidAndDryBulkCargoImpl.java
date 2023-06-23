package cargos;

import administration.Customer;
import administration.Storable;
import cargo.Cargo;
import cargo.Hazard;
import cargo.LiquidAndDryBulkCargo;
import javafx.beans.property.IntegerProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class LiquidAndDryBulkCargoImpl implements Storable, Cargo, LiquidAndDryBulkCargo,storableCargo, Serializable {
    Cargo_Beschreibung cargoBeschreibung;
    Storable_Beschreibung storableBeschreibung;
    DryBulkCargo_Beschreibung dryBulkCargoBeschreibung;
    LiquidBulkCargo_Beschreibung liquidBulkCargoBeschreibung;
    public LiquidAndDryBulkCargoImpl(Customer owner, BigDecimal value, Collection<Hazard> hazards, boolean pressurized, int grainSize){
        this.cargoBeschreibung = new Cargo_Beschreibung(value,hazards);
        this.storableBeschreibung = new Storable_Beschreibung(owner);
        this.liquidBulkCargoBeschreibung = new LiquidBulkCargo_Beschreibung(pressurized);
        this.dryBulkCargoBeschreibung = new DryBulkCargo_Beschreibung(grainSize);

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
    public boolean isPressurized() {
        return liquidBulkCargoBeschreibung.isPressurized();
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

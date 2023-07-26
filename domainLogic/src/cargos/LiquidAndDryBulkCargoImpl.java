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
    private StorableCargo_Beschreibung storableCargoBeschreibung;
    Cargo_Beschreibung cargoBeschreibung;
    Storable_Beschreibung storableBeschreibung;
    DryBulkCargo_Beschreibung dryBulkCargoBeschreibung;
    LiquidBulkCargo_Beschreibung liquidBulkCargoBeschreibung;
    public LiquidAndDryBulkCargoImpl(Customer owner, BigDecimal value, Collection<Hazard> hazards, boolean pressurized, int grainSize){
        this.cargoBeschreibung = new Cargo_Beschreibung(value,hazards);
        this.storableBeschreibung = new Storable_Beschreibung(owner);
        this.liquidBulkCargoBeschreibung = new LiquidBulkCargo_Beschreibung(pressurized);
        this.dryBulkCargoBeschreibung = new DryBulkCargo_Beschreibung(grainSize);
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

    @Override
    public boolean isPressurized() {
        return liquidBulkCargoBeschreibung.isPressurized();
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

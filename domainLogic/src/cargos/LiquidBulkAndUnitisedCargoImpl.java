package cargos;

import administration.Customer;
import administration.Storable;
import cargo.Cargo;
import cargo.Hazard;
import cargo.LiquidBulkAndUnitisedCargo;
import javafx.beans.property.IntegerProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class LiquidBulkAndUnitisedCargoImpl implements LiquidBulkAndUnitisedCargo, Cargo, Storable, storableCargo, Serializable {
    private final StorableCargo_Beschreibung storableCargoBeschreibung;

    private final LiquidBulkCargo_Beschreibung liquidBulkCargoBeschreibung;
    private final UnitisedCargo_Beschreibung unitisedCargoBeschreibung;

    public LiquidBulkAndUnitisedCargoImpl(Customer owner, BigDecimal value, Collection<Hazard> hazards, boolean fragile, boolean pressurized ){
        this.liquidBulkCargoBeschreibung = new LiquidBulkCargo_Beschreibung(pressurized);
        this.unitisedCargoBeschreibung = new UnitisedCargo_Beschreibung(fragile);
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
    public boolean isPressurized() {
        return liquidBulkCargoBeschreibung.isPressurized();
    }

    @Override
    public boolean isFragile() {
        return unitisedCargoBeschreibung.isFragile();
    }
    public IntegerProperty storageLocationProperty() {
        return storableCargoBeschreibung.storageLocationProperty();
    }
    public void setStorageLocation(int location){
        storableCargoBeschreibung.setStorageLocation(location);
    }
    public void setLastInspectionDate(Date date){
        storableCargoBeschreibung.setLastInspectionDate(date);
    }
}

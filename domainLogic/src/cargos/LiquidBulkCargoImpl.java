package cargos;

import administration.Customer;
import administration.Storable;
import cargo.Cargo;
import cargo.Hazard;
import cargo.LiquidBulkCargo;
import javafx.beans.property.IntegerProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class LiquidBulkCargoImpl implements Cargo, Storable, LiquidBulkCargo, storableCargo, Serializable {
    private StorableCargo_Beschreibung storableCargoBeschreibung;

    private LiquidBulkCargo_Beschreibung liquidBulkCargoBeschreibung;

    public LiquidBulkCargoImpl(Customer owner, BigDecimal value, Collection<Hazard> hazards, boolean pressurized){
        this.liquidBulkCargoBeschreibung = new LiquidBulkCargo_Beschreibung(pressurized);
        this.storableCargoBeschreibung = new StorableCargo_Beschreibung(owner,value,hazards);


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

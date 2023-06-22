package cargos;

import administration.Customer;
import administration.Storable;
import cargo.Cargo;
import cargo.Hazard;
import cargo.LiquidBulkCargo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class LiquidBulkCargoImpl implements Cargo, Storable, LiquidBulkCargo, storableCargo {
    Cargo_Beschreibung cargoBeschreibung;
    Storable_Beschreibung storableBeschreibung;
    LiquidBulkCargo_Beschreibung liquidBulkCargoBeschreibung;

    public LiquidBulkCargoImpl(Customer owner, BigDecimal value, Collection<Hazard> hazards, boolean pressurized){
        this.cargoBeschreibung = new Cargo_Beschreibung(value/*,hazards*/);
        this.storableBeschreibung = new Storable_Beschreibung(owner);
        this.liquidBulkCargoBeschreibung = new LiquidBulkCargo_Beschreibung(pressurized);


    }

    public Cargo_Beschreibung getCargoBeschreibung() {
        return cargoBeschreibung;
    }

    public Storable_Beschreibung getStorableBeschreibung() {
        return storableBeschreibung;
    }

    public LiquidBulkCargo_Beschreibung getLiquidBulkCargoBeschreibung() {
        return liquidBulkCargoBeschreibung;
    }

    @Override
    public BigDecimal getValue() {
        return getCargoBeschreibung().getValue();
    }

    @Override
    public Collection<Hazard> getHazards() {
        return null;
    }

    @Override
    public Customer getOwner() {
        return getStorableBeschreibung().getOwner();
    }

    @Override
    public Duration getDurationOfStorage() {
        return getStorableBeschreibung().getDurationOfStorage();
    }

    @Override
    public Date getLastInspectionDate() {
        return getStorableBeschreibung().getLastInspectionDate();
    }

    @Override
    public int getStorageLocation() {
        return getStorableBeschreibung().getStorageLocation();
    }

    @Override
    public boolean isPressurized() {
        return getLiquidBulkCargoBeschreibung().isPressurized();
    }
    public void setStorageLocation(int location){
        storableBeschreibung.setStorageLocation(location);
    }
}

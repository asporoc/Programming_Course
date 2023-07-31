package cargos;

import administration.Customer;
import cargo.Hazard;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class StorableCargo_Beschreibung implements storableCargo, Serializable {
    final Customer owner;
    Duration durationOfStorage;
    Date lastInspectionDate;
    final BigDecimal value;
    final Collection<Hazard> hazards;
    private int storageLocation;
    final Date storageDate = new Date();
    public StorableCargo_Beschreibung(Customer owner,BigDecimal value,Collection<Hazard> hazards){
        this.owner=owner;
        lastInspectionDate=storageDate;
        this.value=value;
        this.hazards=hazards;

    }
    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Collection<Hazard> getHazards() {
        return hazards;
    }
    @Override
    public Customer getOwner() {
        return owner;
    }

    @Override
    public Duration getDurationOfStorage() {
        durationOfStorage = Duration.between(storageDate.toInstant(), new Date().toInstant());
        return durationOfStorage;
    }

    @Override
    public Date getLastInspectionDate() {
        return lastInspectionDate;
    }

    @Override
    public int getStorageLocation() {
        return storageLocation;
    }
    @Override
    public void setStorageLocation(int storageLocation) {
        this.storageLocation = storageLocation;

    }
    public void setLastInspectionDate(Date date) {
        this.lastInspectionDate = date;
    }

    @Override
    public IntegerProperty storageLocationProperty() {
        return  new SimpleIntegerProperty(storageLocation);
    }


}

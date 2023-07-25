package cargos;

import administration.Customer;
import cargo.Hazard;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class StorableCargo_Beschreibung implements storableCargo {
    Customer owner;
    Duration durationOfStorage;
    Date lastInspectionDate;
    BigDecimal value;
    Collection<Hazard> hazards;
    private transient IntegerProperty storageLocation;
    Date storageDate = new Date();
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
        return storageLocation.get();
    }

    public void setStorageLocation(int storageLocation) {
        this.storageLocation = new SimpleIntegerProperty(storageLocation);

    }
    public void setLastInspectionDate(Date date) {
        this.lastInspectionDate = date;
    }


}

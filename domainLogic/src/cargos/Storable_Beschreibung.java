package cargos;

import administration.Customer;
import administration.Storable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

public class Storable_Beschreibung implements Storable, Serializable {
    Customer owner;
    Duration durationOfStorage;
    Date lastInspectionDate;
    private transient IntegerProperty storageLocation;
    Date storageDate = new Date();
    public Storable_Beschreibung(Customer owner){
        this.owner=owner;
        lastInspectionDate=storageDate;



    }
    public IntegerProperty storageLocationProperty() {
        return storageLocation;
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

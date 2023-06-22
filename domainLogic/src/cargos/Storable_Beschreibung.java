package cargos;

import administration.Customer;
import administration.Storable;

import java.time.Duration;
import java.util.Date;

public class Storable_Beschreibung implements Storable {
    Customer owner;
    Duration durationOfStorage;
    Date lastInspectionDate;
    int storageLocation;
    Date storageDate = new Date();
    public Storable_Beschreibung(Customer owner){
        this.owner=owner;
        lastInspectionDate=storageDate;



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
        return null;
    }

    @Override
    public int getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(int storageLocation) {
        this.storageLocation = storageLocation;
    }
}

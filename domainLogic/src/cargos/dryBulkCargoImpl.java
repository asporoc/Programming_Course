package cargos;

import administration.Customer;
import cargo.DryBulkCargo;
import cargo.Hazard;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import verwaltung.Kunde;

import java.math.BigDecimal;
import java.time.Duration;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.*;

public class dryBulkCargoImpl implements DryBulkCargo, storableCargo {

    private Duration durationOfStorage;
    private IntegerProperty storageLocation;
    public Date lastInspectionDate;
    final Kunde owner;
    final Date storageDate = new Date();
    private BigDecimal value;
    final int grainSize;
    private Hazard[] hazards;
    final String type;
    final boolean fragile;
    final boolean pressurized;


    private String einfuegenString;

    public void setStorageLocation(int storageLocation) {
        this.storageLocation.set(storageLocation);
    }


    public dryBulkCargoImpl(String type, String ownerName, BigDecimal value, Hazard[] hazards, boolean fragile, boolean pressurized, int grainSize, String einfuegenString) {
        this.owner = new Kunde(ownerName);
        this.value = value;
        this.grainSize = grainSize;
        this.hazards = hazards;
        this.type = type;
        this.lastInspectionDate = new Date();
        this.fragile = fragile;
        this.pressurized = pressurized;
        this.einfuegenString = einfuegenString;
        this.storageLocation = new SimpleIntegerProperty();
    }

    @Override
    public Customer getOwner() {
        return owner;
    }

    @Override
    public Duration getDurationOfStorage() {
        return Duration.between(storageDate.toInstant(), new Date().toInstant());
    }

    @Override
    public Date getLastInspectionDate() {
        return lastInspectionDate;
    }
    public IntegerProperty storageLocationProperty() {
        return storageLocation;
    }

    @Override
    public int getStorageLocation() {
        return storageLocation.get();
    }

    @Override
    public BigDecimal getValue() {
        return value;}

    @Override
    public Collection<Hazard> getHazards() {
        return Arrays.asList(hazards);
    }

    @Override
    public int getGrainSize() {
        return 0;
    }

    @Override
    public String cargoToString() {
        return einfuegenString;
    }
}

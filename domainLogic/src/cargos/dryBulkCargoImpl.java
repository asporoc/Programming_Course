package cargos;

import administration.Customer;
import cargo.DryBulkCargo;
import cargo.Hazard;
import verwaltung.Kunde;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class dryBulkCargoImpl implements DryBulkCargo, storableCargo {

    private Duration durationOfStorage;
    private int storageLocation;
    public Date lastInspectionDate;
    private Kunde owner;
    private Date storageDate;
    private BigDecimal value;
    private int grainSize;
    private Hazard[] hazards;
    private String type;
    private boolean fragile;
    private boolean pressurized;

    public void setEinfuegenString(String einfuegenString) {
        this.einfuegenString = einfuegenString;
    }

    public String getEinfuegenString() {
        return einfuegenString;
    }

    private String einfuegenString;

    public void setStorageLocation(int storageLocation) {
        this.storageLocation = storageLocation;
    }

    public dryBulkCargoImpl(String type,String ownerName, BigDecimal value, Hazard[] hazards,boolean fragile,boolean pressurized, int grainSize,String einfuegenString){
        this.owner = new Kunde(ownerName);
        this.value = value;
        this.grainSize = grainSize;
        this.hazards = hazards;
        this.type = type;
        this.lastInspectionDate = new Date();
        this.fragile = fragile;
        this.pressurized = pressurized;
        this.einfuegenString = einfuegenString;

    }

    @Override
    public Customer getOwner() {
        return owner;
    }

    @Override
    public Duration getDurationOfStorage() {
        return null;
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
    public BigDecimal getValue() {
        return null;
    }

    @Override
    public Collection<Hazard> getHazards() {
        return null;
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

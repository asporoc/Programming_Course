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
    public int storageLocation;
    public Date lastInspectionDate;
    private Customer owner;
    private Date storageDate;
    private BigDecimal value;
    private int grainSize;
    private Hazard[] hazards;
    public dryBulkCargoImpl(int storageLocation, Kunde owner, BigDecimal value, int grainSize, Hazard hazard){

    }

    @Override
    public Customer getOwner() {
        return null;
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

}

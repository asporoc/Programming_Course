package cargo;

import administration.Customer;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class DryBulkTestCargo implements DryBulkCargo {
    private Date lastInspectionDate;
    private int storageLocation;


    private Duration durationOfStorage;
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

    @Override
    public void setStorageLocation(int location) {
        storageLocation = location;

    }

    @Override
    public void setLastInspectionDate(Date lastInspection) {
        lastInspectionDate = lastInspection;

    }
}

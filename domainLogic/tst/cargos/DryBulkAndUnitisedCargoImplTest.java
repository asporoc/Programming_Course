package cargos;

import cargo.Hazard;
import org.junit.jupiter.api.Test;
import verwaltung.Kunde;
import verwaltung.Lager;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DryBulkAndUnitisedCargoImplTest {
    final EnumSet<Hazard> hazards = EnumSet.of(Hazard.explosive);
    final Kunde kunde = new Kunde("Henry");
    final Lager lager = new Lager();

    final DryBulkAndUnitisedCargoImpl dryBulkAndUnitisedCargo = new DryBulkAndUnitisedCargoImpl(kunde, new BigDecimal(33),hazards,23,false);
    @Test
    void getOwner() {
        assertEquals(dryBulkAndUnitisedCargo.getOwner(),kunde);
    }

    @Test
    void getDurationOfStorage(){
        assertFalse(dryBulkAndUnitisedCargo.getDurationOfStorage().isNegative());
    }

    @Test
    void getLastInspectionDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,5);
        assertTrue(dryBulkAndUnitisedCargo.getLastInspectionDate().before(calendar.getTime()));
    }

    @Test
    void getStorageLocation() {
        lager.einfuegen(kunde);
        lager.einfuegen(dryBulkAndUnitisedCargo);
        assertEquals(dryBulkAndUnitisedCargo.getStorageLocation(), 0);

    }

    @Test
    void getValue() {
        assertEquals(dryBulkAndUnitisedCargo.getValue(),new BigDecimal(33));
    }
    @Test
    void getHazards() {
        assertEquals(dryBulkAndUnitisedCargo.getHazards().size(),1);
    }

    @Test
    void getGrainSize() {
        assertEquals(dryBulkAndUnitisedCargo.getGrainSize(),23);
    }

    @Test
    void isFragile() {
        assertFalse(dryBulkAndUnitisedCargo.isFragile());
    }

    @Test
    void setStorageLocation() {
        dryBulkAndUnitisedCargo.setStorageLocation(1);
        assertEquals(dryBulkAndUnitisedCargo.getStorageLocation(),1);
    }

    @Test
    void setLastInspectionDate() {
        Date date = new Date();
        dryBulkAndUnitisedCargo.setLastInspectionDate(date);
        assertEquals(dryBulkAndUnitisedCargo.getLastInspectionDate(),date);
    }

    @Test
    void storageLocationProperty() {
        lager.einfuegen(kunde);
        lager.einfuegen(dryBulkAndUnitisedCargo);
        assertEquals(dryBulkAndUnitisedCargo.storageLocationProperty().get(),0);
    }
}
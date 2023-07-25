package cargos;

import cargo.Hazard;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verwaltung.Kunde;
import verwaltung.Lager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DryBulkAndUnitisedCargoImplTest {
    EnumSet<Hazard> hazards = EnumSet.of(Hazard.explosive);
    Kunde kunde = new Kunde("Henry");
    Lager lager = new Lager();

    DryBulkAndUnitisedCargoImpl dryBulkAndUnitisedCargo = new DryBulkAndUnitisedCargoImpl(kunde, new BigDecimal(33),hazards,23,false);
    @Test
    void getOwner() {
        assertEquals(dryBulkAndUnitisedCargo.getOwner(),kunde);
    }

    @Test
    void getDurationOfStorage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        assertTrue(dryBulkAndUnitisedCargo.getDurationOfStorage().getSeconds()>=1);
    }

    @Test
    void getLastInspectionDate() {
        Date date = new Date();
        assertTrue(dryBulkAndUnitisedCargo.getLastInspectionDate().before(date));
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
        assertEquals(dryBulkAndUnitisedCargo.isFragile(),false);
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
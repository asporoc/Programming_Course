package cargos;

import cargo.Hazard;
import org.junit.jupiter.api.Test;
import verwaltung.Kunde;
import verwaltung.Lager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class LiquidBulkAndUnitisedCargoImplTest {
    final EnumSet<Hazard> hazards = EnumSet.of(Hazard.explosive);
    final Kunde kunde = new Kunde("Henry");
    final Lager lager = new Lager();

    final LiquidBulkAndUnitisedCargoImpl liquidBulkAndUnitisedCargo = new LiquidBulkAndUnitisedCargoImpl(kunde, new BigDecimal(33),hazards,true,false);

    @Test
    void getOwner() {
        assertEquals(liquidBulkAndUnitisedCargo.getOwner(),kunde);
    }

    @Test
    void getDurationOfStorage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        assertTrue(liquidBulkAndUnitisedCargo.getDurationOfStorage().getSeconds()>=1);
    }

    @Test
    void getLastInspectionDate() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        Date date = new Date();
        assertTrue(liquidBulkAndUnitisedCargo.getLastInspectionDate().before(date));
    }

    @Test
    void getStorageLocation() {
        lager.einfuegen(kunde);
        lager.einfuegen(liquidBulkAndUnitisedCargo);
        assertEquals(liquidBulkAndUnitisedCargo.getStorageLocation(), 0);

    }

    @Test
    void getValue() {
        assertEquals(liquidBulkAndUnitisedCargo.getValue(),new BigDecimal(33));
    }
    @Test
    void getHazards() {

        assertEquals(liquidBulkAndUnitisedCargo.getHazards().size(),1);
    }

    @Test
    void isPressurized() {
        assertFalse(liquidBulkAndUnitisedCargo.isPressurized());
    }

    @Test
    void isFragile() {
        assertTrue(liquidBulkAndUnitisedCargo.isFragile());
    }
    @Test
    void setStorageLocation() {
        liquidBulkAndUnitisedCargo.setStorageLocation(1);
        assertEquals(liquidBulkAndUnitisedCargo.getStorageLocation(),1);
    }

    @Test
    void setLastInspectionDate() {
        Date date = new Date();
        liquidBulkAndUnitisedCargo.setLastInspectionDate(date);
        assertEquals(liquidBulkAndUnitisedCargo.getLastInspectionDate(),date);
    }

    @Test
    void storageLocationProperty() {
        lager.einfuegen(kunde);
        lager.einfuegen(liquidBulkAndUnitisedCargo);
        assertEquals(liquidBulkAndUnitisedCargo.storageLocationProperty().get(),0);
    }

}
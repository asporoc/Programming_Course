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
    void getDurationOfStorage(){
        assertFalse(liquidBulkAndUnitisedCargo.getDurationOfStorage().isNegative());
    }

    @Test
    void getLastInspectionDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,5);
        assertTrue(liquidBulkAndUnitisedCargo.getLastInspectionDate().before(calendar.getTime()));
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
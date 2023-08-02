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

class LiquidBulkCargoImplTest {

    final EnumSet<Hazard> hazards = EnumSet.of(Hazard.explosive);
    final Kunde kunde = new Kunde("Henry");
    final Lager lager = new Lager();

    final LiquidBulkCargoImpl liquidBulkCargo = new LiquidBulkCargoImpl(kunde, new BigDecimal(33),hazards,false);
    @Test
    void getOwner() {
        assertEquals(liquidBulkCargo.getOwner(),kunde);
    }

    @Test
    void getDurationOfStorage(){
        assertFalse(liquidBulkCargo.getDurationOfStorage().isNegative());
    }

    @Test
    void getLastInspectionDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,5);
        assertTrue(liquidBulkCargo.getLastInspectionDate().before(calendar.getTime()));
    }

    @Test
    void getStorageLocation() {
        lager.einfuegen(kunde);
        lager.einfuegen(liquidBulkCargo);
        assertEquals(liquidBulkCargo.getStorageLocation(), 0);

    }

    @Test
    void getValue() {
        assertEquals(liquidBulkCargo.getValue(),new BigDecimal(33));
    }
    @Test
    void getHazards() {
        assertEquals(liquidBulkCargo.getHazards().size(),1);
    }


    @Test
    void isPressurized() {
        assertFalse(liquidBulkCargo.isPressurized());
    }

    @Test
    void setStorageLocation() {
        liquidBulkCargo.setStorageLocation(1);
        assertEquals(liquidBulkCargo.getStorageLocation(),1);
    }

    @Test
    void setLastInspectionDate() {
        Date date = new Date(2023, 6, 26, 12, 0, 0);
        liquidBulkCargo.setLastInspectionDate(date);
        assertEquals(date,liquidBulkCargo.getLastInspectionDate());
    }

    @Test
    void storageLocationProperty() {
        lager.einfuegen(kunde);
        lager.einfuegen(liquidBulkCargo);
        assertEquals(liquidBulkCargo.storageLocationProperty().get(),0);
    }
}
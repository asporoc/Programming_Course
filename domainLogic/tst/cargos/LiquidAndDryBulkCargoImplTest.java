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

class LiquidAndDryBulkCargoImplTest {
    final EnumSet<Hazard> hazards = EnumSet.of(Hazard.explosive);
    final Kunde kunde = new Kunde("Henry");
    final Lager lager = new Lager();

    final LiquidAndDryBulkCargoImpl liquidAndDryBulkCargo = new LiquidAndDryBulkCargoImpl(kunde, new BigDecimal(33),hazards,false,23);
    @Test
    void getOwner() {
        assertEquals(liquidAndDryBulkCargo.getOwner(),kunde);
    }

    @Test
    void getDurationOfStorage(){
        assertFalse(liquidAndDryBulkCargo.getDurationOfStorage().isNegative());
    }

    @Test
    void getLastInspectionDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,5);
        assertTrue(liquidAndDryBulkCargo.getLastInspectionDate().before(calendar.getTime()));
    }

    @Test
    void getStorageLocation() {
        lager.einfuegen(kunde);
        lager.einfuegen(liquidAndDryBulkCargo);
        assertEquals(liquidAndDryBulkCargo.getStorageLocation(), 0);

    }

    @Test
    void getValue() {
        assertEquals(liquidAndDryBulkCargo.getValue(),new BigDecimal(33));
    }
    @Test
    void getHazards() {

        assertEquals(liquidAndDryBulkCargo.getHazards().size(),1);
    }

    @Test
    void getGrainSize() {
        assertEquals(liquidAndDryBulkCargo.getGrainSize(),23);
    }

    @Test
    void isPressurized() {
        assertFalse(liquidAndDryBulkCargo.isPressurized());
    }

    @Test
    void setStorageLocation() {
        liquidAndDryBulkCargo.setStorageLocation(1);
        assertEquals(liquidAndDryBulkCargo.getStorageLocation(),1);
    }

    @Test
    void setLastInspectionDate() {
        Date date = new Date();
        liquidAndDryBulkCargo.setLastInspectionDate(date);
        assertEquals(liquidAndDryBulkCargo.getLastInspectionDate(),date);
    }

    @Test
    void storageLocationProperty() {
        lager.einfuegen(kunde);
        lager.einfuegen(liquidAndDryBulkCargo);
        assertEquals(liquidAndDryBulkCargo.storageLocationProperty().get(),0);
    }
}
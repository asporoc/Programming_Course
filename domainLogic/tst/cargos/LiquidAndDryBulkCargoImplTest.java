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

class LiquidAndDryBulkCargoImplTest {
    EnumSet<Hazard> hazards = EnumSet.of(Hazard.explosive);
    Kunde kunde = new Kunde("Henry");
    Lager lager = new Lager();

    LiquidAndDryBulkCargoImpl liquidAndDryBulkCargo = new LiquidAndDryBulkCargoImpl(kunde, new BigDecimal(33),hazards,false,23);
    @Test
    void getOwner() {
        assertEquals(liquidAndDryBulkCargo.getOwner(),kunde);
    }

    @Test
    void getDurationOfStorage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        assertTrue(liquidAndDryBulkCargo.getDurationOfStorage().getSeconds()>=1);
    }

    @Test
    void getLastInspectionDate() {
        Date date = new Date();
        assertTrue(liquidAndDryBulkCargo.getLastInspectionDate().before(date));
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
        assertEquals(liquidAndDryBulkCargo.isPressurized(),false);
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
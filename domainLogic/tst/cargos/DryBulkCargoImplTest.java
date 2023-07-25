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

class DryBulkCargoImplTest {
    EnumSet<Hazard> hazards = EnumSet.of(Hazard.explosive);
    Kunde kunde = new Kunde("Henry");
    Lager lager = new Lager();

    DryBulkCargoImpl dryBulkCargo = new DryBulkCargoImpl(kunde, new BigDecimal(33),hazards,23);

    @Test
    void getOwner() {
        assertEquals(dryBulkCargo.getOwner(),kunde);
    }

    @Test
    void getDurationOfStorage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        assertTrue(dryBulkCargo.getDurationOfStorage().getSeconds()>=1);
    }

    @Test
    void getLastInspectionDate() {
        Date date = new Date();
        assertTrue(dryBulkCargo.getLastInspectionDate().before(date));
    }

    @Test
    void getStorageLocation() {
        lager.einfuegen(kunde);
        lager.einfuegen(dryBulkCargo);
        assertEquals(dryBulkCargo.getStorageLocation(), 0);

    }

    @Test
    void getValue() {
        assertEquals(dryBulkCargo.getValue(),new BigDecimal(33));
    }
    @Test
    void getHazards() {

        assertEquals(dryBulkCargo.getHazards().size(),1);
    }

    @Test
    void getGrainSize() {
        assertEquals(dryBulkCargo.getGrainSize(),23);
    }

    @Test
    void setStorageLocation() {
        dryBulkCargo.setStorageLocation(1);
        assertEquals(dryBulkCargo.getStorageLocation(),1);
    }

    @Test
    void setLastInspectionDate() {
        Date date = new Date();
        dryBulkCargo.setLastInspectionDate(date);
        assertEquals(dryBulkCargo.getLastInspectionDate(),date);
    }

    @Test
    void storageLocationProperty() {
        lager.einfuegen(kunde);
        lager.einfuegen(dryBulkCargo);
        assertEquals(dryBulkCargo.storageLocationProperty().get(),0);
    }
}
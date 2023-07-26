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

class UnitisedCargoImplTest {
    EnumSet<Hazard> hazards = EnumSet.of(Hazard.explosive);
    Kunde kunde = new Kunde("Henry");
    Lager lager = new Lager();

    UnitisedCargoImpl unitisedCargo = new UnitisedCargoImpl(kunde, new BigDecimal(33),hazards,false);

    @Test
    void getOwner() {
        assertEquals(unitisedCargo.getOwner(),kunde);
    }

    @Test
    void getDurationOfStorage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        assertTrue(unitisedCargo.getDurationOfStorage().getSeconds()>=1);
    }

    @Test
    void getLastInspectionDate() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        Date date = new Date();
        assertTrue(unitisedCargo.getLastInspectionDate().before(date));
    }

    @Test
    void getStorageLocation() {
        lager.einfuegen(kunde);
        lager.einfuegen(unitisedCargo);
        assertEquals(unitisedCargo.getStorageLocation(), 0);

    }

    @Test
    void getValue() {
        assertEquals(unitisedCargo.getValue(),new BigDecimal(33));
    }
    @Test
    void getHazards() {

        assertEquals(unitisedCargo.getHazards().size(),1);
    }

    @Test
    void isFragile() {
        assertEquals(unitisedCargo.isFragile(),false);
    }

    @Test
    void setStorageLocation() {
        unitisedCargo.setStorageLocation(1);
        assertEquals(unitisedCargo.getStorageLocation(),1);
    }

    @Test
    void setLastInspectionDate() {
        Date date = new Date();
        unitisedCargo.setLastInspectionDate(date);
        assertEquals(unitisedCargo.getLastInspectionDate(),date);
    }

    @Test
    void storageLocationProperty() {
        lager.einfuegen(kunde);
        lager.einfuegen(unitisedCargo);
        assertEquals(unitisedCargo.storageLocationProperty().get(),0);
    }
}
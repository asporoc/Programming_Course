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

class UnitisedCargoImplTest {
    final EnumSet<Hazard> hazards = EnumSet.of(Hazard.explosive);
    final Kunde kunde = new Kunde("Henry");
    final Lager lager = new Lager();

    final UnitisedCargoImpl unitisedCargo = new UnitisedCargoImpl(kunde, new BigDecimal(33),hazards,false);

    @Test
    void getOwner() {
        assertEquals(unitisedCargo.getOwner(),kunde);
    }

    @Test
    void getDurationOfStorage(){
        assertFalse(unitisedCargo.getDurationOfStorage().isNegative());
    }

    @Test
    void getLastInspectionDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,5);
        assertTrue(unitisedCargo.getLastInspectionDate().before(calendar.getTime()));
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
        assertFalse(unitisedCargo.isFragile());
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
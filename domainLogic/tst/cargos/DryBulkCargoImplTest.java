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
import static org.mockito.Mockito.spy;

class DryBulkCargoImplTest {
    final EnumSet<Hazard> hazards = EnumSet.of(Hazard.explosive);
    final Kunde kunde = new Kunde("Henry");
    final Lager lager = new Lager();

    final DryBulkCargoImpl dryBulkCargo = new DryBulkCargoImpl(kunde, new BigDecimal(33),hazards,23);

    @Test
    void getOwner() {
        assertEquals(dryBulkCargo.getOwner(),kunde);
    }

    @Test
    void getDurationOfStorage() {
        assertFalse(dryBulkCargo.getDurationOfStorage().isNegative());
    }

    @Test
    void getLastInspectionDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,5);
        assertTrue(dryBulkCargo.getLastInspectionDate().before(calendar.getTime()));
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
        assertEquals(1,dryBulkCargo.getStorageLocation());
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
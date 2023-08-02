package cargos;

import cargo.Hazard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verwaltung.Kunde;
import verwaltung.Lager;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

class StorableCargo_BeschreibungTest {
    StorableCargo_Beschreibung storableCargoBeschreibung;
    Kunde kunde;
    Lager lager;
    @BeforeEach
    void setup(){
        final EnumSet<Hazard> hazards = EnumSet.of(Hazard.explosive);
        kunde = new Kunde("Heinz");
        storableCargoBeschreibung = new StorableCargo_Beschreibung(kunde,new BigDecimal(23),hazards);
        lager = new Lager();
    }

    @Test
    void getValue() {
        assertEquals(storableCargoBeschreibung.getValue(),new BigDecimal(23));
    }

    @Test
    void getHazards() {
        assertEquals(storableCargoBeschreibung.getHazards().size(),1);
    }

    @Test
    void getOwner() {
        assertEquals(kunde,storableCargoBeschreibung.getOwner());
    }

    @Test
    void getDurationOfStorage() {
        assertFalse(storableCargoBeschreibung.getDurationOfStorage().isNegative());
    }

    @Test
    void getLastInspectionDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,5);
    }

    @Test
    void getStorageLocation() {
        lager.einfuegen(kunde);
        lager.einfuegen(storableCargoBeschreibung);
        assertEquals(storableCargoBeschreibung.getStorageLocation(), 0);
    }

    @Test
    void setStorageLocation() {
        storableCargoBeschreibung.setStorageLocation(1);
        assertEquals(storableCargoBeschreibung.getStorageLocation(),1);
    }

    @Test
    void setLastInspectionDate() {
        Date date = new Date();
        storableCargoBeschreibung.setLastInspectionDate(date);
        assertEquals(storableCargoBeschreibung.getLastInspectionDate(),date);
    }

    @Test
    void storageLocationProperty() {
        lager.einfuegen(kunde);
        lager.einfuegen(storableCargoBeschreibung);
        assertEquals(storableCargoBeschreibung.storageLocationProperty().get(),0);
    }
}
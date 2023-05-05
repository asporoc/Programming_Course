package verwaltung;

import cargo.Hazard;
import cargos.dryBulkCargoImpl;
import cargos.storableCargo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {

    @Test
    void einfuegen() {
        Lager lagerZuTesten = new Lager();
        assertTrue(lagerZuTesten.einfuegen(new dryBulkCargoImpl(0,new BigDecimal(6),2, Hazard.flammable)));

    }
    @Test
    void einfuegenKunde() {
        Lager lagerZuTesten = new Lager();
        assertTrue(lagerZuTesten.einfuegen("Jonathan"));
    }
    @Test
    void kundenMitGleichemNamen() {
        Lager lagerZuTesten = new Lager();
        lagerZuTesten.einfuegen("Jonathan");
        assertFalse(lagerZuTesten.einfuegen("Jonathan"));
    }


    @Test
    void abrufen() {
        Lager lagerZuTesten = new Lager();
        assertEquals(lagerZuTesten.cargoList,lagerZuTesten.abrufen());

    }
    @Test
    void abrufenLeeresLager(){
        Lager lagerZuTesten = new Lager();
        List<storableCargo> result = lagerZuTesten.abrufen();
        assertEquals(0, result.size());

    }
    @Test
    void inspectionTest(){
        Lager lagerZuTesten = new Lager();
        assertEquals(new Date(),lagerZuTesten.inspection(new dryBulkCargoImpl(0,new BigDecimal(6),2, Hazard.flammable)));
        // abhängig von System geschwindigkeit einschränkung weniger hart formulieren
    }
    @Test
    void entfernenTest(){
        Lager lagerZuTesten = new Lager();
        dryBulkCargoImpl doodle = new dryBulkCargoImpl(0,new BigDecimal(6),2, Hazard.flammable);
        lagerZuTesten.einfuegen(doodle);
        assertTrue(lagerZuTesten.entfernen(doodle.getStorageLocation()));
    }


}
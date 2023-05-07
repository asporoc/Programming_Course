package verwaltung;

import cargo.Hazard;
import cargos.dryBulkCargoImpl;
import cargos.storableCargo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {

    @Test
    void einfuegen() {
        Lager lagerZuTesten = new Lager();
        lagerZuTesten.einfuegen("Heinz");
        assertTrue(lagerZuTesten.einfuegen(new dryBulkCargoImpl("Heinz", new BigDecimal(6),2, new Hazard[]{Hazard.flammable})));

    }
    @Test
    void einfuegenKunde() {
        Lager lagerZuTesten = new Lager();
        assertTrue(lagerZuTesten.einfuegen("Jonathan"));
    }
    @Test
    void sizeTest() {
        Lager lagerZuTesten = new Lager();
        lagerZuTesten.einfuegen("Heinz");
        for(int i = 0; i<10; i++){
            lagerZuTesten.einfuegen(new dryBulkCargoImpl("Heinz", new BigDecimal(6),2, new Hazard[]{Hazard.flammable}));
        }
        assertFalse(lagerZuTesten.einfuegen(new dryBulkCargoImpl("Heinz", new BigDecimal(6),2, new Hazard[]{Hazard.flammable})));

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
        HashMap<Integer,storableCargo> result = lagerZuTesten.abrufen();
        assertEquals(0, result.size());

    }
    @Test
    void inspectionTest(){
        Lager lagerZuTesten = new Lager();
        assertEquals(new Date(),lagerZuTesten.inspection(new dryBulkCargoImpl("Heinz" ,new BigDecimal(6),2, new Hazard[]{Hazard.flammable})));
        // abhängig von System geschwindigkeit einschränkung weniger hart formulieren
    }
    @Test
    void entfernenTest(){
        Lager lagerZuTesten = new Lager();
        dryBulkCargoImpl doodle = new dryBulkCargoImpl("Heinz",new BigDecimal(6),2, new Hazard[]{Hazard.flammable});
        lagerZuTesten.einfuegen(doodle);
        assertTrue(lagerZuTesten.entfernen(doodle.getStorageLocation()));
    }
    @Test
    void einfuegenKundeExistiert() {
        Lager lagerZuTesten = new Lager();
        lagerZuTesten.einfuegen("Jonathan");
        dryBulkCargoImpl doodle = new dryBulkCargoImpl("Jonathan",new BigDecimal(6),2, new Hazard[]{Hazard.flammable});
        assertTrue(lagerZuTesten.einfuegen(doodle));

    }
    @Test
    void einfuegenKundeExistiertNicht() {
        Lager lagerZuTesten = new Lager();
        dryBulkCargoImpl doodle = new dryBulkCargoImpl("Jonathan",new BigDecimal(6),2, new Hazard[]{Hazard.flammable});
        assertFalse(lagerZuTesten.einfuegen(doodle));

    }
}
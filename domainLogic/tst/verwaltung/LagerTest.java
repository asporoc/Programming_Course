package verwaltung;

import cargos.dryBulkCargo;
import cargos.storableCargo;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LagerTest {

    @Test
    void einfuegen() {
        Lager lagerZuTesten = new Lager();
        assertTrue(lagerZuTesten.einfuegen(new dryBulkCargo()));

    }

    @Test
    void abrufen() {
        Lager lagerZuTesten = new Lager();
        assertEquals(this, lagerZuTesten.abrufen());
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
        assertEquals(new Date(),lagerZuTesten.inspection());
    }
    @Test
    void entfernenTest(){
        Lager lagerZuTesten = new Lager();
        dryBulkCargo doodle = new dryBulkCargo();
        lagerZuTesten.einfuegen(doodle);
        assertTrue(lagerZuTesten.entfernen(doodle));
    }

}
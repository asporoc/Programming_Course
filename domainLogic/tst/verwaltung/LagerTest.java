package verwaltung;

import cargo.Cargo;
import cargo.DryBulkTestCargo;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {

    @Test
    void einfuegen() {
        Lager lagerZuTesten = new Lager();
        assertTrue(lagerZuTesten.einfuegen(new DryBulkTestCargo()));

    }

    @Test
    void abrufen() {
    }
    @Test
    void abrufenLeeresLager(){
        Lager lagerZuTesten = new Lager();
        List<Cargo> result = lagerZuTesten.abrufen();
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
        DryBulkTestCargo doodle = new DryBulkTestCargo();
        lagerZuTesten.einfuegen(doodle);
        assertTrue(lagerZuTesten.entfernen(doodle));
    }

}
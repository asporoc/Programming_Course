package verwaltung;

import cargo.Cargo;
import cargo.DryBulkCargo;
import cargo.testCargo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {

    @Test
    void einfuegen() {
        Lager lagerZuTesten = new Lager();
        assertTrue(lagerZuTesten.einfuegen(new testCargo()));

    }

    @Test
    void abrufen() {
    }
    @Test
    void abrufenLeeresLager(){
        Lager lagerZuTesten = new Lager();
        List<DryBulkCargo> result = lagerZuTesten.abrufen();
        assertEquals(0, result.size());

    }
    @Test
    void inspectionTest(){
        Lager lagerZuTesten = new Lager();
        assertEquals(new Date(),lagerZuTesten.inspection());
    }

}
package verwaltung;

import cargos.storableCargo;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {

    @Test
    void einfuegen() {
        Lager lagerZuTesten = new Lager();
        lagerZuTesten.einfuegen("Heinz");
        assertTrue(lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13"));

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
            lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13");
        }
        assertFalse(lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13"));

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
        assertEquals(lagerZuTesten.getCargoList(),lagerZuTesten.abrufen());

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
        lagerZuTesten.einfuegen("Heinz");
        lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13");
        Date currentDate = new Date();
        assertTrue((lagerZuTesten.inspection(1).getTime() - currentDate.getTime()) < 1000L);
        //test so ändern das datum im Verhältnis zum vorherigen Inspection Date(ist nach vorherigen Inspection date?)
    }
    @Test
    void entfernenTest(){
        Lager lagerZuTesten = new Lager();
        lagerZuTesten.einfuegen("Heinz");
        lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13");
        assertTrue(lagerZuTesten.entfernen(1));
    }
    @Test
    void einfuegenKundeExistiert() {
        Lager lagerZuTesten = new Lager();
        lagerZuTesten.einfuegen("Jonathan");
        assertTrue(lagerZuTesten.einfuegen("DryBulkCargo Jonathan 123 , true false 13"));

    }
    @Test
    void einfuegenKundeExistiertNicht() {
        Lager lagerZuTesten = new Lager();
        assertFalse(lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13"));

    }
}
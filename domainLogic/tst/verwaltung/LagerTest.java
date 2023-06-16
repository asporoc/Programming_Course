package verwaltung;

import cargo.DryBulkCargo;
import cargos.dryBulkCargoImpl;
import cargos.storableCargo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {
    Lager lagerZuTesten;
    @BeforeEach
            void setUp(){
        lagerZuTesten = new Lager();
        lagerZuTesten.einfuegen("Heino");
        lagerZuTesten.einfuegen("DryBulkCargo Heino 33,flammable,explosive true false 33");
    }



    @Test
    void einfuegen() {
        lagerZuTesten.einfuegen("Heinz");
        assertTrue(lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13"));

    }
    @Test
    void einfuegenKunde() {
        assertTrue(lagerZuTesten.einfuegen("Jonathan"));
    }
    @Test
    void sizeTest() {
        lagerZuTesten.einfuegen("Heinz");
        for(int i = 0; i<10; i++){
            lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13");
        }
        assertFalse(lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13"));

    }
    @Test
    void kundenMitGleichemNamen() {
        lagerZuTesten.einfuegen("Jonathan");
        assertFalse(lagerZuTesten.einfuegen("Jonathan"));
    }


    @Test
    void abrufen() {
        assertEquals(lagerZuTesten.getCargoList().clone(),lagerZuTesten.abrufen());

    }
    @Test
    void abrufenLeeresLager(){
        Lager leeresLager = new Lager();
        assertEquals(0, leeresLager.getCargoList().size());

    }
    @Test
    void inspectionTest(){
        lagerZuTesten.einfuegen("Heinz");
        lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13");
        Date currentDate = new Date();
        assertTrue((lagerZuTesten.inspection(1).getTime() <= currentDate.getTime()));
        //test so ändern das datum im Verhältnis zum vorherigen Inspection Date(ist nach vorherigen Inspection date?)
    }
    @Test
    void entfernenTest(){
        lagerZuTesten.einfuegen("Heinz");
        lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13");
        assertTrue(lagerZuTesten.entfernen(1));
    }
    @Test
    void einfuegenKundeExistiert() {
        lagerZuTesten.einfuegen("Jonathan");
        assertTrue(lagerZuTesten.einfuegen("DryBulkCargo Jonathan 123 , true false 13"));

    }
    @Test
    void einfuegenKundeExistiertNicht() {
        assertFalse(lagerZuTesten.einfuegen("DryBulkCargo Heinz 123 , true false 13"));

    }
}
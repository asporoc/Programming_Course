package verwaltung;

import administration.Customer;
import cargos.dryBulkCargoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {
    Lager lagerZuTesten;
    @BeforeEach
            void setUp(){
        lagerZuTesten = new Lager();
        lagerZuTesten.einfuegen("Heino");
        lagerZuTesten.einfuegen("Hugo");
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
    void abrufenNotNull() {
        assertNotNull(lagerZuTesten.abrufen());
    }
    @Test
    void abrufenNotOriginal() {
        assertNotSame(lagerZuTesten.abrufen(),lagerZuTesten.getCargoList());
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
    @Test
    void getMaxSizeTest(){
        Lager sizeLager = new Lager(14);
        assertEquals(14, sizeLager.getMaxsize());
    }
    @Test
    void defaultMaxSizeTest(){
        Lager sizeLager = new Lager();
        assertEquals(10, sizeLager.getMaxsize());
    }
    @Test
    void getCargoList(){
        HashMap<Integer,dryBulkCargoImpl> test = lagerZuTesten.getCargoList();
        assertSame(test,lagerZuTesten.getCargoList());
    }
    @Test
    void getCustomerList(){
        List<Customer> test = lagerZuTesten.getCustomerList();
        assertNotNull(test);
        assertEquals(test.get(0).getName(),"Heino");
        assertEquals(test.get(1).getName(),"Hugo");
    }
    @Test
    void abrufenCargo(){
        assertEquals(lagerZuTesten.abrufen(0),lagerZuTesten.getCargoList().get(0));
    }

}
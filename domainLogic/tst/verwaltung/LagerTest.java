package verwaltung;

import administration.Customer;
import cargo.Hazard;
import cargos.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {
    Lager lagerZuTesten;
    @BeforeEach
    void setUp(){
        lagerZuTesten = new Lager();
        lagerZuTesten.einfuegen(new Kunde("Heino"));
        lagerZuTesten.einfuegen(new Kunde("Hugo"));
        lagerZuTesten.einfuegen(parseCargo("DryBulkCargo Heino 33,flammable,explosive true false 33"));
        lagerZuTesten.einfuegen(parseCargo("UnitisedCargo Hugo 44,explosive true"));
    }

    @Test
    void einfuegen() {
        assertTrue(lagerZuTesten.einfuegen(parseCargo("DryBulkCargo Heino 123 , true false 13")));

    }
    @Test
    void einfuegenKunde() {
        assertTrue(lagerZuTesten.einfuegen(new Kunde("Jonathan")));
    }
    @Test
    void sizeTest() {
        lagerZuTesten.einfuegen(new Kunde("Heinz"));
        for(int i = 0; i<10; i++){
            lagerZuTesten.einfuegen(parseCargo("DryBulkCargo Heinz 123 , true false 13"));
        }
        assertFalse(lagerZuTesten.einfuegen(parseCargo("DryBulkCargo Heinz 123 , true false 13")));

    }
    @Test
    void kundenMitGleichemNamen() {
        lagerZuTesten.einfuegen(new Kunde("Jonathan"));
        assertFalse(lagerZuTesten.einfuegen(new Kunde("Jonathan")));
    }

    @Test
    void abrufenNotNull() throws CloneNotSupportedException {
        assertNotNull(lagerZuTesten.abrufen());
    }
    @Test
    void abrufenNotOriginal() throws CloneNotSupportedException {
        assertNotSame(lagerZuTesten.abrufen(),lagerZuTesten.getCargoList());
    }
    @Test
    void abrufenLeeresLager(){
        Lager leeresLager = new Lager();
        assertEquals(0, leeresLager.getCargoList().size());

    }
    @Test
    void inspectionTest(){
        lagerZuTesten.einfuegen(new Kunde("Heinz"));
        lagerZuTesten.einfuegen(parseCargo("DryBulkCargo Heinz 123 , true false 13"));
        Date currentDate = new Date();
        assertTrue((lagerZuTesten.inspection(1).getTime() <= currentDate.getTime()));
        //test so ändern das datum im Verhältnis zum vorherigen Inspection Date(ist nach vorherigen Inspection date?)
    }
    @Test
    void entfernenTest(){
        lagerZuTesten.einfuegen(new Kunde("Heinz"));
        lagerZuTesten.einfuegen(parseCargo("DryBulkCargo Heinz 123 , true false 13"));
        assertTrue(lagerZuTesten.entfernen(1));
    }
    @Test
    void einfuegenKundeExistiert() {
        lagerZuTesten.einfuegen(new Kunde("Jonathan"));
        assertTrue(lagerZuTesten.einfuegen(parseCargo("DryBulkCargo Jonathan 123 , true false 13")));

    }
    @Test
    void einfuegenKundeExistiertNicht() {
        assertFalse(lagerZuTesten.einfuegen(parseCargo("DryBulkCargo Heinz 123 , true false 13")));

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
        HashMap<Integer, storableCargo> test = lagerZuTesten.getCargoList();
        assertSame(test,lagerZuTesten.getCargoList());
    }
    @Test
    void getCustomerList(){
        List<Customer> test = lagerZuTesten.getCustomerList();
        assertNotNull(test);
        assertEquals(test,lagerZuTesten.getCustomerList());
    }
    @Test
    void abrufenCargo(){
        assertEquals(lagerZuTesten.abrufen(0),lagerZuTesten.getCargoList().get(0));
    }
    public storableCargo parseCargo(String einfuegenString){

        storableCargo cargo = null;
        String[] text = einfuegenString.split(" ");
            EnumSet<Hazard> hazards = EnumSet.noneOf(Hazard.class);
            String value = null;

            if (text[2].contains(",")) {
                String[] hazardText = text[2].split(",");
                value = hazardText[0];
                for (int i = 0; i < hazardText.length; i++) {
                    switch (hazardText[i]) {
                        case "flammable":
                            hazards.add(Hazard.flammable);
                            break;
                        case "toxic":
                            hazards.add(Hazard.toxic);
                            break;
                        case "radioactive":
                            hazards.add(Hazard.radioactive);
                            break;
                        case "explosive":
                            hazards.add(Hazard.explosive);
                            break;
                        default:
                    }
                }
            } else {
                value = text[2];
            }
            switch (text[0]) {
                case "DryBulkCargo":
                    cargo = new DryBulkCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Integer.parseInt(text[text.length - 1]));
                    break;
                case "DryBulkAndUnitisedCargo":
                    cargo = new DryBulkAndUnitisedCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Integer.parseInt(text[text.length - 1]), Boolean.parseBoolean(text[text.length - 3]));
                    break;
                case "LiquidAndDryBulkCargo":
                    cargo = new LiquidAndDryBulkCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Boolean.parseBoolean(text[text.length - 2]), Integer.parseInt(text[text.length - 1]));
                    break;
                case "LiquidBulkCargo":
                    cargo = new LiquidBulkCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Boolean.parseBoolean(text[text.length - 2]));
                    break;
                case "UnitisedCargo":
                    cargo = new UnitisedCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Boolean.parseBoolean(text[text.length - 3]));
                default:
            }
            return cargo;
        }
        @Test
        void setStorageLocationTest(){
        storableCargo cargo = lagerZuTesten.getCargoList().get(0);
        lagerZuTesten.setStorageLocation(0,1);
        assertSame(lagerZuTesten.getCargoList().get(1),cargo);
        }

    @Test
    void setMonitorTest() {
        Object customMonitor = new Object();

        Lager lagerSpy = Mockito.spy(lagerZuTesten);

        lagerSpy.setMonitor(customMonitor);

        Assertions.assertEquals(customMonitor, lagerSpy.monitor);
    }
    @Test
    void entfernenTestLeeresLager(){
        Lager lager = new Lager(0);
        assertFalse(lager.entfernen(0));
    }


}
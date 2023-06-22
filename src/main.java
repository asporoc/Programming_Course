import administration.Customer;
import cargos.LiquidBulkCargoImpl;
import eventSystem.infrastructure.EventHandler;
import eventSystem.listener.EinfuegenListener;
import eventSystem.listener.EntfernenListener;
import eventSystem.listener.InspectionListener;
import eventSystem.viewControl.ConsoleEventSystem;
import verwaltung.Kunde;
import verwaltung.Lager;

import java.math.BigDecimal;
import java.util.Collection;


public class main {
    public static void main(String[] args) throws Exception {
        Lager testLager = new Lager();
        LiquidBulkCargoImpl testliquid = new LiquidBulkCargoImpl(new Kunde("Hinz"),2, new BigDecimal(3),false);
        LiquidBulkCargoImpl test = new LiquidBulkCargoImpl(new Kunde("Hinz"),2, new BigDecimal(7),false);
        System.out.println(testliquid.getCargoBeschreibung().getValue());
        System.out.println(test.getCargoBeschreibung().getValue());
        /* twoLayered approach */
        ConsoleTwoLayered testConsoleTwoLayered = new ConsoleTwoLayered(testLager);
        testConsoleTwoLayered.execute();
        /*Versuch der Implementierung von Event-System Abrufmethode funktioniert beidngt durch fehlenden Rückgabewert nicht*/
        /*ConsoleEventSystem testConsoleEventSystem = new ConsoleEventSystem(testLager);
        EventHandler einfuegenHandler = new EventHandler();
        EinfuegenListener einfuegenListener = new EinfuegenListener(testLager);
        einfuegenHandler.addListener(einfuegenListener);
        testConsoleEventSystem.setEinfuegenHandler(einfuegenHandler);

        EventHandler entfernenHandler = new EventHandler();
        EntfernenListener entfernenListener = new EntfernenListener(testLager);
        entfernenHandler.addListener(entfernenListener);
        testConsoleEventSystem.setStorageLocationHandler(entfernenHandler);

        EventHandler inspectionHandler = new EventHandler();
        InspectionListener inspectionListener = new InspectionListener(testLager);
        inspectionHandler.addListener(inspectionListener);
        testConsoleEventSystem.setStorageLocationHandler(inspectionHandler);
        testConsoleEventSystem.execute();*/








    }
}
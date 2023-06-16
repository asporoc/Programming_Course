import eventSystem.infrastructure.EventHandler;
import eventSystem.listener.EinfuegenListener;
import eventSystem.listener.EntfernenListener;
import eventSystem.listener.InspectionListener;
import eventSystem.viewControl.ConsoleEventSystem;
import verwaltung.Lager;



public class main {
    public static void main(String[] args) throws Exception {
        Lager testLager = new Lager();
        lagerObserver observer = new lagerObserver();
        testLager.addObserver(observer);
        /* twoLayered approach */
       /* ConsoleTwoLayered testConsoleTwoLayered = new ConsoleTwoLayered(testLager);
        testConsoleTwoLayered.execute();*/
        /*Versuch der Implementierung von Event-System Abrufmethode funktioniert beidngt durch fehlenden Rückgabewert nicht*/
        ConsoleEventSystem testConsoleEventSystem = new ConsoleEventSystem(testLager);
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
        testConsoleEventSystem.execute();








    }
}
import eventSystem.infrastructure.EventHandler;
import eventSystem.listener.*;
import eventSystem.viewControl.ConsoleEventSystem;
import verwaltung.Lager;


public class main {
    public static void main(String[] args) throws Exception {
        Lager testLager = new Lager();
        lagerObserver observer = new lagerObserver(testLager);
        ConsoleEventSystem testConsoleEventSystem = new ConsoleEventSystem();
        EventHandler storableCargoEinfuegenHandler = new EventHandler();
        StorableCargoEinfuegenListener storableCargoEinfuegenListener = new StorableCargoEinfuegenListener(testLager);
        storableCargoEinfuegenHandler.addListener(storableCargoEinfuegenListener);
        testConsoleEventSystem.setStorableCargoEinfuegenHandler(storableCargoEinfuegenHandler);

        EventHandler kundeEinfuegenHandler = new EventHandler();
        KundeEinfuegenListener kundeEinfuegenListener = new KundeEinfuegenListener(testLager);
        kundeEinfuegenHandler.addListener(kundeEinfuegenListener);
        testConsoleEventSystem.setKundeEinfuegenHandler(kundeEinfuegenHandler);

        EventHandler entfernenHandler = new EventHandler();
        EntfernenListener entfernenListener = new EntfernenListener(testLager);
        entfernenHandler.addListener(entfernenListener);
        testConsoleEventSystem.setEntfernenEventHandler(entfernenHandler);

        EventHandler abrufenHandler = new EventHandler();
        AbrufenListener abrufenListener = new AbrufenListener(testLager);
        abrufenHandler.addListener(abrufenListener);
        testConsoleEventSystem.setAbrufenEventHandler(abrufenHandler);

        EventHandler inspectionHandler = new EventHandler();
        InspectionListener inspectionListener = new InspectionListener(testLager);
        inspectionHandler.addListener(inspectionListener);
        testConsoleEventSystem.setInspectionEventHandler(inspectionHandler);
        testConsoleEventSystem.execute();
    }
}
import eventSystem.infrastructure.EventHandler;
import viewControl.ConsoleEventSystem;
import server.*;
import verwaltung.LagerFassade;


public class main {
    public static void main(String[] args) throws Exception {
        LagerFassade lagerFassade = new LagerFassade();
        lagerObserver observer = new lagerObserver(lagerFassade.getLager());
        ConsoleEventSystem testConsoleEventSystem = new ConsoleEventSystem();
        EventHandler storableCargoEinfuegenHandler = new EventHandler();
        EventHandler serverEventHandler = new EventHandler();

        StorableCargoEinfuegenListener storableCargoEinfuegenListener = new StorableCargoEinfuegenListener(lagerFassade,serverEventHandler);
        storableCargoEinfuegenHandler.addListener(storableCargoEinfuegenListener);
        testConsoleEventSystem.setStorableCargoEinfuegenHandler(storableCargoEinfuegenHandler);

        EventHandler kundeEinfuegenHandler = new EventHandler();
        KundeEinfuegenListener kundeEinfuegenListener = new KundeEinfuegenListener(lagerFassade);
        kundeEinfuegenHandler.addListener(kundeEinfuegenListener);
        testConsoleEventSystem.setKundeEinfuegenHandler(kundeEinfuegenHandler);

        EventHandler entfernenHandler = new EventHandler();
        CargoEntfernenListener cargoEntfernenListener = new CargoEntfernenListener(lagerFassade);
        entfernenHandler.addListener(cargoEntfernenListener);
        testConsoleEventSystem.setEntfernenEventHandler(entfernenHandler);

        EventHandler abrufenHandler = new EventHandler();
        AbrufenListener abrufenListener = new AbrufenListener(lagerFassade);
        abrufenHandler.addListener(abrufenListener);
        testConsoleEventSystem.setAbrufenEventHandler(abrufenHandler);

        EventHandler inspectionHandler = new EventHandler();
        InspectionListener inspectionListener = new InspectionListener(lagerFassade);
        inspectionHandler.addListener(inspectionListener);
        testConsoleEventSystem.setInspectionEventHandler(inspectionHandler);

        EventHandler persistenceHandler = new EventHandler();
        PersistenceListener persistenceListener = new PersistenceListener(lagerFassade);
        persistenceHandler.addListener(persistenceListener);
        testConsoleEventSystem.setPersistenceEventHandler(persistenceHandler);

        EventHandler kundeEntfernenHandler = new EventHandler();
        KundeEntfernenListener kundeEntfernenListener = new KundeEntfernenListener(lagerFassade);
        kundeEntfernenHandler.addListener(kundeEntfernenListener);
        testConsoleEventSystem.setKundeEntfernenHandler(kundeEntfernenHandler);

        testConsoleEventSystem.execute();
    }
}
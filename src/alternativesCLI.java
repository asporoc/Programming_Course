import eventSystem.infrastructure.EventHandler;
import server.*;
import verwaltung.LagerFassade;
import viewControl.ConsoleEventSystem;

public class alternativesCLI {
    public static void main(String[] args) throws Exception {

        ConsoleEventSystem CES = new ConsoleEventSystem();
        String[] arguments = CES.startClient();
        int capacity;
        try {
            capacity = Integer.parseInt(arguments[0]);
        } catch (NumberFormatException e) {
            return;
        }
        LagerFassade lagerFassade = new LagerFassade(capacity);
        lagerObserver observer = new lagerObserver(lagerFassade.getLager());
        ConsoleEventSystem testConsoleEventSystem = new ConsoleEventSystem();
        EventHandler storableCargoEinfuegenHandler = new EventHandler();
        EventHandler serverEventHandler = new EventHandler();

        StorableCargoEinfuegenListener storableCargoEinfuegenListener = new StorableCargoEinfuegenListener(lagerFassade, serverEventHandler);
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
        AbrufenListener abrufenListener = new AbrufenListener(lagerFassade, CES);
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

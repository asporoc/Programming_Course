import eventSystem.infrastructure.EventHandler;
import eventSystem.viewControl.ConsoleEventSystem;
import server.*;
import verwaltung.Lager;
import verwaltung.LagerFassade;


public class main {
    public static void main(String[] args) throws Exception {
        LagerFassade lagerFassade = new LagerFassade();
        lagerObserver observer = new lagerObserver(lagerFassade.getLager());
        ConsoleEventSystem testConsoleEventSystem = new ConsoleEventSystem();
        EventHandler storableCargoEinfuegenHandler = new EventHandler();
        StorableCargoEinfuegenListener storableCargoEinfuegenListener = new StorableCargoEinfuegenListener(lagerFassade);
        storableCargoEinfuegenHandler.addListener(storableCargoEinfuegenListener);
        testConsoleEventSystem.setStorableCargoEinfuegenHandler(storableCargoEinfuegenHandler);

        EventHandler kundeEinfuegenHandler = new EventHandler();
        KundeEinfuegenListener kundeEinfuegenListener = new KundeEinfuegenListener(lagerFassade);
        kundeEinfuegenHandler.addListener(kundeEinfuegenListener);
        testConsoleEventSystem.setKundeEinfuegenHandler(kundeEinfuegenHandler);

        EventHandler entfernenHandler = new EventHandler();
        EntfernenListener entfernenListener = new EntfernenListener(lagerFassade);
        entfernenHandler.addListener(entfernenListener);
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

        testConsoleEventSystem.execute();
    }
}
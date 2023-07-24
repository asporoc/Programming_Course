import eventSystem.infrastructure.EventHandler;
import eventSystem.viewControl.ConsoleEventSystem;
import server.*;
import verwaltung.Lager;
import verwaltung.LagerFassade;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        LagerFassade lagerFassade = new LagerFassade();
        ConsoleEventSystem CES = new ConsoleEventSystem();

        EventHandler kundeEinfuegenHandler = new EventHandler();
        KundeEinfuegenListener kundeEinfuegenListener = new KundeEinfuegenListener(lagerFassade);
        kundeEinfuegenHandler.addListener(kundeEinfuegenListener);
        CES.setKundeEinfuegenHandler(kundeEinfuegenHandler);

        EventHandler storableCargoEinfuegenHandler = new EventHandler();
        StorableCargoEinfuegenListener storableCargoEinfuegenListener = new StorableCargoEinfuegenListener(lagerFassade);
        storableCargoEinfuegenHandler.addListener(storableCargoEinfuegenListener);
        CES.setStorableCargoEinfuegenHandler(storableCargoEinfuegenHandler);

        EventHandler entfernenHandler = new EventHandler();
        EntfernenListener entfernenListener = new EntfernenListener(lagerFassade);
        entfernenHandler.addListener(entfernenListener);
        CES.setEntfernenEventHandler(entfernenHandler);

        EventHandler abrufenHandler = new EventHandler();
        AbrufenListener abrufenListener = new AbrufenListener(lagerFassade);
        abrufenHandler.addListener(abrufenListener);
        CES.setAbrufenEventHandler(abrufenHandler);

        EventHandler inspectionHandler = new EventHandler();
        InspectionListener inspectionListener = new InspectionListener(lagerFassade);
        inspectionHandler.addListener(inspectionListener);
        CES.setInspectionEventHandler(inspectionHandler);

        EventHandler persistenceHandler = new EventHandler();
        PersistenceListener persistenceListener = new PersistenceListener(lagerFassade);
        persistenceHandler.addListener(persistenceListener);
        CES.setPersistenceEventHandler(persistenceHandler);

        Server server = new Server(12345, CES);








    }
}

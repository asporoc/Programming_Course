import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.KundeEntfernenEvent;
import viewControl.ConsoleEventSystem;
import server.*;
import verwaltung.LagerFassade;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        LagerFassade lagerFassade = new LagerFassade();
        ConsoleEventSystem CES = new ConsoleEventSystem();
        EventHandler serverEventHandler = new EventHandler();


        EventHandler kundeEinfuegenHandler = new EventHandler();
        KundeEinfuegenListener kundeEinfuegenListener = new KundeEinfuegenListener(lagerFassade,serverEventHandler);
        kundeEinfuegenHandler.addListener(kundeEinfuegenListener);
        CES.setKundeEinfuegenHandler(kundeEinfuegenHandler);

        EventHandler storableCargoEinfuegenHandler = new EventHandler();
        StorableCargoEinfuegenListener storableCargoEinfuegenListener = new StorableCargoEinfuegenListener(lagerFassade, serverEventHandler);
        storableCargoEinfuegenHandler.addListener(storableCargoEinfuegenListener);
        CES.setStorableCargoEinfuegenHandler(storableCargoEinfuegenHandler);

        EventHandler entfernenHandler = new EventHandler();
        CargoEntfernenListener cargoEntfernenListener = new CargoEntfernenListener(lagerFassade,serverEventHandler);
        entfernenHandler.addListener(cargoEntfernenListener);
        CES.setEntfernenEventHandler(entfernenHandler);

        EventHandler abrufenHandler = new EventHandler();
        AbrufenListener abrufenListener = new AbrufenListener(lagerFassade,serverEventHandler);
        abrufenHandler.addListener(abrufenListener);
        CES.setAbrufenEventHandler(abrufenHandler);

        EventHandler inspectionHandler = new EventHandler();
        InspectionListener inspectionListener = new InspectionListener(lagerFassade,serverEventHandler);
        inspectionHandler.addListener(inspectionListener);
        CES.setInspectionEventHandler(inspectionHandler);

        EventHandler persistenceHandler = new EventHandler();
        PersistenceListener persistenceListener = new PersistenceListener(lagerFassade);
        persistenceHandler.addListener(persistenceListener);
        CES.setPersistenceEventHandler(persistenceHandler);

        EventHandler kundeEntfernenHandler = new EventHandler();
        KundeEntfernenListener kundeEntfernenListener = new KundeEntfernenListener(lagerFassade,serverEventHandler);
        kundeEntfernenHandler.addListener(kundeEntfernenListener);
        CES.setKundeEntfernenHandler(kundeEntfernenHandler);

        Server server = new Server(12345, CES);
        ServerListener serverListener = new ServerListener(server);
        serverEventHandler.addListener(serverListener);
        server.start();









    }
}

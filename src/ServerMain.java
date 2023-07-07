import eventSystem.infrastructure.EventHandler;
import eventSystem.listener.*;
import eventSystem.viewControl.ConsoleEventSystem;
import server.Server;
import verwaltung.Lager;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        Lager lager = new Lager(5);
        ConsoleEventSystem CES = new ConsoleEventSystem();

        EventHandler kundeEinfuegenHandler = new EventHandler();
        KundeEinfuegenListener kundeEinfuegenListener = new KundeEinfuegenListener(lager);
        kundeEinfuegenHandler.addListener(kundeEinfuegenListener);
        CES.setKundeEinfuegenHandler(kundeEinfuegenHandler);

        EventHandler storableCargoEinfuegenHandler = new EventHandler();
        StorableCargoEinfuegenListener storableCargoEinfuegenListener = new StorableCargoEinfuegenListener(lager);
        storableCargoEinfuegenHandler.addListener(storableCargoEinfuegenListener);
        CES.setStorableCargoEinfuegenHandler(storableCargoEinfuegenHandler);

        EventHandler entfernenHandler = new EventHandler();
        EntfernenListener entfernenListener = new EntfernenListener(lager);
        entfernenHandler.addListener(entfernenListener);
        CES.setEntfernenEventHandler(entfernenHandler);

        EventHandler abrufenHandler = new EventHandler();
        AbrufenListener abrufenListener = new AbrufenListener(lager);
        abrufenHandler.addListener(abrufenListener);
        CES.setAbrufenEventHandler(abrufenHandler);

        EventHandler inspectionHandler = new EventHandler();
        InspectionListener inspectionListener = new InspectionListener(lager);
        inspectionHandler.addListener(inspectionListener);
        CES.setInspectionEventHandler(inspectionHandler);

        Server server = new Server(12345, CES);








    }
}

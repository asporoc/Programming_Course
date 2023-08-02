import client.Client;
import client.NetListener;
import eventSystem.infrastructure.EventHandler;
import logger.LogUtil;
import viewControl.ConsoleEventSystem;
import server.*;
import verwaltung.LagerFassade;


public class main {
    public static void main(String[] args) throws Exception {

        ConsoleEventSystem CES = new ConsoleEventSystem();
        String[] arguments = CES.startClient();
        if(arguments[0].equals("TCP")) {
            Client client = new Client(12345, CES);
            EventHandler eventHandler = new EventHandler();
            NetListener netListener = new NetListener(client);
            eventHandler.addListener(netListener);

            CES.setAbrufenEventHandler(eventHandler);
            CES.setKundeEinfuegenHandler(eventHandler);
            CES.setStorableCargoEinfuegenHandler(eventHandler);
            CES.setEntfernenEventHandler(eventHandler);
            CES.setInspectionEventHandler(eventHandler);
            CES.setPersistenceEventHandler(eventHandler);
            CES.setKundeEntfernenHandler(eventHandler);
            CES.execute();
        }else {


            int capacity;
            try {
                capacity = Integer.parseInt(arguments[0]);
            } catch (NumberFormatException e) {
                return;
            }
            LagerFassade lagerFassade = new LagerFassade(capacity);
            if (arguments.length == 2) {
                LogUtil logSystem = new LogUtil(arguments[1]);
                LagerObserver observer = new LagerObserver(lagerFassade.getLager());
                ConsoleEventSystem testConsoleEventSystem = new ConsoleEventSystem();
                EventHandler storableCargoEinfuegenHandler = new EventHandler();

                StorableCargoEinfuegenListener storableCargoEinfuegenListener = new StorableCargoEinfuegenListener(lagerFassade,logSystem);
                storableCargoEinfuegenHandler.addListener(storableCargoEinfuegenListener);
                testConsoleEventSystem.setStorableCargoEinfuegenHandler(storableCargoEinfuegenHandler);

                EventHandler kundeEinfuegenHandler = new EventHandler();
                KundeEinfuegenListener kundeEinfuegenListener = new KundeEinfuegenListener(lagerFassade,logSystem);
                kundeEinfuegenHandler.addListener(kundeEinfuegenListener);
                testConsoleEventSystem.setKundeEinfuegenHandler(kundeEinfuegenHandler);

                EventHandler entfernenHandler = new EventHandler();
                CargoEntfernenListener cargoEntfernenListener = new CargoEntfernenListener(lagerFassade,logSystem);
                entfernenHandler.addListener(cargoEntfernenListener);
                testConsoleEventSystem.setEntfernenEventHandler(entfernenHandler);

                EventHandler abrufenHandler = new EventHandler();
                AbrufenListener abrufenListener = new AbrufenListener(lagerFassade, CES, logSystem);
                abrufenHandler.addListener(abrufenListener);
                testConsoleEventSystem.setAbrufenEventHandler(abrufenHandler);

                EventHandler inspectionHandler = new EventHandler();
                InspectionListener inspectionListener = new InspectionListener(lagerFassade,logSystem);
                inspectionHandler.addListener(inspectionListener);
                testConsoleEventSystem.setInspectionEventHandler(inspectionHandler);

                EventHandler persistenceHandler = new EventHandler();
                PersistenceListener persistenceListener = new PersistenceListener(lagerFassade,logSystem);
                persistenceHandler.addListener(persistenceListener);
                testConsoleEventSystem.setPersistenceEventHandler(persistenceHandler);

                EventHandler kundeEntfernenHandler = new EventHandler();
                KundeEntfernenListener kundeEntfernenListener = new KundeEntfernenListener(lagerFassade,logSystem);
                kundeEntfernenHandler.addListener(kundeEntfernenListener);
                testConsoleEventSystem.setKundeEntfernenHandler(kundeEntfernenHandler);

                testConsoleEventSystem.execute();
            } else if (arguments.length == 1) {

                LagerObserver observer = new LagerObserver(lagerFassade.getLager());
                ConsoleEventSystem testConsoleEventSystem = new ConsoleEventSystem();
                EventHandler storableCargoEinfuegenHandler = new EventHandler();
                EventHandler serverEventHandler = new EventHandler();

                StorableCargoEinfuegenListener storableCargoEinfuegenListener = new StorableCargoEinfuegenListener(lagerFassade);
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


    }
}

import eventSystem.infrastructure.EventHandler;
import client.NetListener;
import client.Client;
import viewControl.ConsoleEventSystem;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        ConsoleEventSystem CES = new ConsoleEventSystem();
        Client client = new Client(12345,CES);
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


    }
}

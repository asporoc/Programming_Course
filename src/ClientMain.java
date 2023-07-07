import eventSystem.infrastructure.EventHandler;
import eventSystem.listener.KundeEinfuegenListener;
import eventSystem.listener.NetListener;
import eventSystem.viewControl.Client;
import eventSystem.viewControl.ConsoleEventSystem;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        Client client = new Client(12345);
        ConsoleEventSystem CES = new ConsoleEventSystem();
        EventHandler eventHandler = new EventHandler();
        NetListener netListener = new NetListener(client);
        eventHandler.addListener(netListener);
        CES.setAbrufenEventHandler(eventHandler);
        CES.setKundeEinfuegenHandler(eventHandler);
        CES.setStorableCargoEinfuegenHandler(eventHandler);
        CES.setEntfernenEventHandler(eventHandler);
        CES.setInspectionEventHandler(eventHandler);
        CES.setPersistenceEventHandler(eventHandler);
        CES.execute();

    }
}

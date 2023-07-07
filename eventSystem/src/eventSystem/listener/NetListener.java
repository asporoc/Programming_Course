package eventSystem.listener;

import eventSystem.infrastructure.CRUDEventListener;
import eventSystem.viewControl.Client;

import java.io.IOException;
import java.util.EventObject;

public class NetListener implements CRUDEventListener {
    private Client client;
    public NetListener(Client client){
        this.client = client;
    }
    @Override
    public void onEvent(EventObject event) {
        try {
            client.eventAction(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

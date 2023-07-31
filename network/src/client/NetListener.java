package client;

import eventSystem.listener.CRUDEventListener;

import java.io.IOException;
import java.util.EventObject;

public class NetListener implements CRUDEventListener {
    private final Client client;
    public NetListener(Client client){
        this.client = client;
    }
    @Override
    public void onEvent(EventObject event) {
        try {
            client.writeEvent(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

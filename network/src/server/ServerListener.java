package server;

import eventSystem.listener.CRUDEventListener;

import java.io.IOException;
import java.util.EventObject;

public class ServerListener implements CRUDEventListener {
    private Server server;
    public ServerListener(Server server){
        this.server = server;
    }
    @Override
    public void onEvent(EventObject event) {
        try {
            server.writeEvent(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

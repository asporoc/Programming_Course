package client;

import eventSystem.infrastructure.*;
import eventSystem.viewControl.ConsoleEventSystem;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EventObject;

public class Client {
    private EventObject eventObject;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ConsoleEventSystem CES;
    public Client(int port,ConsoleEventSystem CES){ // kaskadierender aufruf implementieren siehe persistence
        this.CES = CES;
        try{
                socket = new Socket("localhost", port);
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                oos = new ObjectOutputStream(dos);
                ois = new ObjectInputStream(dis);



        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeEvent(EventObject obj) throws IOException {
        eventObject = null;
        serialize(oos, obj);
        while(eventObject == null){
            try {
                eventObject =  (EventObject) ois.readObject();
                handleEvent(eventObject);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static void serialize(ObjectOutputStream oos, EventObject event) throws IOException {
        oos.writeObject(event);
        oos.flush();
    }
    private void handleEvent(EventObject event) {
        if (event instanceof KundeEinfuegenErgebnisEvent) {

            CES.einfuegenKundeErfolgreich();
        } else if (event instanceof StorableCargoEinfuegenErgebnisEvent) {
            if(((StorableCargoEinfuegenErgebnisEvent) event).getErgebnis()){
                CES.einfuegenCargoErfolgreich();
            }else{
                CES.einfuegenGescheitert();
            }

        } else if (event instanceof InspectionEvent) {
            CES.inspectionEventHandler.handleEvent(event);
        } else if (event instanceof AbrufenEvent) {
            CES.abrufenEventHandler.handleEvent(event);
        } else if (event instanceof CargoEntfernenEvent) {
            CES.entfernenEventHandler.handleEvent(event);
        } else if (event instanceof PersistenceEvent) {
            CES.persistenceEventHandler.handleEvent(event);
        } else if (event instanceof KundeEntfernenEvent) {
            CES.kundeEntfernenHandler.handleEvent(event);
        }
    }
}

package server;

import eventSystem.infrastructure.*;
import eventSystem.viewControl.ConsoleEventSystem;
import verwaltung.LagerFassade;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.EventObject;


public class Server {
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ConsoleEventSystem CES;

    public Server(int port, ConsoleEventSystem CES) throws IOException {
        this.CES = CES;
        try(ServerSocket serverSocket = new ServerSocket(port)){
            try(Socket socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){
                System.out.println("Client connected: "+ socket.getInetAddress() + ":"+ socket.getPort());
                ObjectInputStream ois = new ObjectInputStream(dis);
                ObjectOutputStream oos = new ObjectOutputStream(dos);


                while(true){
                    EventObject event = (EventObject)ois.readObject();
                    if(event instanceof KundeEinfuegenEvent){
                        CES.kundeEinfuegenHandler.handleEvent(event);

                    }else if(event instanceof StorableCargoEinfuegenEvent){
                        CES.storableCargoEinfuegenHandler.handleEvent(event);

                    } else if (event instanceof InspectionEvent) {
                        CES.inspectionEventHandler.handleEvent(event);

                    } else if (event instanceof AbrufenEvent) {
                        CES.abrufenEventHandler.handleEvent(event);

                    } else if (event instanceof EntfernenEvent) {
                        CES.entfernenEventHandler.handleEvent(event);

                    } else if (event instanceof PersistenceEvent) {
                        CES.persistenceEventHandler.handleEvent(event);
                    }


                }

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void writeEvent(EventObject obj) throws IOException {
        serialize(oos, obj);
    }
    static void serialize(ObjectOutputStream oos, EventObject event) throws IOException {
        oos.writeObject(event);
        oos.flush();
    }
}

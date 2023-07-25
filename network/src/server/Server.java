package server;

import eventSystem.infrastructure.*;
import eventSystem.viewControl.ConsoleEventSystem;

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
    private int port;

    public Server(int port, ConsoleEventSystem CES) {
        this.CES = CES;
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for clients...");
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client connected: " + socket.getInetAddress() + ":" + socket.getPort());
                    setupStreams(socket);
                    startEventLoop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupStreams(Socket socket) throws IOException {
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        oos = new ObjectOutputStream(dos);
        ois = new ObjectInputStream(dis);
    }

    private void startEventLoop() throws IOException {
        while (true) {
            try {
                EventObject event = (EventObject) ois.readObject();
                handleEvent(event);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleEvent(EventObject event) {
        if (event instanceof KundeEinfuegenEvent) {
            CES.kundeEinfuegenHandler.handleEvent(event);
        } else if (event instanceof StorableCargoEinfuegenEvent) {
            CES.storableCargoEinfuegenHandler.handleEvent(event);
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

    public void writeEvent(EventObject obj) throws IOException {
        serialize(oos, obj);
    }

    static void serialize(ObjectOutputStream oos, EventObject event) throws IOException {
        oos.writeObject(event);
        oos.flush();
    }
}


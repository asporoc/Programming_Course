package server;

import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.KundeEinfuegenEvent;
import eventSystem.infrastructure.StorableCargoEinfuegenEvent;
import eventSystem.viewControl.ConsoleEventSystem;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.EventObject;


public class Server {

    public Server(int port, ConsoleEventSystem CES) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            try(Socket socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){
                System.out.println("Client connected: "+ socket.getInetAddress() + ":"+ socket.getPort());
                ObjectInputStream ois = new ObjectInputStream(dis);


                while(true){
                    EventObject event = (EventObject)ois.readObject();
                    if(event instanceof KundeEinfuegenEvent){
                        CES.kundeEinfuegenHandler.handleEvent(event);
                    }
                    if(event instanceof StorableCargoEinfuegenEvent){
                        CES.storableCargoEinfuegenHandler.handleEvent(event);
                    }


                }

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

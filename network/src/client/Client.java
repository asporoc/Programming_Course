package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EventObject;

public class Client {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    public Client(int port){ // kaskadierender aufruf implementieren siehe persistence
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
        serialize(oos, obj);
    }
    static void serialize(ObjectOutputStream oos, EventObject event) throws IOException {
        oos.writeObject(event);
        oos.flush();
    }
}

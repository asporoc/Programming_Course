package eventSystem.viewControl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EventObject;

public class Client {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    public Client(int port){
        try{
                socket = new Socket("localhost", port);
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                oos = new ObjectOutputStream(dos);


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void eventAction(EventObject obj) throws IOException {
        oos.writeObject(obj);
        oos.flush();
    }
}

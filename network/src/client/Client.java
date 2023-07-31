package client;

import eventSystem.infrastructure.*;
import viewControl.ConsoleEventSystem;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EventObject;

public class Client {
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;
    private final ConsoleEventSystem CES;
    public Client(int port,ConsoleEventSystem CES){ // kaskadierender aufruf implementieren siehe persistence
        this.CES = CES;
        try{
            Socket socket = new Socket("localhost", port);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                oos = new ObjectOutputStream(dos);
                ois = new ObjectInputStream(dis);



        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeEvent(EventObject obj) throws IOException {
        EventObject eventObject = null;
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
        } else if (event instanceof InspectionErgebnisEvent) {
            if(((InspectionErgebnisEvent) event).getNewInspectionDate()!=null){
                CES.inspectionErfolgreich(((InspectionErgebnisEvent) event).getNewInspectionDate());
            }else{
                CES.inspectiongGescheitert();
            }
        } else if (event instanceof CustomerAbrufenEvent) {
            String[] cargoCustomer = ((CustomerAbrufenEvent)event).getCustomerCargo();
            for(String customer: cargoCustomer){
                CES.customerAbrufen(customer);
            }

        } else if (event instanceof HazardsAbrufenEvent) {
            CES.hazardsAbrufen(((HazardsAbrufenEvent)event).getHazards(),((HazardsAbrufenEvent)event).getOption());
        } else if (event instanceof PersistenceErgebnisEvent) {
        } else if (event instanceof KundeEntfernenErgebnisEvent) {
            CES.kundeEntfernt(((KundeEntfernenErgebnisEvent)event).getErgebnis());
        }else if(event instanceof CargoEntfernenErgebnisEvent){
            CES.cargoEntfernt(((CargoEntfernenErgebnisEvent)event).getErgebnis());
        }else if(event instanceof CargosAbrufenEvent){
            CES.cargosAbrufen(((CargosAbrufenEvent)event).getCargos());
        }else{

        }
    }
}

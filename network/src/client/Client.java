package client;

import administration.Customer;
import cargo.Hazard;
import cargos.storableCargo;
import eventSystem.infrastructure.*;
import verwaltung.Kunde;
import verwaltung.Lager;
import viewControl.ConsoleEventSystem;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EventObject;
import java.util.List;

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
        } else if (event instanceof InspectionErgebnisEvent) {
            if(((InspectionErgebnisEvent) event).getNewInspectionDate()!=null){
                CES.inspectionErfolgreich(((InspectionErgebnisEvent) event).getNewInspectionDate());
            }else{
                CES.inspectiongGescheitert();
            }
        } else if (event instanceof CustomerAbrufenEvent) {
            List<Customer> customerList = ((CustomerAbrufenEvent)event).getLagerFassade().getLager().getCustomerList();
            Lager lager = ((CustomerAbrufenEvent)event).getLagerFassade().getLager();
            for(Customer customer: customerList){
                int i = 0;
                for(int y=0;y<lager.getCargoList().size();y++){
                    if(lager.getCargoList().get(y).getOwner().getName().equals(customer.getName())){
                        i++;
                    }
                }
                CES.customerAbrufen(customer.getName(),i);
            }

        } else if (event instanceof HazardsAbrufenEvent) {
            int[] numbHazards = new int[4];
            Lager lager = ((HazardsAbrufenEvent)event).getLagerFassade().getLager();
            for(int i = 0; i <lager.getCargoList().size();i++){
                if(lager.getCargoList().get(i).getHazards().contains(Hazard.explosive)){
                    numbHazards[0]+=1;
                }
                if(lager.getCargoList().get(i).getHazards().contains(Hazard.flammable)){
                    numbHazards[1]+=1;
                }
                if(lager.getCargoList().get(i).getHazards().contains(Hazard.radioactive)){
                    numbHazards[2]+=1;
                }
                if(lager.getCargoList().get(i).getHazards().contains(Hazard.toxic)){
                    numbHazards[3]+=1;
                }

            }
            CES.hazardsAbrufen(numbHazards);
        } else if (event instanceof PersistenceEvent) {
            CES.persistenceEventHandler.handleEvent(event);
        } else if (event instanceof KundeEntfernenEvent) {
            CES.kundeEntfernenHandler.handleEvent(event);
        }
    }
}

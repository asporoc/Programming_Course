package verwaltung;

import administration.Customer;
import cargo.Hazard;
import cargos.dryBulkCargoImpl;
import cargos.storableCargo;

import java.math.BigDecimal;
import java.util.*;


public class Lager extends Observable {
    private final Object monitor = new Object();
    private List<Customer> customerList = new LinkedList<>();



    private HashMap<Integer,dryBulkCargoImpl> cargoList = new HashMap<>();

    private int maxsize;
    private boolean full = false;
    int used;
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Lager(int maxsize){
        this.maxsize = maxsize;
        if(maxsize==0){
            full = true;
        }
    }
    public Lager(){
        this(10);
    }


    public <T extends storableCargo> boolean einfuegen(String einfuegenString) {
        boolean fragile;
        boolean pressurized; //parsen nicht teil der Logic gehört zum view! Warum überhaupt parsen?
        String[] text = einfuegenString.split(" ");
        if (full) {
            return false;
        }
        if (text.length > 3 && text.length < 11) {
            Hazard[] hazards = new Hazard[0];
            String value = null;

            if (text[2].contains(",")) {
                String[] hazardText = text[2].split(",");
                value = hazardText[0];
                hazards = new Hazard[hazardText.length];
                for (int i = 0; i < hazards.length; i++) {
                    switch (hazardText[i]) {
                        case "flammable":
                            hazards[i] = Hazard.flammable;
                            break;
                        case "toxic":
                            hazards[i] = Hazard.toxic;
                            break;
                        case "radioactive":
                            hazards[i] = Hazard.radioactive;
                            break;
                        case "explosive":
                            hazards[i] = Hazard.explosive;
                            break;
                        default:
                    }
                }
            }else{
                value = text[2];
            }
            dryBulkCargoImpl cargo = new dryBulkCargoImpl(text[0], text[1], new BigDecimal(value), hazards,Boolean.parseBoolean(text[text.length-3]),Boolean.parseBoolean(text[text.length-2]),Integer.parseInt(text[text.length-1]),einfuegenString);

            for (Customer o : customerList) {
                if (o.getName().equals(cargo.getOwner().getName())) {

                    if (cargo != null) {
                        for (int location = 0; location < maxsize; location++) {
                            if (cargoList.get(location) == null) {
                                if (location == maxsize - 1) {
                                    full = true;
                                    setChanged();
                                    notifyObservers("Das Lager ist jetzt Voll.");
                                }
                                cargoList.put(location, cargo);
                                cargo.setStorageLocation(location);
                                setChanged();
                                notifyObservers("Das Frachtstück vom Typ: "+ text[0] + " wurde eingefügt von Kunde: " +text[1]);
                                return true;
                            }
                        }
                    } else {
                        setChanged();
                        notifyObservers("Das neue Frachtstück konnte nicht eingefügt werden.");
                        return false;
                    }

                }
            }
            return false;
        } else if (text.length == 1) {
            for (Customer c : customerList) {
                if (c.getName().equals(text[0])) {
                    return false;
                }
            }
            customerList.add(new Kunde(text[0]));
            setChanged();
            notifyObservers("Ein neuer Kunde mit dem Namen "+ text[0] +" wurde der Liste von Kunden hinzugefügt.");
            return true;
        } else {
            setChanged();//so werden observer nicht benutzt dies wäre ein fall für eventhandler
            notifyObservers("Der Kunde konnte der Kunden Liste nicht hinzugefügt werden.");
            return false;
        }
    }

    public Object abrufen() {
        return cargoList.clone(); //cargoList nicht übergeben zerstört kapselung!!
    }
    public dryBulkCargoImpl abrufen(int storageLocation){
        return cargoList.get(storageLocation);
    }

    public boolean entfernen(int storageLocation) {
        try {
            if (cargoList.get(storageLocation) == null && cargoList.size() < 1) {
                setChanged();
                notifyObservers("Der eingegebene Lagerort: "+storageLocation+" enthält kein Frachtstück.");
                return false;
            }
            cargoList.remove(storageLocation);
            full = false;
            setChanged();
            notifyObservers("Das Frachtstück was sich an Lagerort: "+ storageLocation+ " befunden hat wurde erfolgreich entfernt.");
            return true;//notify observer so falsch kein arg
        } catch (Exception e) {
            return false;
        }
    }

    public Date inspection(int storageLocation) {
        Date newDate = new Date();
        (cargoList.get(storageLocation)).lastInspectionDate = newDate;
        setChanged();
        notifyObservers("Letztes Inspektionsdatum wurde neu gesetzt: "+newDate);
        return newDate;
    }

    public HashMap<Integer, dryBulkCargoImpl> getCargoList() {
        return cargoList;
    }

    public int getMaxsize() {
        return maxsize;
    }
}


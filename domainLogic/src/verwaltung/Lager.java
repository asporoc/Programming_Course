package verwaltung;

import administration.Customer;
import cargo.Hazard;
import cargos.DryBulkCargoImpl;
import cargos.UtilityClass;
import cargos.dryBulkCargoImpl;
import cargos.storableCargo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


public class Lager implements Serializable {
    private transient Object monitor = new Object();
    private List<Customer> customerList = new LinkedList<>();
    private HashMap<Integer,storableCargo> cargoList = new HashMap<>();
    private int maxsize;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Lager(int maxsize){
        this.maxsize = maxsize;
        if(maxsize==0){
        }
    }
    public Lager(){
        this(10);
    }


    public boolean einfuegen(String name){
        for (Customer c : customerList) {
            if (c.getName().equals(name)) {
                return false;
            }

        }
        customerList.add(new Kunde(name));
        return true;
    }
    public <T extends storableCargo> boolean einfuegen(storableCargo cargo) {
        boolean fragile;
        boolean pressurized; //parsen nicht teil der Logic gehört zum view! Warum überhaupt parsen?
        //String[] text = einfuegenString.split(" ");
        if (cargoList.size()==maxsize) {
            return false;
        }
        /*if (text.length > 3 && text.length < 11) {
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
*/
            for (Customer o : customerList) {
                if (o.getName().equals(cargo.getOwner().getName())) {

                    if (cargo != null) {
                        for (int location = 0; location < maxsize; location++) {
                            if (cargoList.get(location) == null) {
                                if (location == maxsize - 1) {
                                }
                                cargoList.put(location, cargo);
                                UtilityClass.setStorageLocation(cargo,location);

                                //cargo.setStorageLocation(location);
                                return true;
                            }
                        }
                    } else {
                        return false;
                    }

                }
            }
            return false;
        }/* else if (text.length == 1) {
            for (Customer c : customerList) {
                if (c.getName().equals(text[0])) {
                    return false;
                }
            }
            customerList.add(new Kunde(text[0]));
            return true;
        } else {
            return false;
        }
    }*/

    public Object abrufen() {
        return cargoList.clone();
    }
    public storableCargo abrufen(int storageLocation){
        return cargoList.get(storageLocation);
    }

    public boolean entfernen(int storageLocation) {
        try {
            if (cargoList.get(storageLocation) == null && cargoList.size() < 1) {
                return false;
            }
            cargoList.remove(storageLocation);
            ;
            return true;//notify observer so falsch kein arg
        } catch (Exception e) {
            return false;
        }
    }

    public Date inspection(int storageLocation) {
        Date newDate = new Date();
        UtilityClass.setLastInspectionDate((cargoList.get(storageLocation)),newDate);
        //(cargoList.get(storageLocation)).setLastInspectionDate(newDate);
        return newDate;
    }

    public HashMap<Integer, storableCargo> getCargoList() {
        return cargoList;
    }

    public int getMaxsize() {
        return maxsize;
    }

    public void setMonitor(Object monitor) {
        this.monitor = monitor;
    }
}


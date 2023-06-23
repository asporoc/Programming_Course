package verwaltung;

import administration.Customer;
import cargos.UtilityClass;
import cargos.storableCargo;

import java.io.Serializable;
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
        boolean pressurized;
        if (cargoList.size()==maxsize) {
            return false;
        }
            for (Customer o : customerList) {
                if (o.getName().equals(cargo.getOwner().getName())) {
                    if (cargo != null) {
                        for (int location = 0; location < maxsize; location++) {
                            if (cargoList.get(location) == null) {
                                if (location == maxsize - 1) {
                                    cargoList.put(location, cargo);
                                    UtilityClass.setStorageLocation(cargo, location);
                                    return true;
                                }
                            }
                        }
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }

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


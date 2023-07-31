package verwaltung;

import administration.Customer;
import cargos.storableCargo;

import java.io.Serializable;
import java.util.*;


public class Lager extends Observable implements Serializable, Cloneable {
    transient Object monitor = new Object();
    private final List<Customer> customerList = new LinkedList<>();
    private final HashMap<Integer, storableCargo> cargoList = new HashMap<>();

    private final int maxsize;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Lager(int maxsize) {
        this.maxsize = maxsize;
        if (maxsize == 0) {
        }
    }

    public Lager() {
        this(10);
    }


    public boolean einfuegen(Kunde kunde) {
        for (Customer c : customerList) {
            if (c.getName().equals(kunde.getName())) {
                return false;
            }

        }
        synchronized (monitor){
        customerList.add(kunde);}
        return true;
    }

    public <T extends storableCargo> boolean einfuegen(storableCargo cargo) {

        if (cargoList.size() == maxsize) {
            return false;
        }
        if (cargo != null) {
        for (Customer o : customerList) {
            if (o.getName().equals(cargo.getOwner().getName())) {
                    for (int location = 0; location < maxsize; location++) {
                        if (cargoList.get(location) == null) {
                            synchronized (monitor){
                            cargoList.put(location, cargo);}
                            this.setChanged();
                            this.notifyObservers();
                            //UtilityClass.setStorageLocation(cargo, location);
                            cargo.setStorageLocation(location);
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }

    public Object abrufen() throws CloneNotSupportedException {
        return this.clone();
    }

    public storableCargo abrufen(int storageLocation) {
        return cargoList.get(storageLocation);
    }

    public boolean entfernen(int storageLocation) {
            if (cargoList.get(storageLocation) == null && cargoList.size() < 1) {
                return false;
            }
            synchronized (monitor){
            cargoList.remove(storageLocation);}

            return true;
    }
    public boolean entfernen(String name){
        for(Customer customer: customerList){
            if(customer.getName().equals(name)){
                synchronized (monitor){
                customerList.remove(customer);}
                return true;
            }
        }
        return false;
    }

    public Date inspection(int storageLocation) {
        Date newDate = new Date();
        //UtilityClass.setLastInspectionDate((cargoList.get(storageLocation)), newDate);
        (cargoList.get(storageLocation)).setLastInspectionDate(newDate);
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

    public void setStorageLocation(int sourceLocation, int targetLocation) {
        storableCargo sourceCargo = abrufen(sourceLocation);
        storableCargo targetCargo = abrufen(targetLocation);

        if (sourceCargo != null && targetCargo != null) {
            getCargoList().put(sourceLocation, targetCargo);
            getCargoList().put(targetLocation, sourceCargo);
        }
    }


}



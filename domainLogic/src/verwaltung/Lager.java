package verwaltung;

import administration.Customer;
import cargos.dryBulkCargoImpl;
import cargos.storableCargo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Lager{
    public List<Customer> customerList = new ArrayList<>();
    //anstelle von List struktur eine Map implementieren...
    public HashMap<Integer,storableCargo> cargoList = new HashMap<>();
    private ArrayList<Integer> freeStorageLocations = new ArrayList<>();
    public int maxsize;
    boolean full = false;
    int used;


    public Lager(int maxsize){
        this.maxsize = maxsize;
        if(maxsize==0){
            full = true;
        }
        freeStorageLocations = storageLocationsInit(maxsize);

    }
    public Lager(){
        this(10);
    }


    public <T extends storableCargo> boolean einfuegen(T cargo) {
        for (Customer o : customerList) {
            if (o.getName().equals(cargo.getOwner().getName())) {
                if (cargo != null && !full) {
                    //for(int locations: freeStorageLocations){
                    int storageLocation = freeStorageLocations.get(0);
                    cargoList.put(storageLocation, cargo);
                    ((dryBulkCargoImpl)cargo).setStorageLocation(storageLocation);
                    freeStorageLocations.remove(0);
                    if (freeStorageLocations.size() == 0) {
                        full = true;
                    }
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean einfuegen(String name){
        for(Customer c: customerList){
            if(c.getName().equals(name)){
                return false;
            }
        }
        customerList.add(new Kunde(name));
        return true;
    }

    public HashMap<Integer, storableCargo> abrufen() {
        System.out.println(cargoList.toString());
        return cargoList;
    }
    public storableCargo abrufen(int storageLocation){
        return cargoList.get(storageLocation);
    }

    public boolean entfernen(int storageLocation) {
        try {
            cargoList.remove(storageLocation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Date inspection(storableCargo cargo) {
        //cast cargo as drybulkcargo because only implemented in dryBulkCargo
        ((dryBulkCargoImpl)cargo).lastInspectionDate = new Date();

        return new Date();
    }
    private ArrayList<Integer> storageLocationsInit(int maxsize){
        for(int i = 0; i<maxsize;i++){
            freeStorageLocations.add(i+1);
        }
        return freeStorageLocations;
    }

}


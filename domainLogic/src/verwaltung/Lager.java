package verwaltung;

import administration.Customer;
import cargos.dryBulkCargoImpl;
import cargos.storableCargo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Lager{
    public List<Customer> customerList = new ArrayList<>();
    //anstelle von List struktur eine Map implementieren...
    public List<storableCargo> cargoList = new ArrayList<>();
    public int maxsize;
    boolean full = false;
    int used;


    public Lager(int maxsize){
        this.maxsize = maxsize;
        if(maxsize==0){
            full = true;
        }
    }
    public Lager(){
        this(10);
    }


    public <T extends storableCargo> boolean einfuegen(T cargo) {
        if(cargo != null && !full){
            cargoList.add(cargo);
            if (cargoList.size() == maxsize) {
                full = true;
            }
            return true;

        }else{
            return false;
        }
        /*if (!full) {
            //cast cargo as drybulkcargo because storageLocation only implemented in dryBulkCargo
            ((dryBulkCargoImpl)cargo).storageLocation=cargoList.size();
            cargoList.add(cargo);

            if (cargoList.size() == maxsize) {
                full = true;
            }
            return true;
        } else {
            return false;
        }*/
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

    public List<storableCargo> abrufen() {
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

}


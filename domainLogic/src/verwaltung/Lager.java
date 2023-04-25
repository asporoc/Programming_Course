package verwaltung;

import administration.Customer;
import cargos.dryBulkCargo;
import cargos.storableCargo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Lager{
    public List<Customer> customerList;
    public List<storableCargo> cargoList = new ArrayList<>();
    public int maxsize;
    boolean full = false;
    int used;


    public Lager(int maxsize){
        this.maxsize = maxsize;
    }
    public Lager(){
        this(10);
    }


    public boolean einfuegen(storableCargo cargo) {
        if (!full) {
            //cast cargo as drybulkcargo because only implemented in dryBulkCargo
            ((dryBulkCargo)cargo).storageLocation=cargoList.size();
            cargoList.add(cargo);

            if (cargoList.size() == maxsize) {
                full = true;
            }
            return true;
        } else {
            return false;
        }
    }

    public List<storableCargo> abrufen() {
        /*for(storableCargo x : cargoList){
            int i = 0;
            System.out.println(cargoList.get(i));
            i++;
        }*/
        return cargoList;
    }

    public boolean entfernen(storableCargo cargo) {
        try {
            cargoList.remove(cargo.getStorageLocation());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Date inspection(storableCargo cargo) {
        //cast cargo as drybulkcargo because only implemented in dryBulkCargo
        ((dryBulkCargo)cargo).lastInspectionDate = new Date();

        return new Date();
    }
}


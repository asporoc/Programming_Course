package verwaltung;

import administration.Customer;
import cargo.Cargo;
import cargo.DryBulkCargo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lager {
    public List<Customer> customerList;
    public List<Cargo> cargoList;
    int maxsize;
    boolean full;
    int used;
    public boolean einfuegen(DryBulkCargo cargo){
        try{
            cargoList.add(cargo);
        }catch(Exception e){
            return false;
        }
        return true;
    }
    public List<DryBulkCargo> abrufen(){
        return new ArrayList<>();
    }
    public boolean entfernen(DryBulkCargo Cargo){
        throw new IllegalStateException();
    }
    public Date inspection(DryBulkCargo Cargo){
        throw new IllegalStateException();
    }
}

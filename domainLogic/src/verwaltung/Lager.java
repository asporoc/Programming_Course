package verwaltung;

import administration.Customer;
import administration.Storable;
import cargo.Cargo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Lager<T extends Storable & Cargo> {
    public List<Customer> customerList;
    public List<T> cargoList = new ArrayList<>();
    int maxsize;
    boolean full = false;
    int used;

    public boolean einfuegen(T cargo) {
        if (!full) {
            cargo.setStorageLocation(cargoList.size());
            cargoList.add(cargo);

            if (cargoList.size() == maxsize) {
                full = true;
            }
            return true;
        } else {
            return false;
        }
    }

    public List<T> abrufen() {
        return cargoList;
    }

    public boolean entfernen(T cargo) {
        try {
            cargoList.remove(cargo.getStorageLocation());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Date inspection() {

        return new Date();
    }
}


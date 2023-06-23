package JOS;

import administration.Customer;
import cargos.storableCargo;
import verwaltung.Lager;

import java.util.HashMap;
import java.util.LinkedList;

import java.io.Serializable;
import java.util.List;

public class josItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private int maxsize;
    private List<Customer> customerList;
    private HashMap<Integer, storableCargo> cargoList;

    public josItem(Lager lager, storableCargo cargo) {
        //super();
        this.maxsize = lager.getMaxsize();
        this.customerList = new LinkedList<>(lager.getCustomerList());
        this.cargoList = new HashMap<>(lager.getCargoList());
    }

    public int getMaxsize() {
        return maxsize;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public HashMap<Integer, storableCargo> getCargoList() {
        return cargoList;
    }
}


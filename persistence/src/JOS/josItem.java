package JOS;

import administration.Customer;
import cargos.dryBulkCargoImpl;
import cargos.storableCargo;
import verwaltung.Lager;

import java.io.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import cargos.dryBulkCargoImpl;
import verwaltung.Lager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
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


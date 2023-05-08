package verwaltung;

import administration.Customer;
import cargo.Hazard;
import cargos.dryBulkCargoImpl;
import cargos.storableCargo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Lager{
    public List<Customer> customerList = new ArrayList<>();
    
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


    public <T extends storableCargo> boolean einfuegen(String einfuegenString) {
        String[] text = einfuegenString.split(" ");
        if (text.length > 3 && text.length < 11) {
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
        dryBulkCargoImpl cargo = new dryBulkCargoImpl(text[0], text[1], new BigDecimal(value), hazards,Integer.parseInt(text[text.length-1]));

            for (Customer o : customerList) {
                if (o.getName().equals(cargo.getOwner().getName())) {
                    if (cargo != null && !full) {
                        //for(int locations: freeStorageLocations){
                        int storageLocation = freeStorageLocations.get(0);
                        cargoList.put(storageLocation, cargo);
                        ((dryBulkCargoImpl) cargo).setStorageLocation(storageLocation);
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
        } else if (text.length == 1) {
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
            freeStorageLocations.add(storageLocation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Date inspection(int storageLocation) {
        //cast cargo as drybulkcargo because only implemented in dryBulkCargo
        ((dryBulkCargoImpl)cargoList.get(storageLocation)).lastInspectionDate = new Date();
        return new Date();
    }
    private ArrayList<Integer> storageLocationsInit(int maxsize){
        for(int i = 0; i<maxsize;i++){
            freeStorageLocations.add(i+1);
        }
        return freeStorageLocations;
    }

}


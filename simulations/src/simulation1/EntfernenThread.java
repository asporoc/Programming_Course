package simulation1;

import Utility.generateRandomCargo;
import verwaltung.Lager;

import java.util.Observable;

public class EntfernenThread extends Observable implements Runnable {
    final Lager lager;
    private static final Object monitor = new Object();
    public EntfernenThread(Lager lager){
        this.lager = lager;

    }

    @Override
    public void run() {
        while (true) {
            int storageLocation = generateRandomCargo.randStorageLocation(lager);
            synchronized (monitor){
                boolean i=lager.entfernen(storageLocation);
                if(i){
                    setChanged();
                    notifyObservers("Objekt wurde erfolgreich entfernt");
                }else{
                    setChanged();
                    notifyObservers("Objekt konnte nicht entfernt werden.");
                }
            }


        }

    }
}
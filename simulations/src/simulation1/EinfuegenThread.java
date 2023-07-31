package simulation1;

import Utility.generateRandomCargo;
import verwaltung.Lager;

import java.util.Observable;

public class EinfuegenThread extends Observable implements Runnable {
    private static final Object monitor = new Object();
    private final Lager lager;
    public EinfuegenThread(Lager lager){
        this.lager = lager;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (monitor) {
                boolean i = lager.einfuegen(generateRandomCargo.generateRandCargo());
                if (i) {
                    setChanged();
                    notifyObservers("Objekt wurde erfolgreich eingefuegt");
                } else {
                    setChanged();
                    notifyObservers("Objekt konnte nicht eingefuegt werden.");
                }

            }
        }

    }
}

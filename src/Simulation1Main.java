import cargo.Hazard;
import simulation1.SimulationObserver;
import verwaltung.Lager;

import java.util.Observable;
import java.util.Random;

public class Simulation1Main {
    private static final Object monitor = new Object();
    public String[] cargoTypes = {"DryBulkCargo", "DryBulkUnitisedCargo", "LiquidBulkCargo", "LiquidBulkAndUnitisedCargo", "LiquidAndDryBulkCargo", "UnitisedCargo"};
    public String[] kunden = {"Heinz", "Jonathan", "Lennart", "Hugo", "Bernhard", "Maurice", "Hermann"};
    public Hazard[] allHazards = {Hazard.flammable, Hazard.toxic, Hazard.radioactive, Hazard.explosive};


    public static
    class simulation1Create extends Observable implements Runnable {
        private Lager lager;
        private Simulation1Main outerInstance; // Reference to the outer class instance

        @Override
        public void run() {

            while (true) {

                String insertString = generateRandCargo(outerInstance.cargoTypes, outerInstance.kunden, outerInstance.allHazards);
                synchronized (monitor) {
                    boolean i = lager.einfuegen(insertString);
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

        simulation1Create(Lager lager, Simulation1Main outerInstance) {
            this.lager = lager;
            this.outerInstance = outerInstance;
        }
    }

    public static class simulation1Delete extends Observable implements Runnable {
        private Lager lager;
        simulation1Delete(Lager lager){
            this.lager = lager;

        }
        @Override
        public void run() {

            while (true) {
                int storageLocation = randStorageLocation(lager);
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

    public static String generateRandCargo(String[] cargos, String[] kunden, Hazard[] allHazards) {
        boolean[] hazBool = new boolean[4];
        int numberOfHazards = 0;
        Random random = new Random();
        String cargoType = cargos[random.nextInt(cargos.length)];
        String kunde = kunden[random.nextInt(kunden.length)];
        String value = String.valueOf(random.nextInt(90000));
        String hazards = "";
        String grainSize = String.valueOf(random.nextInt(20));
        for (int i = 0; i < hazBool.length; i++) {
            hazBool[i] = random.nextBoolean();
            if (hazBool[i] == true) {
                numberOfHazards++;
            }
        }
        for (int i = 0; i < numberOfHazards; i++) {
            if (numberOfHazards == 0) {
                hazards = " , "; //value,flammable
            } else {
                hazards = hazards.concat("," + allHazards[i].name());
            }
        }
        return (cargoType + " " + kunde + " " + value + hazards + " " + random.nextBoolean() + " " + random.nextBoolean()+ " " + grainSize);
    }
    public static int randStorageLocation(Lager lager){
        Random random = new Random();
        return random.nextInt((lager.getMaxsize()-1+1));
    }


    public static void main(String[] args) {
        Lager simuLager = new Lager();
        Simulation1Main simulation1Main = new Simulation1Main();
        for(String kunde : simulation1Main.kunden){
            simuLager.einfuegen(kunde);
        }


        simulation1Create createSimulation = new simulation1Create(simuLager, simulation1Main);
        simulation1Delete deleteSimulation = new simulation1Delete(simuLager);
        Thread create = new Thread(createSimulation);
        Thread delete = new Thread(deleteSimulation);
        SimulationObserver simuObserver = new SimulationObserver();
        createSimulation.addObserver(simuObserver);
        deleteSimulation.addObserver(simuObserver);
        create.start();
        delete.start();
    }
}


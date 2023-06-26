import cargo.Hazard;
import simulation1.SimulationObserver;
import verwaltung.Kunde;
import verwaltung.Lager;

import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation2Main {
    private static final Object monitor = new Object();
    public String[] cargoTypes = {"DryBulkCargo", "DryBulkUnitisedCargo", "LiquidBulkCargo", "LiquidBulkAndUnitisedCargo", "LiquidAndDryBulkCargo", "UnitisedCargo"};
    public String[] kunden = {"Heinz", "Jonathan", "Lennart", "Hugo", "Bernhard", "Maurice", "Hermann"};
    public Hazard[] allHazards = {Hazard.flammable, Hazard.toxic, Hazard.radioactive, Hazard.explosive};


    public static
    class simulation2Create extends Observable implements Runnable {
        private Lager lager;
        private Simulation2Main outerInstance; // Reference to the outer class instance

        @Override
        public void run() {

            while (true) {

                String insertString = generateRandCargo(outerInstance.cargoTypes, outerInstance.kunden, outerInstance.allHazards);
                synchronized (monitor) {
                    boolean i = lager.einfuegen(new Kunde(insertString));
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

        simulation2Create(Lager lager, Simulation2Main outerInstance) {
            this.lager = lager;
            this.outerInstance = outerInstance;
        }
    }

        public static class simulation2Delete extends Observable implements Runnable {
            private Lager lager;
            simulation2Delete(Lager lager){
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
        Simulation2Main simulation2Main = new Simulation2Main();
        for(String kunde : simulation2Main.kunden){
            simuLager.einfuegen(new Kunde(kunde));
        }


        Simulation2Main.simulation2Create createSimulation = new Simulation2Main.simulation2Create(simuLager, simulation2Main);
        Simulation2Main.simulation2Delete deleteSimulation = new Simulation2Main.simulation2Delete(simuLager);
        Scanner sc = new Scanner(System.in);
        int threadCount = sc.nextInt();

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread create = new Thread(createSimulation);
            Thread delete = new Thread(deleteSimulation);
            executorService.submit(createSimulation);
            executorService.submit(deleteSimulation);
        }
        SimulationObserver simuObserver = new SimulationObserver();
        createSimulation.addObserver(simuObserver);
        deleteSimulation.addObserver(simuObserver);
    }
}




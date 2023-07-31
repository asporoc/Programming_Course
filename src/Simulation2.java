import Utility.generateRandomCargo;
import simulation1.EinfuegenThread;
import simulation1.EntfernenThread;
import simulation1.SimulationObserver;
import verwaltung.Kunde;
import verwaltung.Lager;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation2 {
    public static void main(String[] args) {

        System.out.println("Threadanzahl bitte angeben: \n");
        Scanner sc = new Scanner(System.in);
        int threadCount = sc.nextInt();

        System.out.println("Lagergroesse bitte angeben: \n");
        int lagergroesse= sc.nextInt();


        Lager lager = new Lager(lagergroesse);
        String[] kunden = generateRandomCargo.getKunden();
        for(String kunde : kunden){
            lager.einfuegen(new Kunde(kunde));
        }
        EinfuegenThread einfuegenThread = new EinfuegenThread(lager);
        EntfernenThread entfernenThread = new EntfernenThread(lager);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount*2);

        for (int i = 0; i < threadCount; i++) {
            Thread create = new Thread(einfuegenThread);
            Thread delete = new Thread(entfernenThread);
            executorService.submit(einfuegenThread);
            executorService.submit(entfernenThread);
        }
        SimulationObserver simuObserver = new SimulationObserver();
        einfuegenThread.addObserver(simuObserver);
        entfernenThread.addObserver(simuObserver);
    }
}

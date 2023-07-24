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
        Lager lager = new Lager();
        String[] kunden = generateRandomCargo.getKunden();
        for(String kunde : kunden){
            lager.einfuegen(new Kunde(kunde));
        }
        EinfuegenThread einfuegenThread = new EinfuegenThread(lager);
        EntfernenThread entfernenThread = new EntfernenThread(lager);
        System.out.println("Please enter amount of threads you want to start: \n");
        Scanner sc = new Scanner(System.in);
        int threadCount = sc.nextInt();

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

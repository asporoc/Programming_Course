import simulation1.EinfuegenThread;
import simulation1.EntfernenThread;
import simulation1.SimulationObserver;
import verwaltung.Kunde;
import verwaltung.Lager;
import Utility.generateRandomCargo;

public class Simulation1 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Bitte gewuenschte Lagergroesse angeben:");
            return;
        }
        int maxsize;
        try {
            maxsize = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Ungueltige Lagergroesse angegeben");
            return;
        }

        Lager lager = new Lager(maxsize);
        String[] kunden = generateRandomCargo.getKunden();
        for(String kunde : kunden){
            lager.einfuegen(new Kunde(kunde));
        }
        EinfuegenThread einfuegenThread = new EinfuegenThread(lager);
        EntfernenThread entfernenThread = new EntfernenThread(lager);
        Thread create = new Thread(einfuegenThread);
        Thread delete = new Thread(entfernenThread);
        SimulationObserver simuObserver = new SimulationObserver();
        einfuegenThread.addObserver(simuObserver);
        entfernenThread.addObserver(simuObserver);
        create.start();
        delete.start();

    }

}

import verwaltung.Lager;



public class main {
    public static void main(String[] args) {
        Lager testLager = new Lager();
       // System.out.println(testLager.maxsize);
        /*dryBulkCargoImpl testCargo = new dryBulkCargoImpl();
        testLager.einfuegen(testCargo);
        System.out.println(testLager.abrufen());
        testLager.entfernen(testCargo);
        System.out.println(testLager);*/
        Console testConsole = new Console(testLager);
        testConsole.execute();




    }
}
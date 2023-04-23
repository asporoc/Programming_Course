import cargos.dryBulkCargo;
import verwaltung.Lager;
public class main {
    public static void main(String[] args) {
        Lager testLager = new Lager();
        System.out.println(testLager.maxsize);
        dryBulkCargo testCargo = new dryBulkCargo();
        testLager.einfuegen(testCargo);
        testLager.abrufen();
        testLager.entfernen(testCargo);
    }
}
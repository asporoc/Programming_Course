package verwaltung;

import cargo.Cargo;
import cargo.DryBulkCargo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {

    @Test
    void einfuegen() {
        Lager lagerZuTesten = new Lager();
        int a = lagerZuTesten.used;
        lagerZuTesten.einfuegen(new DryBulkCargo(){
        });
    }

    @Test
    void abrufen() {
    }
    @Test
    void abrufenLeeresLager(){
        Lager lagerZuTesten = new Lager();
        List<DryBulkCargo> result = lagerZuTesten.abrufen();
        assertEquals(0, result.size());

    }
}
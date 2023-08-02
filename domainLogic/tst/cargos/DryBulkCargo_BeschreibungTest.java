package cargos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DryBulkCargo_BeschreibungTest {

    @Test
    void getGrainSize() {
        DryBulkCargo_Beschreibung dryBulkCargoBeschreibung = new DryBulkCargo_Beschreibung(22);
        assertEquals(22,dryBulkCargoBeschreibung.getGrainSize());
    }
}
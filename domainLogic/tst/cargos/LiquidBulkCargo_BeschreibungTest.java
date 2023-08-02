package cargos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiquidBulkCargo_BeschreibungTest {

    @Test
    void isPressurized() {
        LiquidBulkCargo_Beschreibung liquidBulkCargoBeschreibung = new LiquidBulkCargo_Beschreibung(true);
        assertTrue(liquidBulkCargoBeschreibung.isPressurized());
    }
}
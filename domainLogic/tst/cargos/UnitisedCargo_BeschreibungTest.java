package cargos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitisedCargo_BeschreibungTest {

    @Test
    void isFragile() {
        UnitisedCargo_Beschreibung unitisedCargoBeschreibung = new UnitisedCargo_Beschreibung(true);
        assertTrue(unitisedCargoBeschreibung.isFragile());
    }
}
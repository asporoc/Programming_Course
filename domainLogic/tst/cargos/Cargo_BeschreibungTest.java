package cargos;

import cargo.Hazard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

class Cargo_BeschreibungTest {
    Cargo_Beschreibung cargo;
    EnumSet<Hazard> hazards = EnumSet.noneOf(Hazard.class);
    @BeforeEach
            void setup(){
            cargo = new Cargo_Beschreibung(new BigDecimal(22),hazards);
            hazards.add(Hazard.explosive);
    }



    @Test
    void getValue() {
        assertEquals(cargo.getValue(), new BigDecimal(22));
    }

    @Test
    void getHazards() {
        assertEquals(cargo.getHazards().size(),1);


    }
}
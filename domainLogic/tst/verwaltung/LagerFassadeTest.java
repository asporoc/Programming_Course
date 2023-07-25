package verwaltung;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LagerFassadeTest {
    LagerFassade lagerFassade;
    Lager lager;
    @BeforeEach
    void setup(){
        lagerFassade = new LagerFassade(8);
        lager = new Lager(13);
    }

    @Test
    void getLagerTest() {
       assertEquals(lagerFassade.getLager().getMaxsize(),8);
    }

    @Test
    void setLager() {
        lagerFassade.setLager(lager);
        assertSame(lagerFassade.getLager(),lager);
    }
    @Test
    void defaultConstructor(){
        LagerFassade lagerFassade1 = new LagerFassade();
        assertEquals(lagerFassade1.getLager().getMaxsize(), 10);
    }
    @Test
    void constructor(){
        LagerFassade lagerFassade1 = new LagerFassade(55);
        assertEquals(lagerFassade1.getLager().getMaxsize(),55);
    }
}
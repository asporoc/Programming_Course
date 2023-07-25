package verwaltung;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

    public class KundeTest {

        @Test
        public void testGetName() {

            String name = "John Doe";
            Kunde kunde = new Kunde(name);

            String result = kunde.getName();

            Assertions.assertEquals(name, result);
        }
    }



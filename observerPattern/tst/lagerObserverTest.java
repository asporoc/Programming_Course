import cargo.Hazard;
import cargos.DryBulkCargoImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import verwaltung.Kunde;
import verwaltung.Lager;

import java.math.BigDecimal;
import java.util.EnumSet;

import static org.mockito.Mockito.*;
class lagerObserverTest {
    private final Lager lager = new Lager();
    @BeforeEach
    void setup(){
        lager.einfuegen(new Kunde("Heinz"));

    }
    @org.junit.jupiter.api.Test
    @Test
    void updateTestHazard() {
        EnumSet<Hazard> hazards = EnumSet.of(Hazard.toxic);
        DryBulkCargoImpl dryBulkCargo = new DryBulkCargoImpl(new Kunde("Heinz"),new BigDecimal(22),hazards,33);
        LagerObserver lagerObserverSpy = spy(new LagerObserver(lager));
        lager.addObserver(lagerObserverSpy);

        lager.einfuegen(dryBulkCargo);


        verify(lagerObserverSpy, times(1)).update(any(), any());
    }
    @org.junit.jupiter.api.Test
    @Test
    void update90PercentTest() {
        EnumSet<Hazard> hazards = EnumSet.noneOf(Hazard.class);
        LagerObserver lagerObserverSpy = spy(new LagerObserver(lager));
        lager.addObserver(lagerObserverSpy);

        lager.einfuegen(new DryBulkCargoImpl(new Kunde("Heinz"),new BigDecimal(22),hazards,33));
        lager.einfuegen(new DryBulkCargoImpl(new Kunde("Heinz"),new BigDecimal(22),hazards,33));
        lager.einfuegen(new DryBulkCargoImpl(new Kunde("Heinz"),new BigDecimal(22),hazards,33));
        lager.einfuegen(new DryBulkCargoImpl(new Kunde("Heinz"),new BigDecimal(22),hazards,33));
        lager.einfuegen(new DryBulkCargoImpl(new Kunde("Heinz"),new BigDecimal(22),hazards,33));
        lager.einfuegen(new DryBulkCargoImpl(new Kunde("Heinz"),new BigDecimal(22),hazards,33));
        lager.einfuegen(new DryBulkCargoImpl(new Kunde("Heinz"),new BigDecimal(22),hazards,33));
        lager.einfuegen(new DryBulkCargoImpl(new Kunde("Heinz"),new BigDecimal(22),hazards,33));
        lager.einfuegen(new DryBulkCargoImpl(new Kunde("Heinz"),new BigDecimal(22),hazards,33));

        verify(lagerObserverSpy, times(9)).update(any(), any());
    }
    @org.junit.jupiter.api.Test
    @Test
    void update100PercentTest() {
        Lager newLager = new Lager(1);
        newLager.einfuegen(new Kunde("Heinz"));
        EnumSet<Hazard> hazards = EnumSet.noneOf(Hazard.class);
        LagerObserver lagerObserverSpy = spy(new LagerObserver(newLager));
        newLager.addObserver(lagerObserverSpy);

        newLager.einfuegen(new DryBulkCargoImpl(new Kunde("Heinz"),new BigDecimal(22),hazards,33));


        verify(lagerObserverSpy, times(1)).update(any(), any());
    }
}

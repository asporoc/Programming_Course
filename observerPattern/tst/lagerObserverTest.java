import cargo.Hazard;
import cargos.DryBulkCargoImpl;
import cargos.storableCargo;
import org.junit.Test;
import org.mockito.Mockito;
import verwaltung.Lager;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class lagerObserverTest {
    @org.junit.jupiter.api.Test
    @Test
    void updateTest() {
        EnumSet<Hazard> hazards = EnumSet.of(Hazard.toxic);
        Lager lagerSpy = spy(Lager.class);
        DryBulkCargoImpl dryBulkCargoMock = Mockito.mock(DryBulkCargoImpl.class);

        lagerObserver observer = new lagerObserver(lagerSpy);

        doReturn(dryBulkCargoMock).when(lagerSpy).abrufen(0);
        when(dryBulkCargoMock.getHazards()).thenReturn(hazards);

        lagerSpy.einfuegen(dryBulkCargoMock);
        verify(observer, times(1)).update(any(), any());



    }
}

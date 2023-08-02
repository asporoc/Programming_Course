package viewControl;

import eventSystem.infrastructure.AbrufenEvent;
import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.KundeEinfuegenEvent;
import eventSystem.listener.CRUDEventListener;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import server.AbrufenListener;
import server.KundeEinfuegenListener;
import verwaltung.Kunde;
import verwaltung.LagerFassade;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.Mockito.*;

class ConsoleEventSystemTest {

    private ConsoleEventSystem consoleEventSystem;
    private EventHandler<CRUDEventListener<KundeEinfuegenEvent>> kundeEinfuegenHandler = new EventHandler<>();
    EventHandler<CRUDEventListener<KundeEinfuegenEvent>> spyEinfuegeHandler = Mockito.spy(kundeEinfuegenHandler);

    private EventHandler<CRUDEventListener<AbrufenEvent>> abrufenHandler = new EventHandler<>();
    EventHandler<CRUDEventListener<AbrufenEvent>> spyAbrufenHandler = Mockito.spy(abrufenHandler);


    @BeforeEach
    public void setUp() {
        LagerFassade lagerFassade = new LagerFassade();
        lagerFassade.getLager().einfuegen(new Kunde("Hugo"));
        consoleEventSystem = new ConsoleEventSystem();

        kundeEinfuegenHandler = new EventHandler<>();
        KundeEinfuegenListener kundeEinfuegenListener = new KundeEinfuegenListener(lagerFassade);
        kundeEinfuegenHandler.addListener(kundeEinfuegenListener);

        spyEinfuegeHandler = Mockito.spy(kundeEinfuegenHandler);
        consoleEventSystem.setKundeEinfuegenHandler(spyEinfuegeHandler);

        abrufenHandler = new EventHandler<>();
        AbrufenListener abrufenListener = new AbrufenListener(lagerFassade, consoleEventSystem);
        abrufenHandler.addListener(abrufenListener);
        spyAbrufenHandler = Mockito.spy(abrufenHandler);
        consoleEventSystem.setAbrufenEventHandler(spyAbrufenHandler);
    }
    @org.junit.jupiter.api.Test
    void kundeEinfuegenTestCLI() {
        String userInput = ":c\nHeinz";

        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        System.setIn(inputStream);

        consoleEventSystem.execute();

        verify(spyEinfuegeHandler, times(1)).handleEvent(any(KundeEinfuegenEvent.class));
    }
    @org.junit.jupiter.api.Test
    void kundeAbrufenTestCLI() {
        String userInput = ":r\ncustomers";

        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        System.setIn(inputStream);

        consoleEventSystem.execute();

        verify(spyAbrufenHandler, times(1)).handleEvent(any(AbrufenEvent.class));
    }
}
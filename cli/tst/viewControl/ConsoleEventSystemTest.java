package viewControl;

import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.KundeEinfuegenEvent;
import eventSystem.listener.CRUDEventListener;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsoleEventSystemTest {

    private ConsoleEventSystem consoleEventSystem;
    private EventHandler<CRUDEventListener<KundeEinfuegenEvent>> kundeEinfuegenHandler;

    @BeforeEach
    public void setUp() {
        consoleEventSystem = new ConsoleEventSystem();
        kundeEinfuegenHandler = mock(EventHandler.class);
        consoleEventSystem.setKundeEinfuegenHandler(kundeEinfuegenHandler);
    }

    @org.junit.jupiter.api.Test
    void kundeEinfuegenTestCLI() {
        String userInput = ":c\nJohn Doe"; // Input for the user, separated by newline

        // Create an InputStream with the desired user input
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect System.in to the ByteArrayInputStream
        System.setIn(inputStream);

        // Invoke the execute method, which will read from the redirected input stream
        consoleEventSystem.execute();

        // Verify that the handleEvent method of the handler is called with the correct event object
        verify(kundeEinfuegenHandler, times(1)).handleEvent(any(KundeEinfuegenEvent.class));
    }
}
package eventSystem.infrastructure;

import eventSystem.listener.CRUDEventListener;
import org.mockito.Mockito;

import java.util.EventObject;
import static org.mockito.Mockito.*;

class EventHandlerTest {

    @org.junit.jupiter.api.Test
    void addListener() {
        EventHandler<CRUDEventListener> eventHandler = new EventHandler<>();
        EventHandler eventHandlerSpy = spy(eventHandler);

        CRUDEventListener mockListener1 = Mockito.mock(CRUDEventListener.class);
        eventHandlerSpy.addListener(mockListener1);

        verify(eventHandlerSpy,times(1)).addListener(mockListener1);
    }

    @org.junit.jupiter.api.Test
    void removeListener() {
        EventHandler<CRUDEventListener> eventHandler = new EventHandler<>();
        EventHandler eventHandlerSpy = spy(eventHandler);

        CRUDEventListener mockListener1 = Mockito.mock(CRUDEventListener.class);
        eventHandlerSpy.addListener(mockListener1);
        eventHandlerSpy.removeListener(mockListener1);

        verify(eventHandlerSpy,times(1)).removeListener(mockListener1);

    }

    @org.junit.jupiter.api.Test
    void handleEvent() {
        EventHandler<CRUDEventListener> eventHandler = new EventHandler<>();
        CRUDEventListener mockListener1 = Mockito.mock(CRUDEventListener.class);
        eventHandler.addListener(mockListener1);
        EventObject event = new EventObject(this);
        eventHandler.handleEvent(event);

        verify(mockListener1, times(1)).onEvent(event);
    }
}
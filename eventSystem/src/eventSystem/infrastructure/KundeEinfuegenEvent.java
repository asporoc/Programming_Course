package eventSystem.infrastructure;

import verwaltung.Kunde;

import java.util.EventObject;

public class KundeEinfuegenEvent extends EventObject {
    private Kunde object;
    public KundeEinfuegenEvent(Object source, Kunde object) {
        super(source);
        this.object = object;
    }
    public Kunde getObject(){
        return object;
    }
}


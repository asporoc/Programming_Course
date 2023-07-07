package eventSystem.infrastructure;

import verwaltung.Lager;

import java.util.EventObject;

public class PersistenceEvent extends EventObject {
    String jos;
    Lager lager;

    public String getJos() {
        return jos;
    }

    public Lager getLager() {
        return lager;
    }

    public PersistenceEvent(Object source, String jos, Lager lager) {
        super(source);
        this.jos = jos;
        this.lager = lager;
    }
}

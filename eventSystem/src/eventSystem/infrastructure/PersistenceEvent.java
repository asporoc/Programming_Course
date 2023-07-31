package eventSystem.infrastructure;



import java.util.EventObject;

public class PersistenceEvent extends EventObject {
    final String jos;

    public String getJos() {
        return jos;
    }

    public PersistenceEvent(Object source, String jos) {
        super(source);
        this.jos = jos;
    }
}

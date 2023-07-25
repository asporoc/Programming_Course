package eventSystem.infrastructure;

import java.util.EventObject;

public class KundeEntfernenEvent extends EventObject{
    private String name;

    public KundeEntfernenEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

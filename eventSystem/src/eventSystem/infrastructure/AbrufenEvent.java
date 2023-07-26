package eventSystem.infrastructure;

import java.util.EventObject;

public class AbrufenEvent extends EventObject {
    private String type;

    public String getType() {
        return type;
    }

    public AbrufenEvent(Object source, String type) {
        super(source);
        this.type = type;
    }
}

package eventSystem.infrastructure;

import java.util.EventObject;

public class EntfernenEvent extends EventObject {

    private int storageLocation;
    public EntfernenEvent(Object source, int storageLocation) {
        super(source);
        this.storageLocation = storageLocation;
    }
    public int getStorageLocation(){
        return storageLocation;
    }
}

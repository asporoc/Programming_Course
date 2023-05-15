package eventSystem.infrastructure;

import java.util.EventObject;

public class StorageLocationEvent extends EventObject {

    private int storageLocation;
    public StorageLocationEvent(Object source, int storageLocation) {
        super(source);
        this.storageLocation = storageLocation;
    }
    public int getStorageLocation(){
        return storageLocation;
    }
}

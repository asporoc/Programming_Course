package eventSystem.infrastructure;

import java.util.EventObject;

public class CargoEntfernenEvent extends EventObject {

    private int storageLocation;
    public CargoEntfernenEvent(Object source, int storageLocation) {
        super(source);
        this.storageLocation = storageLocation;
    }
    public int getStorageLocation(){
        return storageLocation;
    }
}

package baseGUI;

import cargos.dryBulkCargoImpl;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;

import java.time.Duration;


public class CargoItem {



    private final StringProperty owner;
    private final ObjectProperty<Duration> durationOfStorage;
    private final StringProperty lastInspectionDate;
    private dryBulkCargoImpl cargo;
    private IntegerProperty storageLocation;
    private dryBulkCargoImpl dryBulkCargo;

    public CargoItem(dryBulkCargoImpl cargo) {
        this.cargo = cargo;
        this.storageLocation = new SimpleIntegerProperty(cargo.getStorageLocation());
        this.owner = new SimpleStringProperty(cargo.getOwner().getName());
        this.durationOfStorage = new SimpleObjectProperty<>(cargo.getDurationOfStorage());
        this.lastInspectionDate = new SimpleStringProperty(cargo.getLastInspectionDate().toString());
        Duration duration = cargo.getDurationOfStorage();
        updateDurationOfStorage(duration);


        Bindings.bindBidirectional(storageLocation, cargo.storageLocationProperty());
    }


    public IntegerProperty storageLocationProperty() {
        return storageLocation;
    }

    public StringProperty ownerProperty() {
        return owner;
    }

    public ObjectProperty<Duration> durationOfStorageProperty() {
        return durationOfStorage;
    }

    public void setDryBulkCargoImpl(dryBulkCargoImpl dryBulkCargo) {
        this.dryBulkCargo = dryBulkCargo;
    }

    public void updateDurationOfStorage(Duration duration) {
        this.durationOfStorage.set(Duration.parse(duration.toString()));
    }

    public dryBulkCargoImpl getDryBulkCargoImpl() {
        return cargo;
    }
    public String getLastInspectionDate() {
        return lastInspectionDate.get();
    }
    public StringProperty lastInspectionDateProperty() {
        return lastInspectionDate;
    }

}

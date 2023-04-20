package administration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public interface Storable {
    Customer getOwner();

    /**
     * liefert die vergangene Zeit seit dem Einfügen
     *
     * @return vergangene Zeit oder null wenn kein Einfügedatum gesetzt
     */
    Duration getDurationOfStorage();

    void setStorageLocation(int location);

    void setLastInspectionDate(Date lastInspection);

    Date getLastInspectionDate();

    int getStorageLocation();
}

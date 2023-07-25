package cargos;

import javafx.beans.property.IntegerProperty;

import java.util.Date;

public class UtilityClass {
    public static void setStorageLocation(storableCargo obj, int location) {
        if (obj instanceof DryBulkCargoImpl) {
            DryBulkCargoImpl dryBulkCargo = (DryBulkCargoImpl) obj;
            dryBulkCargo.setStorageLocation(location);
        }else if (obj instanceof LiquidBulkCargoImpl) {
            LiquidBulkCargoImpl liquidBulkCargo = (LiquidBulkCargoImpl) obj;
            liquidBulkCargo.setStorageLocation(location);
        }else if (obj instanceof UnitisedCargoImpl) {
            UnitisedCargoImpl unitisedCargo = (UnitisedCargoImpl) obj;
            unitisedCargo.setStorageLocation(location);
        }else if (obj instanceof LiquidAndDryBulkCargoImpl) {
            LiquidAndDryBulkCargoImpl liquidAndDryBulkCargo = (LiquidAndDryBulkCargoImpl) obj;
            liquidAndDryBulkCargo.setStorageLocation(location);
        }else if (obj instanceof DryBulkAndUnitisedCargoImpl) {
            DryBulkAndUnitisedCargoImpl dryBulkAndUnitisedCargo = (DryBulkAndUnitisedCargoImpl) obj;
            dryBulkAndUnitisedCargo.setStorageLocation(location);
        }else if(obj instanceof LiquidBulkAndUnitisedCargoImpl){
            LiquidBulkAndUnitisedCargoImpl liquidBulkAndUnitisedCargo = (LiquidBulkAndUnitisedCargoImpl) obj;
            liquidBulkAndUnitisedCargo.setStorageLocation(location);
        }
    }
    public static void setLastInspectionDate(storableCargo obj, Date date) {
        if (obj instanceof DryBulkCargoImpl) {
            ((DryBulkCargoImpl) obj).setLastInspectionDate(date);
        } else if (obj instanceof LiquidBulkCargoImpl) {
            ((LiquidBulkCargoImpl) obj).setLastInspectionDate(date);
        } else if (obj instanceof UnitisedCargoImpl) {
            ((UnitisedCargoImpl) obj).setLastInspectionDate(date);
        } else if (obj instanceof LiquidAndDryBulkCargoImpl) {
            ((LiquidAndDryBulkCargoImpl) obj).setLastInspectionDate(date);
        } else if (obj instanceof DryBulkAndUnitisedCargoImpl) {
            ((DryBulkAndUnitisedCargoImpl) obj).setLastInspectionDate(date);
        }else if(obj instanceof LiquidBulkAndUnitisedCargoImpl) {
            ((LiquidBulkAndUnitisedCargoImpl) obj).setLastInspectionDate(date);
        }
    }
    public static IntegerProperty storageLocationProperty(storableCargo obj) {
        if (obj instanceof DryBulkCargoImpl) {
            return((DryBulkCargoImpl) obj).storageLocationProperty();
        } else if (obj instanceof LiquidBulkCargoImpl) {
            return((LiquidBulkCargoImpl) obj).storageLocationProperty();
        } else if (obj instanceof UnitisedCargoImpl) {
            return((UnitisedCargoImpl) obj).storageLocationProperty();
        } else if (obj instanceof LiquidAndDryBulkCargoImpl) {
            return((LiquidAndDryBulkCargoImpl) obj).storageLocationProperty();
        } else if (obj instanceof DryBulkAndUnitisedCargoImpl) {
            return((DryBulkAndUnitisedCargoImpl) obj).storageLocationProperty();
        }else if (obj instanceof LiquidBulkAndUnitisedCargoImpl) {
            return((LiquidBulkAndUnitisedCargoImpl) obj).storageLocationProperty();
        }
        return null;
    }
}


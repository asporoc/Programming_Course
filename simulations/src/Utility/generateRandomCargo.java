package Utility;

import cargo.Hazard;
import cargos.*;
import verwaltung.Kunde;
import verwaltung.Lager;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Random;

public class generateRandomCargo {
    public static final String[] kunden = {"Heinz", "Jonathan", "Lennart", "Hugo", "Bernhard", "Maurice", "Hermann"};

    public static storableCargo generateRandCargo() {

        String[] cargoTypes = {"DryBulkCargo", "DryBulkAndUnitisedCargo", "LiquidBulkCargo", "LiquidBulkAndUnitisedCargo", "LiquidAndDryBulkCargo", "UnitisedCargo"};
        String[] kunden = getKunden();
        Hazard[] allHazards = {Hazard.flammable, Hazard.toxic, Hazard.radioactive, Hazard.explosive};
        boolean[] hazBool = new boolean[4];
        int numberOfHazards = 0;
        Random random = new Random();
        String cargoType = cargoTypes[random.nextInt(cargoTypes.length)];
        String kunde = kunden[random.nextInt(kunden.length)];
        int value = random.nextInt(90000);
        EnumSet<Hazard> hazards = EnumSet.noneOf(Hazard.class);
        int grainSize = random.nextInt(20);
        for (int i = 0; i < hazBool.length; i++) {
            hazBool[i] = random.nextBoolean();
            if (hazBool[i]) {
                numberOfHazards++;
                hazards.add(allHazards[i]);
            }
        }
        switch (cargoType){
            case("DryBulkCargo"):
                return new DryBulkCargoImpl(new Kunde(kunde), new BigDecimal(value), hazards,  grainSize);
            case("LiquidBulkAndUnitisedCargo"):
                return new DryBulkAndUnitisedCargoImpl(new Kunde(kunde), new BigDecimal(value), hazards,  grainSize,random.nextBoolean());
            case("LiquidBulkCargo"):
                return new LiquidBulkCargoImpl(new Kunde(kunde), new BigDecimal(value), hazards,random.nextBoolean());
            case("DryBulkAndUnitisedCargo"):
                return new DryBulkAndUnitisedCargoImpl(new Kunde(kunde), new BigDecimal(value), hazards,  grainSize,random.nextBoolean());
            case("LiquidAndDryBulkCargo"):
                return new LiquidAndDryBulkCargoImpl(new Kunde(kunde), new BigDecimal(value), hazards, random.nextBoolean() ,grainSize);
            case("UnitisedCargo"):
                return new UnitisedCargoImpl(new Kunde(kunde), new BigDecimal(value), hazards,random.nextBoolean());
        }
        return null;
    }
    public static int randStorageLocation(Lager lager){
        Random random = new Random();
        return random.nextInt((lager.getMaxsize()-1+1));
    }
    public static String[] getKunden(){
        return kunden;
    }
}

package eventSystem.infrastructure;

import cargos.storableCargo;

import java.util.ArrayList;
import java.util.EventObject;

public class CargosAbrufenEvent extends EventObject {
    private ArrayList<storableCargo> cargos;

    public ArrayList<storableCargo> getCargos() {
        return cargos;
    }

    public CargosAbrufenEvent(Object source, ArrayList<storableCargo> cargos) {
        super(source);
        this.cargos = cargos;
    }
}

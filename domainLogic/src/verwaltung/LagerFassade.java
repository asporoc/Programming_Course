package verwaltung;

import java.io.Serializable;

public class LagerFassade implements Serializable {
    Lager lager;
    public LagerFassade(int size){
        lager = new Lager(size);

    }
    public LagerFassade(){

        lager = new Lager();
    }

    public Lager getLager() {
        return lager;
    }

    public void setLager(Lager lager) {
        this.lager = lager;
    }
}

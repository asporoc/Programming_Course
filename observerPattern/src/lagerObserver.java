import cargo.Hazard;
import verwaltung.Lager;
import cargos.*;
import java.util.Observable;
import java.util.Observer;

public class lagerObserver implements Observer{
    private Lager lager;
    private int maxsize;
    private int currentSize;
    public lagerObserver(Lager lager){
        this.lager = lager;
        this.lager.addObserver(this);
        this.maxsize = lager.getMaxsize();
        this.currentSize = lager.getCargoList().size();

    }
    @Override
    public void update(Observable o, Object arg) {
        currentSize = lager.getCargoList().size();
        if(lager.getCargoList().get(currentSize-1).getHazards().size() != 0){
            System.out.print("Ein Cargo mit den folgenden gefährlichen Eigenschaften wurde hinzugefügt: ");
            for(Hazard hazard:lager.getCargoList().get(currentSize-1).getHazards()){
                System.out.print(" "+hazard+"\n");

            }

        }
        if((currentSize/(double)maxsize)==0.9){
            System.out.println("Lager ist zu 90% oder mehr belegt!");
        } else if(currentSize == maxsize){
            System.out.println("Lager ist jetzt Voll!");
        }

    }
}

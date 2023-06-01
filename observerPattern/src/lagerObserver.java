import java.util.Observable;
import java.util.Observer;
/*lager Observer gibt updates zu verändeurngen der Lager Kapazitäten (einfuege und entfern operationen)*/
public class lagerObserver implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);

    }
}

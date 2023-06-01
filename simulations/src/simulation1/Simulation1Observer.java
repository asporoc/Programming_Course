package simulation1;

import java.util.Observable;
import java.util.Observer;

public class Simulation1Observer implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);

    }
}

package simulation1;

public class Simulation1Main {
    public static class simulation1Create implements Runnable{
        @Override
        public void run() {
            while (true){
            }
        }
    }
    public static class simulation1Delete implements Runnable{
        @Override
        public void run() {
            while (true){
            }
        }
    }
    public static void main(String[] args){

        simulation1Create createSimulation = new simulation1Create();
        simulation1Delete deleteSimulation = new simulation1Delete();
        Thread create = new Thread(createSimulation);
        Thread delete = new Thread(deleteSimulation);

    }
}

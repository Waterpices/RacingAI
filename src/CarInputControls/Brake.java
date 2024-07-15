package CarInputControls;

public class Brake implements InputMethod {
    private final Car car;

    public Brake(Car c){
        this.car = c;
    }
    public void run() {
        this.car.brake();
    }
}

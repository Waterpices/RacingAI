package CarInputControls;

public class Accelerate implements InputMethod {
    private final Car car;

    public Accelerate(Car c){
        this.car = c;
    }
    public void run() {
        this.car.accelerate();
    }
}
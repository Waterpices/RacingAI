package CarInputControls;

public class TurnLeft implements InputMethod {
    private final Car car;

    public TurnLeft(Car c){
        this.car = c;
    }
    public void run() {
        this.car.turnLeft();
    }
}
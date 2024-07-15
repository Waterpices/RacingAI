package CarInputControls;

public class TurnRight implements InputMethod {
    private final Car car;

    public TurnRight(Car c){
        this.car = c;
    }
    public void run() {
        this.car.turnRight();
    }
}
//TODO do the same for left turn, forward, realease, backward
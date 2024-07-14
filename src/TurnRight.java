public class TurnRight implements InputMethod{
    private Car car;

    public TurnRight(Car c){
        this.car = c;
    }
    public void run() {
        //TODO switch to car.turnRight
        System.out.println(car.toString());
    }
}
//TODO do the same for left turn, forward, realease, backward
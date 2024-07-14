import java.lang.Math;
public class Car {
    public static double rotation = 0.2;
    public static double acceleration = 0.5;
    public static double brakes = Car.acceleration*2;
    private int height;
    private int width;
    private int x;
    private int y;
    private double speed;
    private double orientation;

    public Car(int height, int width){
        this.height = height;
        this.width = width;
        this.x = 0;
        this.y = 0;
        this.speed = 0;
        this.orientation = 0;
    }

    //TODO block rotation when still
    public void turnLeft(){
        this.orientation += Car.rotation%(Math.PI*2) ;
    }
    //TODO block rotation when still
    public void turnRight(){
        double orientation = this.orientation - Car.rotation;
        if(orientation < 0){ orientation += Math.PI*2;}
        this.orientation = orientation;
    }

    public void accelerate(){
        this.speed += Car.acceleration;
    }

    public void brake(){
        this.speed -= Car.brakes;
    }

    public void move(){
        this.x = (int) (this.x + Math.round(this.speed * Math.cos(this.orientation)));
        this.y = (int) (this.y + Math.round(this.speed * Math.sin(this.orientation)));
    }

    public void forceMove(int x,int y,double orientation){
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public void updateDisplay(){
        //drawSquareCenter(this.x,this.y,this.height,this.width,this.orientation);
    }

    @Override
    public String toString() {
        return "Car{" +
                "height=" + height +
                ", width=" + width +
                ", x=" + x +
                ", y=" + y +
                ", speed=" + speed +
                ", orientation=" + orientation +
                '}';
    }
}

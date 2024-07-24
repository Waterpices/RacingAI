package CarInputControls;

import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;

public class Car {
    private static final float friction = 0.012F; //3.8 pxl/frame max speed
    private static final float maxSpeed = 40;
    public static float maxSteering = 0.2F;
    public static float acceleration = 0.25F;
    public static float brakes = Car.acceleration*2;
    private final int height;
    private final int width;
    private Color color;
    private int x;
    private int y;
    private float speed;
    private float orientation;
    private float steering;

    public Car(int height, int width, Color c){
        this.height = height;
        this.width = width;
        this.color = c;
        this.x = 0;
        this.y = 0;
        this.speed = 0;
        this.orientation = 0;
        this.steering = 0;
    }

    private void airFriction(){
        this.speed = speed * (1 - friction);
    }

    //TODO block rotation when still
    public void turnLeft(){
        this.steering = Car.maxSteering; // * dt
        this.steering = Math.min(this.steering,Car.maxSteering);
        //this.orientation += (float) (Car.maxSteering %(Math.PI*2));
    }
    //TODO block rotation when still
    public void turnRight(){
        this.steering = -Car.maxSteering; // * dt
        this.steering = Math.max(this.steering,-Car.maxSteering);
        //float orientation = this.orientation - Car.maxSteering;
        //if(orientation < 0){ orientation += (float) (Math.PI*2);}
        //this.orientation = orientation;
    }

    public void accelerate(){
        this.speed += Car.acceleration;// * dt
        this.speed = Math.min(this.speed,Car.maxSpeed);
    }

    public void brake(){
        this.speed -= Car.brakes;// * dt
        this.speed = Math.max(this.speed,-Car.maxSpeed);
    }

    public void move(){
        airFriction();
        //this.x = (int) (this.x + Math.round(this.speed * Math.cos(this.orientation)));
        //this.y = (int) (this.y + Math.round(this.speed * Math.sin(this.orientation)));

        float angular_velocity = 0;
        if (this.steering != 0){
            float turning_radius = (float) (this.height / Math.sin(this.steering));
            angular_velocity = this.speed / turning_radius;
        }

        this.x = (int) (this.x + Math.round(this.speed * Math.cos(this.orientation)));// * dt
        this.y = (int) (this.y + Math.round(this.speed * Math.sin(this.orientation)));// * dt
        this.orientation += angular_velocity;// * dt
        this.steering = 0;
    }

    public void forceMove(int x,int y,float orientation){
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "InputControls.Car{" +
                "height=" + height +
                ", width=" + width +
                ", x=" + x +
                ", y=" + y +
                ", speed=" + speed +
                ", orientation=" + orientation +
                '}';
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public float getOrientation() {
        return orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Color getColor() {
        return color;
    }

    /* Max speed
    * a+V_max(1-frt) = V_max
    * a = V_max(1-(1-frt)) = frt*V_max
    * a/frt = V_max
    */
    public double maxSpeed(){
        return acceleration / friction;
    }

    public ArrayList<Float> getDebugSteeringArrow(){
        ArrayList<Float> arrow = new ArrayList<Float>(4);
        arrow.add((float)this.x);
        arrow.add((float)this.y);
        arrow.add(this.speed*10);
        arrow.add(this.orientation+this.steering);
        return arrow;
    }

    public ArrayList<Float> getDebugSpeedArrow(){
        ArrayList<Float> arrow = new ArrayList<Float>(4);
        arrow.add((float)this.x);
        arrow.add((float)this.y);
        arrow.add(this.speed*10);
        arrow.add(this.orientation);
        return arrow;
    }

}

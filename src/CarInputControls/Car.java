package CarInputControls;

import java.awt.*;
import java.lang.Math;
public class Car {
    private static final float friction = 0.012F; //3.8 pxl/frame max speed
    public static float rotation = 0.1F;
    public static float acceleration = 0.25F;
    public static float brakes = Car.acceleration*2;
    private final int height;
    private final int width;
    private int x;
    private int y;
    private float speed;
    private float orientation;

    private Color color;

    public Car(int height, int width, Color c){
        this.height = height;
        this.width = width;
        this.color = c;
        this.x = 0;
        this.y = 0;
        this.speed = 0;
        this.orientation = 0;
    }

    private void airFriction(){
        this.speed = speed * (1 - friction);
    }

    //TODO block rotation when still
    public void turnLeft(){
        this.orientation += (float) (Car.rotation%(Math.PI*2));
    }
    //TODO block rotation when still
    public void turnRight(){
        float orientation = this.orientation - Car.rotation;
        if(orientation < 0){ orientation += (float) (Math.PI*2);}
        this.orientation = orientation;
    }

    public void accelerate(){
        this.speed += Car.acceleration;
    }

    public void brake(){
        this.speed -= Car.brakes;
    }

    public void move(){
        airFriction();
        this.x = (int) (this.x + Math.round(this.speed * Math.cos(this.orientation)));
        this.y = (int) (this.y + Math.round(this.speed * Math.sin(this.orientation)));
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
}

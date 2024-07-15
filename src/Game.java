import CarInputControls.*;
import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game extends PApplet {
    public int i=0;
    public int size = 800;
    public ArrayList<Car> cars = new ArrayList<Car>();
    public Controls controls = new Controls();
    public void settings() {
        size(size, size);
    }

    public void drawBackground(){
        background(55,55,55);
    }

    public void setup() {
        Car car1 = new Car(20,40, new Color(0, 40, 225));
        addCar(car1);
        controls.addControl("q", new TurnRight(car1));
        controls.addControl("d", new TurnLeft(car1));
        controls.addControl("z", new Accelerate(car1));
        controls.addControl("s", new Brake(car1));
        car1.forceMove(0,200,0);

        Car car2 = new Car(20,40, new Color(225, 0, 150));
        addCar(car2);
        controls.addControl("f", new TurnRight(car2));
        controls.addControl("h", new TurnLeft(car2));
        controls.addControl("t", new Accelerate(car2));
        controls.addControl("g", new Brake(car2));
        car2.forceMove(0,200,0);
        background(55,55,55);
        //noLoop();
    }
    public void draw() {
        drawBackground();
        fill(255,0,0);
        rect(50, 50, 100, 100);
        //updateControl
        //playControl -> play collision inside each object
        controls.playControls();
        //update object position
        for(Car c:cars){
            c.move();
        }
        //update display
        i++;
        if(i<40){
            controls.simulateKeyPress("z");
            controls.simulateKeyPress("t");
        }
        if(i>40){
            controls.simulateKeyPress("d");
        }
        for(Car c:cars){
            drawItem(c);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(Car c:cars){
            System.out.println(c);
        }
        System.out.println("End of draw");
    }

    private void drawItem(Car c){
        drawSquareCenter(c.getX(), c.getY(), c.getWidth(), c.getHeight(), c.getOrientation(),c.getColor());
    }

    public void addCar(Car c){
        this.cars.add(c);
    }

    //TODO work with a drawer and drawer.drawItem(Car);
    public void drawSquareCenter(int x, int y, int w, int h, float orientation, Color c) {
        translate(x,y);
        rotate(orientation);
        fill(c.getRed(),c.getGreen(),c.getBlue());
        rect(-(float)w/2, -(float)h/2, w, h);
        fill(200,200,0);
        rect(-5, -5, 10, 10); // Draw a yellow square
        rotate(-orientation);
        translate(-x,-y);
    }

    public static void main(String[] args) {
        PApplet.main("Game");
        System.out.println("End of main");
    }
}
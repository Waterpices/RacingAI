import processing.core.PApplet;
import java.util.concurrent.TimeUnit;

public class Game extends PApplet {
    public int i =0;
    public int size = 400;
    public void settings() {
        size(size, size);
    }

    public void drawBackground(){
        background(55,55,55);
    }

    public void setup() {
        background(55,55,55);
        //noLoop();
    }

    public void drawSquareCenter(int x, int y, int w, int h, float orientation) {
        float centerX = (float)x-(float)w/2;
        float centerY = (float)y-(float)h/2;
        translate(x,y);
        rotate(orientation);
        fill(0, 40, 225);
        rect(-(float)w/2, -(float)h/2, w, h); // Draw a red square
        fill(200,200,0);
        rect(-5, -5, 10, 10); // Draw a yellow square
        System.out.println("Orientation=" + orientation);
        translate(-x,-y);
        rotate(-orientation);
    }

    public void draw() {
        //drawBackground();
        //updateControl
        //playControl -> play collision inside each object
        //update display
        i++;
        drawSquareCenter(i,50,20,40,(float)i/20);
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End of draw");
    }

    public static void main(String[] args) {
        Controls controls = new Controls();
        Car car1 = new Car(20,40);
        controls.addControl("q", new TurnRight(car1));
        controls.playControls();
        car1.forceMove(10,10,10);
        controls.playControls();
        PApplet.main("Game");
        System.out.println("End of main");
    }
}
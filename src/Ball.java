import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
    private int yspeed = 3;
    private int xspeed = 1;
    Circle c;

    public Ball(){
        c = new Circle(225, 540, 10, Color.BLUE);
        c.setVisible(true);
    }

    public void move() {
        c.setCenterY(c.getCenterY() - yspeed);
        c.setCenterX(c.getCenterX() + xspeed);
    }

    public void hitY(){
        yspeed *= -1;
    }

    public void hitX(){
        xspeed *= -1;
    }

    public double getX(){
        return c.getCenterX();
    }

    public double getY(){
        return c.getCenterY();
    }

    public int getSpeed() { return xspeed; }
}

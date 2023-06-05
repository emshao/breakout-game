import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle {
    Rectangle r;
    public Paddle(){
        r = new Rectangle(180, 550, 90, 20);
        r.setFill(Color.BLACK);
        r.setArcWidth(20.0);
        r.setArcHeight(20.0);
        r.setVisible(true);
    }

    public void moveLeft(){
        if(r.getX() - 10 >= 10){
            r.setX(r.getX() - 10);
        }
        else {
            r.setX(10);
        }
    }

    public void moveRight(){
        if(r.getX() + 10 <= 350){
            r.setX(r.getX() + 10);
        }
        else {
            r.setX(350);
        }
    }

    public int hit(){
        return 0;
    }

    public double getX(){
        return r.getX();
    }

    public double getY(){
        return r.getY();
    }
}

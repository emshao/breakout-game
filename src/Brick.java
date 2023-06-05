import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick {
    Rectangle r;
    public Brick(int x, int y){
        r = new Rectangle(x, y, 49, 29);
        r.setFill(Color.GRAY);
        r.setVisible(true);
    }

    public void disappear(){
        r.setVisible(false);
    }
}

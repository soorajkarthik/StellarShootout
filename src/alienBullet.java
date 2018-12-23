import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class alienBullet {

    private int x;
    private int y;
    private int w;
    private int h;
    private Image image;
    private Rectangle hitBox;
    private int move = 3;

    public alienBullet (int x, int y) {
        this.x = x + 50;
        this.y = y + 3;
        loadImage();
    }
    
    public void loadImage() {
        
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/alienbullet.png"));
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);

        hitBox = new Rectangle(this.x, this.y, w, h);
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public Image getImage() {

        return image;
    }

    public void move() {

        y += move;
        hitBox.setLocation(x, y);
    }
    
    public Rectangle getHitBox() {
        
        return hitBox;
    }
}

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class userBullet {

    private int x;
    private int y;
    private int w;
    private int h;
    private Image image;
    private Rectangle hitBox;
    private int move = 4;

    public userBullet(int x, int y) {
        this.x = x + 35;
        this.y = y - 3;

        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/userBullet.png"));
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

        y -= move;
        hitBox.setLocation(x, y);
    }
    
    public Rectangle getHitBox() {
        
        return hitBox;
    }
}

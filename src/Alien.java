import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Alien {

    private int x;
    private int y;
    private int w;
    private int h;
    private Image image;
    private int health;
    private Rectangle hitBox;
    private ArrayList<alienBullet> alienBullets = new ArrayList<>();

    public Alien(int x) {
        
        this.x = x;
        this.y = 0;
        
        loadImage();
    }

    public void loadImage() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/alienShip.png"));
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
        
        hitBox = new Rectangle (x, y, w, h);
        
        health = 200;
    }
        public void move(int i) {

        if (i == 1 && x >= 2) {
            x -= 2;
            hitBox.setLocation(x, y);
        }

        if (i == 2 && x <= 548) {
            x += 2;
            hitBox.setLocation(x, y);
        }

        if (i == 3 && y <= 800) {
            y += 2;
            hitBox.setLocation(x, y);
        }

        if (i == 4) {
            y += 0;
            hitBox.setLocation(x, y);
        }
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public int getWidth() {

        return w;
    }

    public int getHeight() {

        return h;
    }

    public Image getImage() {

        return image;
    }
    
    public Rectangle getHitBox() {
    
        return hitBox;
    }
    
    public int getHealth() {
        
        return health;
    }
    
    public ArrayList<alienBullet> getAlienBullets() {
        
        return alienBullets;
    }
    
    public void fire() {
        
        alienBullets.add(new alienBullet(x, y));
    }
    
    public void takeDamage() {
        
        health -= 100;
    }
}
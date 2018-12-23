import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class SpaceShip {

    private int x = 262;
    private int y = 725;
    private int moveX;
    private int moveY;
    private int w;
    private int h;
    private Image image;
    private int ammo;
    private double health;
    private Rectangle hitBox;
    private ArrayList<userBullet> userBullets = new ArrayList<>();

    public SpaceShip() {

        loadImage();
    }

    public void loadImage() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/userShip.png"));
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
        
        ammo = 10;
        
        hitBox = new Rectangle (x, y, w, h);
        
        health = 1000;
    }

    public void move() {

        if (moveX < 0 && x >= 2) 
            x += moveX;

        if (moveX > 0 && x <= 523) 
            x += moveX;

        if (moveY > 0 && y <= 723) 
            y += moveY;

        if (moveY < 0 && y >= 500) 
            y += moveY;
        
        hitBox.setLocation(x, y);
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

    public void keyPressed(KeyEvent e, int reloading) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE && reloading <= 0) {
            fire();
        }
        
        if (key == KeyEvent.VK_LEFT) {
            moveX = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            moveX = 2;
        }

        if (key == KeyEvent.VK_DOWN) {
            moveY = 2;
        }

        if (key == KeyEvent.VK_UP) {
            moveY = -2;
        }
    }    

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            moveX = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            moveX = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            moveY = 0;
        }

        if (key == KeyEvent.VK_UP) {
            moveY = 0;
        }
    }
    
    public void fire() {
        if (ammo > 0) {
            userBullets.add(new userBullet(x, y));
            ammo--;
        }
    }

    public ArrayList<userBullet> getUserBullets() {

        return userBullets;
    }

    public void reload() {

        ammo = 10;
    }
    
    public double getHealth() {
        
        return health;
    }
    
    public void takeDamagei() {
        
        health -= 100;
    }
    
    public void takeDamageii() {
        
        health -= 50;
    }
    
    public void regen() {        
        if(health < 1000)
            health += 0.1;
    }
    
    public int getAmmo() {
        
        return ammo;
    }
}

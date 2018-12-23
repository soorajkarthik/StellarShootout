import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public final class HealthHud {
    
    private ArrayList<Image> images;
    private ArrayList<ImageIcon> ii;
    private final int X = 340;
    private final int Y = 801;
    
    public HealthHud() {
        
        images = new ArrayList<>();
        ii = new ArrayList<>();
        
        loadHud();
    }
    
    public void loadHud() {
        
        ii.add(new ImageIcon(this.getClass().getResource("images/health0.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/health1.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/health2.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/health3.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/health4.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/health5.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/health6.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/health7.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/health8.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/health9.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/health10.png")));
        
        for (ImageIcon icon : ii) {
            Image temp = icon.getImage();
            images.add(temp);
        }
        
    }
    
    public ArrayList<Image> getImages() {
        
        return images;
    }
    
    public int getX() {
        
        return X;
    }
    
    public int getY() {
        
        return Y;
    }
}
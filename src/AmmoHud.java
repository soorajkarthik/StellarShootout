import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public final class AmmoHud {
    
    private ArrayList<Image> images;
    private ArrayList<ImageIcon> ii;
    private final int X = -20;
    private final int Y = 801;
    
    public AmmoHud() {
        
        images = new ArrayList<>();
        ii = new ArrayList<>();
        
        loadHud();
    }
    
    public void loadHud() {
        
        ii.add(new ImageIcon(this.getClass().getResource("images/ammo0.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/ammo1.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/ammo2.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/ammo3.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/ammo4.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/ammo5.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/ammo6.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/ammo7.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/ammo8.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/ammo9.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/ammo10.png")));
        ii.add(new ImageIcon(this.getClass().getResource("images/ammor.png")));
        
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
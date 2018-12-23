import java.awt.Image;
import javax.swing.ImageIcon;

public class Paused {
    
    private Image image;
    
    public Paused() {
        
        loadPaused();
    }
    
    public void loadPaused() {
        
        ImageIcon ii = new ImageIcon (this.getClass().getResource("images/paused.png"));
        image = ii.getImage();
    }
    
    public Image getImage() {
        
        return image;
    }
}
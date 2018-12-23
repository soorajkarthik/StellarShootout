import java.awt.Image;
import javax.swing.ImageIcon;

public class StartScreen {
    
    private Image image;
    
    public StartScreen() {
        
        loadStartScreen();
    }
    
    public void loadStartScreen() {
        
        ImageIcon ii = new ImageIcon (this.getClass().getResource("images/start.png"));
        image = ii.getImage();
    }
    
    public Image getImage() {
        
        return image;
    }
}
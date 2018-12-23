import java.awt.Image;
import javax.swing.ImageIcon;

public class gameOver {
    
    private Image image;
    
    public gameOver() {
        
        loadGameOver();
    }
    
    public void loadGameOver() {
        
        ImageIcon ii = new ImageIcon (this.getClass().getResource("images/gameOver.png"));
        image = ii.getImage();
    }
    
    public Image getImage() {
        
        return image;
    }
}
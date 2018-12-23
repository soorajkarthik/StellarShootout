import java.awt.Image;
import javax.swing.ImageIcon;

public class Instructions {
    
    private Image image;
    
    public Instructions() {
        
        loadInstructions();
    }
    
    public void loadInstructions() {
        
        ImageIcon ii = new ImageIcon (this.getClass().getResource("images/instructions.png"));
        image = ii.getImage();
    }
    
    public Image getImage() {
        
        return image;
    }
}
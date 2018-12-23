import java.awt.Image;
import javax.swing.ImageIcon;

public class Background {

    private Image image;

    public Background() {

        loadBackground();
    }

    public void loadBackground() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/background.png"));
        image = ii.getImage();
    }

    public Image getImage() {

        return image;
    }
}

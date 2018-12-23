import java.awt.Color;
import javax.swing.JFrame;

public class Main extends JFrame {

    public Main() {

        openUI();
    }

    public void openUI() {

        add(new Board());

        setTitle("Stellar Shootout");
        setSize(607, 880);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
    }

    public static void main(String[] args) {

        Main m = new Main();
        m.setVisible(true);

    }
}
 
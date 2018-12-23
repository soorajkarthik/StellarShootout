import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Timer alienSpawner;
    private final int DELAY = 10;
    private int spawnDelay = 10000;
    private SpaceShip ss;
    private Background back;
    private Instructions instructions;
    private StartScreen start;
    private HealthHud healthHud;
    private AmmoHud ammoHud;
    private int numKeyPress = 0;
    private int numActions = 0;
    private ArrayList<Alien> aliens;
    private Random r = new Random();
    private int round = 0;
    private int score = 0;
    private int kills = 0;
    private double hudNum;
    private Paused paused;
    private gameOver gameOver;
    private int reloading = 0;
    private boolean lost;
    private JButton restart;
    private Font f;
    
    public Board() {

        openBoard();
    }
    
    public void openBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.GRAY);
        setDoubleBuffered(true);

        ss = new SpaceShip();
        back = new Background();
        instructions = new Instructions();
        start = new StartScreen();
        aliens = new ArrayList<>();
        healthHud = new HealthHud();
        ammoHud = new AmmoHud();
        paused = null;
        gameOver = null;
        
        timer = new Timer(DELAY, this);
        timer.start();
        
        alienSpawner = new Timer(spawnDelay, this);
        
        lost = false;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        
        g2d.clearRect(0, 0, 601, 900);
        
        if(lost == false) {
            
        g2d.drawImage(back.getImage(), 0, 0, this);
        g2d.drawImage(ss.getImage(), ss.getX(), ss.getY(), this);
        
        for (userBullet b : ss.getUserBullets()) {

            g2d.drawImage(b.getImage(), b.getX(), b.getY(), this);
        }
        
        for (Alien a : aliens) {
            
            g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
            
            if (a.getAlienBullets().size() > 0)
                for (alienBullet ab : a.getAlienBullets())
                    g2d.drawImage(ab.getImage(), ab.getX(), ab.getY(), this);
        }
        
        g2d.setColor(Color.WHITE);
        f = new Font("helvetica", Font.BOLD, 20);
        g2d.setFont(f);
        g2d.drawString("Score: " + score, 5, 25);
        g2d.drawString("Round: " + round, 5, 50);
        
        g2d.clearRect(0, 800, 600, 81);
        
        hudNum = ss.getHealth() /100;
        
        if(hudNum >= 0)
            g2d.drawImage(healthHud.getImages().get((int)hudNum), healthHud.getX(), healthHud.getY(), this);
        
        else
            g2d.drawImage(healthHud.getImages().get(0), healthHud.getX(), healthHud.getY(), this);
        
        if (reloading < 0)
            g2d.drawImage(ammoHud.getImages().get(ss.getAmmo()), ammoHud.getX(), ammoHud.getY(), this);
        
        else if(reloading > 0)
            g2d.drawImage(ammoHud.getImages().get(11), ammoHud.getX(), ammoHud.getY(), this);
        
        if(paused != null)
            g2d.drawImage(paused.getImage(), 136, 364, this);
              
        if(numKeyPress < 2) {
            g2d.drawImage(instructions.getImage(), 0, 0, this);
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 800, 600, 81);
        }
        
        if(numKeyPress < 1)
            g2d.drawImage(start.getImage(), 0, 0, this);
        }
        
        if (lost == true) {
            
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, 600, 900);
            
            g2d.drawImage(gameOver.getImage(), 50, 200, this);
            
            g2d.setColor(Color.WHITE);
            f = new Font ("helvetica", Font.BOLD, 50);
            g2d.setFont(f);
            
            String scr = String.format("Score: %06d", score);
            String rnd = String.format("Round: %02d", round);
            
            g2d.drawString(scr, 125, 400);
            g2d.drawString(rnd, 175, 525);
            
        }
    }

    public class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            
            int event = e.getKeyCode();
            
            numKeyPress++;
            
            if(event == KeyEvent.VK_P && timer.isRunning() && alienSpawner.isRunning()) 
                pause();
                
            else if(event == KeyEvent.VK_P && !timer.isRunning() && !alienSpawner.isRunning()) 
                resume();
            
            else if (numKeyPress > 2)
                ss.keyPressed(e, reloading);
            
            if(event == KeyEvent.VK_R) {
                ss.reload();
                reloading = 50;
            }
            
            if(numKeyPress == 2)
                alienSpawner.start();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            ss.keyReleased(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == alienSpawner)
            spawnAliens();
        
        else if (e.getSource() == restart) {
            
            restart.setVisible(false);
            lost = false;
            ss = new SpaceShip();
            numKeyPress = 0;
            numActions = 0;
            kills = 0;
            round = 0;
            spawnDelay = 15000;
            aliens.clear();
            timer.start();
        }
        
        else {
            checkForCollision();
            updateUserShip();
            updateUserBullets();
            updateAlienShip();
            updateAlienBullets();
            updateRound();
           
            numActions++;
        }
        
        if(numActions % 10 == 0 && numKeyPress > 2) 
            updateScore();
        
        repaint();
    }
    
    public void spawnAliens() {
        
        for(int x = 0; x < 600; x += 600/6) {
                        
            aliens.add(new Alien(x));
        }
    }
    
    public void checkForCollision() {
        
        for(Alien a : aliens) {
            
            for(int aB = 0; aB < a.getAlienBullets().size(); aB++) {
                
                if(a.getAlienBullets().get(aB).getHitBox().intersects(ss.getHitBox())) {
                    
                    ss.takeDamagei();
                    a.getAlienBullets().remove(aB);
                    aB--;
                }
                
                if(aB == -1)
                    break;
            }
        }
        
        for(int uB = 0; uB < ss.getUserBullets().size(); uB++) {

            for(Alien a : aliens) {
                
                if(ss.getUserBullets().get(uB).getHitBox().intersects(a.getHitBox())) {
                    
                    a.takeDamage();
                    ss.getUserBullets().remove(uB);
                    uB--;
                }
                
                if(uB == -1)
                    break;
            }
            
            if(uB == -1)
                break;
        }
    }
    
    public void updateUserShip() {
        
        ss.move();
        ss.regen();
        
        if(ss.getHealth() <= 0) {
            gameOverSequence();
        }
    }
    
    public void updateUserBullets() {

        ArrayList<userBullet> uB = ss.getUserBullets();

        for (int i = 0; i < uB.size(); i++) {

            if (uB.get(i).getY() < 0) {

                uB.remove(i);
                i--;
            } 
            
            else {
                uB.get(i).move();
            }
        }
        
        reloading--;
    }
    
    public void updateAlienShip() {
        
        int i = r.nextInt(4) + 1;
        
        if(numActions % 2 == 0) {
            
            for (Alien a : aliens) {
            
                a.move(i);
            }   
        }    
        
        for(int z = 0; z < aliens.size(); z++) {
            
            if(aliens.get(z).getHealth() == 0) {
                
                aliens.remove(z);
                kills++;
                z--;
            }
        }
        
        if (numActions % 50 == 0) {
            
            for(Alien a : aliens) {
                
                int fire = r.nextInt(7) + 1;
                
                if(fire == 7)
                    a.fire();
            }
        }
        
        for(int a = 0; a < aliens.size(); a++)
        {
            if(aliens.get(a).getY() > 790)
            {
                aliens.remove(a);
                ss.takeDamageii();
                a--;
            }
        }
    }
    
    public void updateAlienBullets() {
        
        for (Alien a : aliens) {
            
            ArrayList<alienBullet> aB = a.getAlienBullets();
            
                for (int i = 0; i < aB.size(); i++) {
                
                    if (aB.get(i).getY() > 800) {

                    aB.remove(i);
                    i--;
                } 
            
                else {
                    aB.get(i).move();
                }
            }
        } 
    }
    
    public void updateRound() {
        
        if(aliens.isEmpty() && alienSpawner.isRunning()) {
            
            round++;
            spawnDelay -= 500;
            alienSpawner.setDelay(spawnDelay);
            alienSpawner.restart();
            spawnAliens();
        }
    }
    
    public void updateScore() {
        
        score = (round * 1000) + (numActions / 10) + (kills * 200);
    }
    
    public void gameOverSequence() {
        
        alienSpawner.stop();
        timer.stop();
        gameOver = new gameOver();
        lost = true;
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/restart.png"));
        restart = new JButton("Click to restart", ii);
        restart.setBackground(Color.BLACK);
        restart.setLocation(175, 600);
        restart.setSize(250, 53);
        restart.addActionListener(this);
        restart.setVisible(true);
        this.add(restart);
        repaint();
    }
    
    public void pause() {
        
        paused = new Paused();
        timer.stop();
        alienSpawner.stop();
        repaint();
    }
    
    public void resume() {
        
        paused = null;
        timer.start();
        alienSpawner.start();
        
    }
}

    
   
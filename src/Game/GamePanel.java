package Game;

import GameObjects.*;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener {
    private ArrayList<Block> blocks;
    private ArrayList<Wall> walls;
    private Pop pop;
    private Katch katch;
    private BigLegs bigleg;
    private BufferedImage background;
    private ScoreKeeper scoreKeeper;


    Thread thread;
    JFrame mainFrame, startScreen;


    public GamePanel(JFrame frame, JFrame startScreen) {

        this.mainFrame = frame;
        this.startScreen = startScreen;

        init();
        reset();
        thread = new Thread(() -> {
            while (true) {
                update();
                try {
                    Thread.sleep(12);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void init() {
        BufferedImageLoader load = new BufferedImageLoader();
        scoreKeeper = new ScoreKeeper();
        background = load.loadImage("Background2.bmp");
        blocks = new ArrayList<Block>();
        walls = new ArrayList<Wall>();
        pop = new Pop(305, 350, 25, 25, "pop.png");
        katch = new Katch(280, 400, 120, 30, "katch.png");
        bigleg = new BigLegs(20, 20, 45, 37, "BigLegs.png");
    }

    public void reset() {
        for (int i = 0; i < 23; i++) //vertical walls (left)
            walls.add(new Wall(0, (i * 20), 20, 20, "Wall.gif"));
        for (int i = 0; i < 32; i++) //horizontal walls
            walls.add(new Wall((i * 20), 0, 20, 20, "Wall.gif"));
        for (int i = 0; i < 23; i++) //vertical walls (right)
            walls.add(new Wall(620, (i * 20), 20, 20, "Wall.gif"));

        for (int i = 1; i < 13; i++)
            blocks.add(new Block((i * 46 + 20), 21, 45, 20, "Block7.gif"));
        for (int i = 1; i < 13; i++)
            blocks.add(new Block((i * 46 + 20), 42, 45, 20, "Block6.gif"));
        for (int i = 0; i < 13; i++)
            blocks.add(new Block((i * 46 + 20), 63, 45, 20, "Block5.gif"));
        for (int i = 0; i < 13; i++)
            blocks.add(new Block((i * 46 + 20), 84, 45, 20, "Block4.gif"));
        for (int i = 0; i < 13; i++)
            blocks.add(new Block((i * 46 + 20), 105, 45, 20, "Block3.gif"));
        for (int i = 0; i < 13; i++)
            blocks.add(new Block((i * 46 + 20), 126, 45, 20, "Block2.gif"));
        for (int i = 0; i < 13; i++)
            blocks.add(new Block((i * 46 + 20), 147, 45, 20, "Block1.gif"));

        addKeyListener(this);
        setFocusable(true);


    }

    public void checkCollisions() {
        pop.x += pop.moveX;
        pop.y += pop.moveY;

        if (pop.x > (getWidth() - 45) || pop.x < 20) {
            pop.moveX *= -1;
        }
        if (pop.intersects(katch)) {
            pop.moveY *= -1;
            if(pop.getX() < katch.getX() + katch.getWidth() / 2 - katch.getWidth()/4) {
                System.out.println("IT WORKS #1");
                pop.moveX = pop.moveX - 1;
            }

            if(pop.getX() < katch.getX() + katch.getWidth() && pop.getX() > katch.getX() + katch.getWidth() / 4) {
                System.out.println("IT WORKS #2");
                pop.moveX = pop.moveX + 1;
            }
        }
        for (int i = 0; i < blocks.size(); i++) {
            if (pop.intersects(blocks.get(i)) && !(blocks.get(i).destroyed)) {
                pop.moveY *= -1;
                blocks.remove(i);
                scoreKeeper.addScore(25);
            }
        }
        if (pop.intersects(bigleg)) {
            thread = null;
            init();
            reset();
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        g2d.drawImage(background, 0, 0, null);
        for(int i = 0; i < blocks.size(); i++) {
            blocks.get(i).draw(g, this);
        }
        for(int i = 0; i < walls.size(); i++) {
            walls.get(i).draw(g, this);
        }
        scoreKeeper.draw(g);
        pop.draw(g, this);
        katch.draw(g, this);
        bigleg.draw(g, this);
        g.setFont(new Font("Courier New",Font.BOLD, 15));
        g.setColor(Color.RED);
        g.drawString("Lives: " + pop.getLives(), 300, 300);

    }

    private void checkOutOfBounds() {
        if (pop.y > getHeight() && pop.getLives() > 1) {
            pop.y = 200;
            pop.x = 305;
            pop.moveX = 3;
            pop.moveY = 3;
            pop.setLives( pop.getLives() - 1);
        }else if(pop.y > getHeight()) {
            thread = null;
            init();
            reset();
            mainFrame.setVisible(false);
            startScreen.setVisible(true);
        }
    }

    public void update() {
        checkCollisions();
        checkOutOfBounds();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D && katch.x < (getWidth() - katch.width) - 20) {
            katch.x += 7;
        }
        if (e.getKeyCode() == KeyEvent.VK_A && katch.x > 20) {
            katch.x -= 7;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

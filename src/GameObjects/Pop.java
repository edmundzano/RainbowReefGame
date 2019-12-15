package GameObjects;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Pop extends Rectangle {
    private Image pic;
    boolean isDestroyed;

    public int moveX, moveY;
    private int lives;



    public Pop(int x, int y,int w, int h, String s){
        this.x = x;
        this.y = y;
        lives = 3;
        moveY = 3;
        moveX = 3;

        this.width = w;
        this.height = h;
        try {
            pic = ImageIO.read(new File("resources/"+s));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics g, Component c){
        if(!isDestroyed){
            g.drawImage(pic,x,y,width,height,c);
        }
    }
    public void setLives(int l) {
        lives = l;
    }

    public int getLives() {
        return lives;
    }

}

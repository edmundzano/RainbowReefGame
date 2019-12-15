package GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Block extends Rectangle{

    private Image pic;
    public boolean isDestroyed;
    int moveX, moveY;


    public Block(int x, int y,int w, int h, String s){
        this.x = x;
        this.y = y;


        moveY = -3;
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
    public int getCoordinate (int x, int y){
        return x & y;
    }
}

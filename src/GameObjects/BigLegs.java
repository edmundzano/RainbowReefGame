package GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BigLegs extends Rectangle {
    private Image pic;
    boolean isDestroyed;




    public BigLegs(int x, int y,int w, int h, String s){
        this.x = x;
        this.y = y;

        this.width = w;
        this.height = h;

        try {
            pic = ImageIO.read(new File("resources/" +s));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics g, Component c){
        if(!isDestroyed){
            g.drawImage(pic,x,y,width,height,c);
        }
    }


}

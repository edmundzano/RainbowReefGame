package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BigLegs extends Rectangle {
    private Image pic;
    boolean destroyed;




    //x = x-coordinate, y = y-coordinate, h = height, w = width, s = location of image
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
        if(!destroyed){
            g.drawImage(pic,x,y,width,height,c);
        }
    }


}

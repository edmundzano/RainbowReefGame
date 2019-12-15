package GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LifePowerUp extends  Block{
        private Image pic;
        public boolean isDestroyed;

        public LifePowerUp(int x, int y,int w, int h, String s){
            super(x,y,w,h,s);
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
}

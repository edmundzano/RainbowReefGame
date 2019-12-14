package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Super Rainbow Reef");


        JFrame startScreen = new JFrame();
        JButton start = new JButton("Click or press spacebar to start!!");
        GamePanel panel = new GamePanel(frame, startScreen);

        start.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                startScreen.dispose();
                frame.setVisible(true);
            }
        });
        /*
        start.addActionListener(listener -> {
            frame.setVisible(true);
        });
         */


        startScreen.getContentPane().add(start);
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen.setVisible(true); //shows the frame
        startScreen.setSize(640, 480);
        startScreen.setResizable(false);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); //shows the frame
        frame.setSize(640, 480);
        frame.setResizable(false);
    }

}

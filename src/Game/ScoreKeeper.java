package Game;

import java.awt.*;

public class ScoreKeeper {

    private int score;

    public ScoreKeeper() {
        score = 0;
    }


    public void draw(Graphics g) {
        g.setFont(new Font("Courier New",Font.BOLD, 15));
        g.setColor(Color.RED);
        g.drawString("Score: " + score, 490, 440);
    }


    public void addScore(int score) {
        this.score += score;
    }
}

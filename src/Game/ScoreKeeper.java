package Game;

import java.awt.*;

public class ScoreKeeper {

    private int score;

    public ScoreKeeper() {
        init();
    }

    public void init() {
        score = 0;
    }

    public void draw(Graphics g, Component c) {
        g.setFont(new Font("Courier New",Font.BOLD, 15));
        g.setColor(Color.RED);
        g.drawString("Score: " + score, 510, 470);
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }
}

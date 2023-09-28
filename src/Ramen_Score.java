import java.awt.*;

public class Ramen_Score
{
    int score = 0;
    void draw(Graphics2D g2D)
    {
        String presentation_of_life = "x"+String.valueOf(score);

        Font font = new Font(presentation_of_life,Font.PLAIN,25);
        g2D.setFont(font);
        g2D.setPaint(Color.YELLOW);
        g2D.drawString(presentation_of_life,75,75);
    }
}

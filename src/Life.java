import java.awt.*;

public class Life
{
    int life = 3, newLife = 2;
    final int MAXLIFE = 5;
    void draw(Graphics2D g2D)
    {
        String presentation_of_life = "x"+String.valueOf(life);

        Font font = new Font(presentation_of_life,Font.PLAIN,25);
        g2D.setFont(font);
        g2D.setPaint(Color.PINK);
        g2D.drawString(presentation_of_life,800,75);
    }
}

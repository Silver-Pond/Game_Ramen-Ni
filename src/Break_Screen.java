import javax.swing.*;
import java.awt.*;

public class Break_Screen
{
    int x,y,width,height;
    boolean b_screen;
    Image ramen = new ImageIcon("images/ramen.png").getImage();
    Image ninja_sama = new ImageIcon("images/nin icon.png").getImage();
    void Screen(int x,int y,int width,int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    protected void draw(Graphics2D g2D)
    {
        g2D.setPaint(Color.DARK_GRAY);
        g2D.fillRect(0,0,Game_Panel.WIDTH,Game_Panel.HEIGHT);

        g2D.setPaint(Color.WHITE);
        g2D.setFont(new Font("Aerial",Font.ITALIC,20));
        g2D.drawString("Level: "+Game_Panel.level,350,25);

        g2D.drawImage(ninja_sama,325,275,null);

        String presentation_of_life = "x"+String.valueOf(Game_Panel.playerLife.life);

        Font font = new Font(presentation_of_life,Font.PLAIN,25);
        g2D.setFont(font);
        g2D.setPaint(Color.PINK);
        g2D.drawString(presentation_of_life,375,275);

        g2D.drawImage(ramen,25,25,null);
        Game_Panel.ramen_score.draw(g2D);
    }
}

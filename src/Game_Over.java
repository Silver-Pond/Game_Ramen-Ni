import javax.swing.*;
import java.awt.*;

public class Game_Over
{
    Image gameover = new ImageIcon("images/Game_Over.png").getImage();
    int x,y,width,height;
    Game_Over(int x,int y,int width,int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    protected void draw(Graphics2D g2D)
    {
        g2D.drawImage(gameover,x,y,null);
    }
}

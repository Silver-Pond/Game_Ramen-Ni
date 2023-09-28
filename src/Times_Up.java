import javax.swing.*;
import java.awt.*;

public class Times_Up
{
    Image time_up = new ImageIcon("images/time's up.png").getImage();
    int x,y,width,height;
    Times_Up(int x,int y,int width,int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    protected void draw(Graphics2D g2D)
    {
        g2D.drawImage(time_up,x,y,null);
    }
}

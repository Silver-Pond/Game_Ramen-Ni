import javax.swing.*;
import java.awt.*;

public class Ramen_Icon
{
    int x,y,width,height,startX;
    Image ramen = new ImageIcon("images/ramen.png").getImage();
    Ramen_Icon(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    protected void draw(Graphics2D g2D)
    {
        g2D.drawImage(ramen,x,y,null);
    }
}

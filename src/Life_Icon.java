import javax.swing.*;
import java.awt.*;

public class Life_Icon
{
    Image ninIcon = new ImageIcon("images/nin icon.png").getImage();
    int x,y,width,height;
    Life_Icon(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    protected void draw(Graphics2D g2D)
    {
        g2D.drawImage(ninIcon,x,y,null);
    }
}

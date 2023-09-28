import javax.swing.*;
import java.awt.*;

public class Ocean
{
    Image mizu = new ImageIcon("images/mizu.jpg").getImage();
    int x,y,width,height;
    Ocean(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    protected void draw(Graphics2D g2D)
    {
        g2D.drawImage(mizu,x,y,null);
    }
}

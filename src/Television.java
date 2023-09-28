import javax.swing.*;
import java.awt.*;

public class Television
{
    Image tele = new ImageIcon("images/tele.png").getImage();
    int x,y,width,height,startX;
    Television(int x, int y, int width, int height)
    {
        this.x = x;
        this.startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    protected int Set(int cameraX)
    {
        x = startX + cameraX;
        return x;
    }
    protected void draw(Graphics2D g2D)
    {
        g2D.drawImage(tele,x,y,null);
    }
}

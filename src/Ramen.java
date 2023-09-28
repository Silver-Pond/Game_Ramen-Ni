import javax.swing.*;
import java.awt.*;

public class Ramen
{
    Rectangle hitBox;
    int x,y,width,height,startX;
    Image ramen = new ImageIcon("images/ramen.png").getImage();
    Ramen(int x, int y, int width, int height)
    {
        this.x = x;
        this.startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle(x,y,width,height);
    }
    int Set(int cameraX)
    {
        x = startX + cameraX;
        hitBox.x = x;
        return x;
    }
    protected void draw(Graphics2D g2D)
    {
        g2D.drawImage(ramen,x,y,null);
    }
}

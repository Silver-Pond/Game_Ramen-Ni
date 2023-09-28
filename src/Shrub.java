import javax.swing.*;
import java.awt.*;

public class Shrub
{
    Image shrub = new ImageIcon("images/shrub.png").getImage();
    Rectangle hitBox;
    int x,y,width,height,startX;
    Shrub(int x, int y, int width, int height)
    {
        this.x = x;
        this.startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle(x,y,width,height);
    }
    protected int Set(int cameraX)
    {
        x = startX + cameraX;
        hitBox.x = x;
        return x;
    }
    protected void draw(Graphics2D g2D)
    {
        g2D.drawImage(shrub,x,y,null);
    }
}

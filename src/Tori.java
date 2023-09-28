import javax.swing.*;
import java.awt.*;

public class Tori
{
    Image tori = new ImageIcon("images/torii.png").getImage();
    Rectangle hitBox;
    int x,y,width,height,startX;
    Tori(int x, int y, int width, int height)
    {
        this.x = x;
        this.startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle(x +150,y,width,height);
    }
    protected int Set(int cameraX)
    {
        x = startX + cameraX;
        hitBox.x = x;
        return x;
    }
    protected void draw(Graphics2D g2D)
    {
        g2D.drawImage(tori,x,y,null);
    }
}

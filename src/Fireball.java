import javax.swing.*;
import java.awt.*;

public class Fireball
{
    Image fireball = new ImageIcon("images/fireball.png").getImage();
    int x,y,width,height,startX;
    Rectangle hitBox;
    double velocity;
    boolean blast;
    Fireball(int x,int y,int width,int height)
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
    void drawProjectile(Graphics2D g2D)
    {
        g2D.drawImage(fireball,x,y,null);
    }
}

import javax.swing.*;
import java.awt.*;

public class Sign
{
    Image sign = new ImageIcon("images/sign.png").getImage();
    int x,y,width,height,startX;
    Rectangle hitBox;
    Sign(int x,int y,int width,int height)
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
    void draw(Graphics2D g2D)
    {
        g2D.drawImage(sign,x,y,null);
    }
}

import javax.swing.*;
import java.awt.*;

public class Onikasa extends Enemies
{
    Image onikasa_high = new ImageIcon("images/Enemies/onikasa_high.png").getImage();
    Image onikasa_low = new ImageIcon("images/Enemies/onikasa_low.png").getImage();
    int x,y,width,height,onikasa_animation = 0,distance;
    boolean low, high, attack;
    Onikasa(int x,int y,int width,int height)
    {
        this.x = x;
        this.startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle(x,y,width,height);
    }
    @Override
    int Set(int cameraX)
    {
        x = startX + cameraX;
        hitBox.x = x;
        return x;
    }

    @Override
    void drawBubble(Graphics2D g2D)
    {
        if(low == true)
        {
            g2D.drawImage(onikasa_low,x,y,null);
        } else if (high == true)
        {
            g2D.drawImage(onikasa_high,x,y,null);
        }
    }
}

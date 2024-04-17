import javax.swing.*;
import java.awt.*;

public class Bakujin extends Enemies
{
    Image bakujin = new ImageIcon("images/Enemies/bakujin.png").getImage();
    int x,y,width,height,movement = 0;
    boolean left_right_contact = false;
    Bakujin(int x,int y,int width,int height)
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
        g2D.drawImage(bakujin,x,y,null);
    }
}

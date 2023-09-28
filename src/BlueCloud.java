import javax.swing.*;
import java.awt.*;

public class BlueCloud extends Clouds
{
    Image blueCloud = new ImageIcon("images/blue cloud.png").getImage();
    int x,y,width,height, note = 0, cloudspeed = 1;
    BlueCloud(int x,int y,int width,int height)
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
    void drawCloud(Graphics2D g2D)
    {
        g2D.drawImage(blueCloud,x,y,null);
    }
}

import javax.swing.*;
import java.awt.*;

public class RedCloud extends Clouds
{
    Image redCloud = new ImageIcon("images/red cloud.png").getImage();
    int x,y,width,height;
    RedCloud(int x,int y,int width,int height)
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
        g2D.drawImage(redCloud,x,y,null);
    }
}

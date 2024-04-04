import javax.swing.*;
import java.awt.*;

public class GoldenCloud extends Clouds
{
    Image goldenCloud = new ImageIcon("images/golden cloud.png").getImage();
    int x,y,width,height, golden_note = 0, golden_velocity = 1;
    boolean touch = false;
    GoldenCloud(int x,int y,int width,int height)
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
        g2D.drawImage(goldenCloud,x,y,null);
    }

}

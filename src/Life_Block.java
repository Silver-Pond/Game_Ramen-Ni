import javax.swing.*;
import java.awt.*;

public class Life_Block extends Blocks
{
    Image life_box = new ImageIcon("images/life box.png").getImage();
    Life_Block(int x, int y, int width, int height)
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
    void drawBlock(Graphics2D g2D)
    {
        g2D.drawImage(life_box,x,y,null);
    }
}

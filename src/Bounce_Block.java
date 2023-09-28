import java.awt.*;

public class Bounce_Block extends Blocks
{
    boolean above,below;
    Bounce_Block(int x, int y, int width, int height)
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
        g2D.setPaint(new Color(150,60,90,200));
        g2D.fillRoundRect(x,y,width,height,8,8);
    }
}

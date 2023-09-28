import java.awt.*;

public class Normal_Block extends Blocks
{
    Normal_Block(int x, int y, int width, int height)
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
        if(Game_Panel.level == 3)
        {
            g2D.setPaint(new Color(150,100,60,180));
            g2D.fillRoundRect(x,y,width,height,8,8);
            g2D.setPaint(Color.BLACK);
            g2D.drawRoundRect(x,y,width,height,8,8);
        }
        else
        {
            g2D.setPaint(new Color(150,150,180));
            g2D.fillRect(x,y,width,height);
            g2D.setPaint(Color.BLACK);
            g2D.drawRect(x,y,width,height);
        }
    }
}

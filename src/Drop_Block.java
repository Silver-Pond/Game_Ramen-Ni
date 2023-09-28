import java.awt.*;

public class Drop_Block extends Blocks
{
    boolean stepped_on;
    double velocity = 0;
    int count = 0;
    Drop_Block(int x, int y, int width, int height)
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
        switch (Game_Panel.level)
        {
            case 1 ->
            {
                if(stepped_on){g2D.setPaint(Color.BLACK);}
                else if(!stepped_on){g2D.setPaint(new Color(60,120,190));}
                g2D.fillRect(x,y,width,height);

                if(stepped_on){g2D.setPaint(Color.WHITE);}
                else if(!stepped_on){g2D.setPaint(Color.BLACK);}
                g2D.drawRect(x,y,width,height);
            }
            case 2 ->
            {
                if(stepped_on){g2D.setPaint(Color.BLACK);}
                else if(!stepped_on){g2D.setPaint(new Color(60,120,190));}
                g2D.fillRect(x,y,width,height);

                if(stepped_on){g2D.setPaint(Color.WHITE);}
                else if(!stepped_on){g2D.setPaint(Color.BLACK);}
                g2D.drawRect(x,y,width,height);
            }
            case 3 ->
            {
                if(stepped_on){g2D.setPaint(Color.BLACK);}
                else if(!stepped_on){g2D.setPaint(new Color(60,250,120, 90));}
                g2D.fillRoundRect(x,y,width,height,10,10);

                if(stepped_on){g2D.setPaint(Color.WHITE);}
                else if(!stepped_on){g2D.setPaint(Color.BLACK);}
                g2D.drawRoundRect(x,y,width,height,10,10);
            }
        }
    }
}

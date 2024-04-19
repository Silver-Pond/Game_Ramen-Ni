import javax.swing.*;
import java.awt.*;

public class Tomoe extends Enemies
{
    Image tomoe_high = new ImageIcon("images/Enemies/tomoe.png").getImage();
    Image tomoe_mid = new ImageIcon("images/Enemies/tomoe_mid.png").getImage();
    Image tomoe_low = new ImageIcon("images/Enemies/tomoe_low.png").getImage();
    Image tomoe_mad = new ImageIcon("images/Enemies/tomoe_mad.png").getImage();
    Image tomoe_high_reverse = new ImageIcon("images/Enemies/tomoe_high_reverse.png").getImage();
    Image tomoe_mid_reverse = new ImageIcon("images/Enemies/tomoe_mid_reverse.png").getImage();
    Image tomoe_low_reverse = new ImageIcon("images/Enemies/tomoe_low_reverse.png").getImage();
    Image tomoe_mad_reverse = new ImageIcon("images/Enemies/tomoe_mad_reverse.png").getImage();
    int x,y,width,height,tomoe_animation = 0,distance;
    double velocity, y_velocity;
    boolean low,mid,high,mad, falling = true, collide;
    Tomoe(int x,int y,int width,int height)
    {
        this.x = x;
        this.startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle(x,y,width,height);
    }
    void collision()
    {
        hitBox.x += velocity;
        x += velocity;

        hitBox.y += y_velocity;
        y += y_velocity;

        falling = true;
        collide = false;
        //calling methods
        xRegBlockCollsionCheck();
        yRegBlockCollisionCheck();
        yLandCollisionCheck();
        //falling and fall prevention code
        if(falling)
        {
            y_velocity += 0.3;
        }
        else if(!falling && y_velocity > 0)
        {
            y_velocity = 0;
        }
        hitBox.x = x;
        hitBox.y = y;
    }
    private void yLandCollisionCheck()
    {
        for(Land land: Game_Panel.landTiles)
        {
            if(hitBox.intersects(land.hitBox))
            {
                if(y_velocity > 0)
                {
                    falling = false;
                    hitBox.y = land.y - (land.height*2);
                }
            }
            y = hitBox.y;
        }
    }
    private void xRegBlockCollsionCheck()
    {
        for(Normal_Block normal_block: Game_Panel.normal_blocks)
        {
            if(hitBox.intersects(normal_block.hitBox))
            {
                if(velocity > 0)
                {
                    collide = true;
                    velocity = 0;
                } else if (velocity < 0)
                {
                    collide = true;
                    velocity = 0;
                }
            }
            x = hitBox.x;
        }
    }
    private void yRegBlockCollisionCheck()
    {
        for (Normal_Block normal_block : Game_Panel.normal_blocks)
        {
            if (hitBox.intersects(normal_block.hitBox))
            {
                if (y_velocity > 0)
                {
                    falling = false;
                    hitBox.y = normal_block.y - (normal_block.height * 2);
                }
            }
            y = hitBox.y;
        }
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
        if(low && distance >= 0)
        {
            g2D.drawImage(tomoe_low,x,y,null);
        } else if(mid && distance >= 0)
        {
            g2D.drawImage(tomoe_mid,x,y,null);
        } else if(high && distance >= 0)
        {
            g2D.drawImage(tomoe_high,x,y,null);
        }
        if(mad && distance >= 0)
        {
            g2D.drawImage(tomoe_mad,x,y,null);
        }

        if(low && distance < 0)
        {
            g2D.drawImage(tomoe_low_reverse,x,y,null);
        } else if(mid && distance < 0)
        {
            g2D.drawImage(tomoe_mid_reverse,x,y,null);
        } else if(high && distance < 0)
        {
            g2D.drawImage(tomoe_high_reverse,x,y,null);
        }
        if(mad && distance < 0)
        {
            g2D.drawImage(tomoe_mad_reverse,x,y,null);
        }
    }
}

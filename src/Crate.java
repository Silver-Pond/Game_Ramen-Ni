import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Crate extends Blocks
{
    boolean falling = true;
    static boolean keyCrate;
    double y_velocity, x_velocity;
    Image crate = new ImageIcon("images/crate.png").getImage();
    Crate(int x, int y, int width, int height)
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
        hitBox.y += y_velocity;
        y += y_velocity;

        falling = true;

        yLandCollisionCheck();

        if(falling)
        {
            y_velocity += 0.3;
        }
        else if(!falling && y_velocity > 0)
        {
            y_velocity = 0;
        }
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
            //System.out.println(y_velocity);
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
    void drawBlock(Graphics2D g2D)
    {
        g2D.drawImage(crate,x,y,null);
    }
}

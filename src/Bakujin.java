import javax.swing.*;
import java.awt.*;

public class Bakujin extends Enemies
{
    Image bakujin = new ImageIcon("images/Enemies/bakujin.png").getImage();
    int x,y,width,height;
    double xMovement = 0, yMovement = 0;
    boolean falling, direction = false;
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
    void collision()
    {
        hitBox.y += yMovement;
        y += yMovement;

        falling = true;
        //Calling x collision check methods
        xBoxCollisionCheck();
        //Calling y collision check methods
        yLandCollisionCheck();
        //falling and fall prevention code
        if(falling)
        {
            yMovement += 0.3;
        }
        else if(!falling && yMovement > 0)
        {
            yMovement = 0;
        }
        hitBox.y = y;
    }
    private void xBoxCollisionCheck()
    {
        for(Box box: Game_Panel.boxes)
        {
            if(hitBox.intersects(box.hitBox))
            {
                if(!direction)
                    direction = true;
                else
                    direction = false;
            }
        }
    }
    private void yLandCollisionCheck()
    {
        for(Land land: Game_Panel.landTiles)
        {
            if(hitBox.intersects(land.hitBox))
            {
                if(yMovement > 0)
                {
                    falling = false;
                    hitBox.y = land.y - (land.height*2);
                }
            }
            y = hitBox.y;
        }
    }

    @Override
    void drawBubble(Graphics2D g2D)
    {
        g2D.drawImage(bakujin,x,y,null);
    }
}

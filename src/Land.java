import javax.swing.*;
import java.awt.*;

public class Land
{
    Image ground = new ImageIcon("images/ground.png").getImage();
    Image ash_ground = new ImageIcon("images/tile.png").getImage();
    Rectangle hitBox;
    int x,y,width,height,startX;
    Land(int x, int y, int width, int height)
    {
        this.x = x;
        this.startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle(x,y,width,height);
    }
    protected int Set(int cameraX)
    {
        x = startX + cameraX;
        hitBox.x = x;
        return x;
    }
    protected void draw(Graphics2D g2D)
    {
        switch (Game_Panel.level)
        {
            case 1 ->
            {
                g2D.drawImage(ground,x,y,null);
            }
            case 2 ->
            {
                g2D.drawImage(ground,x,y,null);
            }
            case 3 ->
            {
                g2D.drawImage(ash_ground,x,y,null);
            }
            case 4 ->
            {
                g2D.drawImage(ground,x,y,null);
            }
            case 5 ->
            {
                g2D.drawImage(ash_ground,x,y,null);
            }
            case 6 ->
            {
                g2D.drawImage(ground,x,y,null);
            }
            case 7 ->
            {
                g2D.drawImage(ash_ground,x,y,null);
            }
        }
    }
}

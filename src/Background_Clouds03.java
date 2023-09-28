import javax.swing.*;
import java.awt.*;

public class Background_Clouds03 extends Backgrounds
{
    Image background = new ImageIcon("images/Background Clouds C.png").getImage();
    Background_Clouds03(int x, int y, int width, int height)
    {
        this.x = x;
        this.startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    @Override
    int Set(int cameraX)
    {
        x = startX + cameraX;
        return x;
    }
    @Override
    void draw(Graphics2D g2D)
    {
        g2D.drawImage(background,x,y,null);
    }
}

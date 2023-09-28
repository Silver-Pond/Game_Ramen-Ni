import javax.swing.*;
import java.awt.*;

public class Light_Post
{
    Image post = new ImageIcon("images/post.png").getImage();
    Rectangle hitBox;
    int x,y,width,height,startX;
    Light_Post(int x, int y, int width, int height)
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
        g2D.drawImage(post,x,y,null);
    }
}

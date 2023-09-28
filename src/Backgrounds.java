import java.awt.*;

public abstract class Backgrounds
{
    int x,y,width,height,startX;
    abstract int Set(int cameraX);
    abstract void draw(Graphics2D g2D);
}

import java.awt.*;

public abstract class Blocks
{
    Rectangle hitBox;
    int x,y,width,height,startX;
    abstract int Set(int cameraX);
    abstract void drawBlock(Graphics2D g2D);
}
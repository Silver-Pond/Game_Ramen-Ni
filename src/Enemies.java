import java.awt.*;

public abstract class Enemies
{
    int startX;
    Rectangle hitBox;
    abstract int Set(int cameraX);
    abstract void drawBubble(Graphics2D g2D);
}

import java.awt.*;

public abstract class Enemy_Attacks
{
    int startX;
    Rectangle hitBox;
    abstract int Set(int cameraX);
    abstract void drawBubble(Graphics2D g2D);
}

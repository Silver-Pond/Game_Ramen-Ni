import java.awt.*;

public abstract class Clouds
{
    int startX;
    Rectangle hitBox;
    abstract int Set(int cameraX);
    abstract void drawCloud(Graphics2D g2D);
}

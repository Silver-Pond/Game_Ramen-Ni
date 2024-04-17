import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class DirtyBubble extends Enemies
{
    Image dirtyBubble = new ImageIcon("images/Enemies/Dirty_Bubble.png").getImage();
    Image dirtyBubbleClosed = new ImageIcon("images/Enemies/Dirty_Bubble_Closed.png").getImage();
    Image dirtyBubbleReverse = new ImageIcon("images/Enemies/Dirty_Bubble Reverse.png").getImage();
    Image dirtyBubbleClosedReverse = new ImageIcon("images/Enemies/Dirty_Bubble_Closed Reverse.png").getImage();
    Image dirtyBubbleV2 = new ImageIcon("images/Enemies/Dirty_Bubble_V2.png").getImage();
    Image dirtyBubbleV2Closed = new ImageIcon("images/Enemies/Dirty_Bubble_V2_Closed.png").getImage();
    Image dirtyBubbleV2Reverse = new ImageIcon("images/Enemies/Dirty_Bubble_V2_Open Rverse.png").getImage();
    Image dirtyBubbleV2ClosedReverse = new ImageIcon("images/Enemies/Dirty_Bubble_V2_Closed Reverse.png").getImage();
    int x,y,width,height, bubble_tracker = 0, bubble_note = 150, bubble_animation = 0, bubble_velocity = 1;
    boolean open = true, closed;
    DirtyBubble(int x,int y,int width,int height)
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
    @Override
    void drawBubble(Graphics2D g2D)
    {
        switch (Game_Panel.level)
        {
            case 1 ->
            {
                if(open)
                {
                    if(bubble_tracker < 150 && bubble_note > 0){g2D.drawImage(dirtyBubble,x,y,null);}
                    if(bubble_tracker == 150 && bubble_note != 150){g2D.drawImage(dirtyBubbleReverse,x,y,null);}
                } else if (closed)
                {
                    if(bubble_tracker < 150 && bubble_note > 0){g2D.drawImage(dirtyBubbleClosed,x,y,null);}
                    if(bubble_tracker == 150 && bubble_note != 150){g2D.drawImage(dirtyBubbleClosedReverse,x,y,null);}
                }
            }
            case 2 ->
            {
                if(open)
                {
                    if(bubble_tracker < 150 && bubble_note > 0){g2D.drawImage(dirtyBubbleV2,x,y,null);}
                    if(bubble_tracker == 150 && bubble_note != 150){g2D.drawImage(dirtyBubbleV2Reverse,x,y,null);}
                } else if (closed)
                {
                    if(bubble_tracker < 150 && bubble_note > 0){g2D.drawImage(dirtyBubbleV2Closed,x,y,null);}
                    if(bubble_tracker == 150 && bubble_note != 150){g2D.drawImage(dirtyBubbleV2ClosedReverse,x,y,null);}
                }
            }
        }
    }
}

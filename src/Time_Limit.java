import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Time_Limit
{
    Timer timer;
    int seconds = 59, minutes = 30, time = 1860;
    String seconds_string = String.format("%02d",seconds)
            , minutes_string = String.format("%02d",minutes);
    void Tick()
    {
            timer = new Timer(125, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(Start_Panel.paused == false)
                    {
                        if(minutes == 0 && seconds == 0)
                        {
                            seconds++;
                            timer.stop();
                        }

                        seconds--;
                        time --;

                        if(seconds == -1)
                        {
                            seconds = 59;
                            minutes--;
                        }
                        seconds_string = String.format("%02d",seconds);
                        minutes_string = String.format("%02d",minutes);
                        //System.out.println("Time: "+minutes_string+":"+seconds_string);
                    }
                }
            }); timer.start();
    }
    void draw(Graphics2D g2D)
    {
        Font font = new Font(Font.SERIF,Font.ITALIC,25);
        g2D.setFont(font);

        g2D.setPaint(Color.GREEN);
        if(minutes <= 20){g2D.setPaint(Color.YELLOW);}
        if(minutes <= 10){g2D.setPaint(Color.RED);}
        g2D.drawString(minutes_string+":"+seconds_string,750,50);
    }
}

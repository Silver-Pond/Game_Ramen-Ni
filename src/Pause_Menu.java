import javax.swing.*;
import java.awt.*;

public class Pause_Menu extends Thread
{
    int x,y,width,height;
    void Menu(int x,int y,int width,int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    void draw(Graphics2D g2D)
    {
        g2D.setPaint(Color.BLACK);
        g2D.fillRect(0,0,900,600);

        Font font = new Font(Font.SERIF,2,25);

            g2D.setFont(font);
            g2D.setPaint(Color.GRAY);
            g2D.drawString("Backspace",25,50);
            g2D.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,25));
            g2D.drawString("X",150,50);


            g2D.setPaint(Color.BLUE);
            g2D.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,25));
            g2D.drawString("D ",325,200);
            g2D.drawString("A ",325,225);
            g2D.drawString("2 ",325,250);
            g2D.drawString("4 ",325,275);
            g2D.drawString("6 ",325,300);
            g2D.setPaint(Color.PINK);
            g2D.drawString("-> Forward Movement",350,200);
            g2D.drawString("-> Backward Movement",350,225);
            g2D.drawString("-> Jump",350,250);
            g2D.drawString("-> 1st Attack",350,275);
            g2D.drawString("-> 2nd Attack",350,300);
    }
}

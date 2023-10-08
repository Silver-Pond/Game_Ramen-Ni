import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Random_Box extends Blocks
{
    boolean attacked, box;
    Image randobox = new ImageIcon("images/random_box.png").getImage();
    Random_Box(int x, int y, int width, int height)
    {
        this.x = x;
        this.startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle(x,y,width,height);
    }
    void RandomPrize()
    {
        Random random = new Random();

        if(attacked)
        {
            switch (random.nextInt(3))
            {
                case 0 ->
                {
                    Timer boxyMama = new Timer(1500, new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            box = true;
                        }
                    }); boxyMama.start();
                    box = false;
                }
                case 1 ->
                {
                    if(Game_Panel.playerLife.life != Game_Panel.playerLife.MAXLIFE && Game_Panel.playerLife.newLife != Game_Panel.playerLife.MAXLIFE+1)
                    {
                        Main.hus.Audio();
                        Game_Panel.playerLife.life++;
                        Game_Panel.playerLife.newLife++;
                    }
                }
                case 2 ->
                {
                    Main.suh.Audio();
                    if(Game_Panel.playerLife.life != 0 && Game_Panel.playerLife.newLife != -1)
                    {
                        Game_Panel.playerLife.life--;
                        Game_Panel.playerLife.newLife--;
                    }
                    if(Game_Panel.playerLife.life <= 0 && Game_Panel.playerLife.newLife <= -1)
                    {
                        Game_Panel.ninja.dyingAction();
                    }
                }
                case 3 ->
                {
                    Main.rsc.Audio();
                    Game_Panel.ramen_score.score++;
                }
            }
        }
    }
    @Override
    int Set(int cameraX)
    {
        x = startX + cameraX;
        hitBox.x = x;
        return x;
    }
    @Override
    void drawBlock(Graphics2D g2D)
    {
        g2D.drawImage(randobox,x,y,null);

        if(box)
        {
            Font font = new Font("Aerial",Font.BOLD,Font.TRUETYPE_FONT);
            g2D.setPaint(new Color(120,120,50));
            g2D.setFont(font);
            g2D.drawString("BOXY MAMA!", 445,25);
        }
    }
}

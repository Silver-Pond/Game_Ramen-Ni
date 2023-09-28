import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Start_Panel extends JPanel
{
    final int WIDTH = 450;
    final int HEIGHT = 450;
    final int UNIT_SIZE = 25;
    JButton startButton = new JButton("start");
    JButton exitButton = new JButton("exit");
    Image wallpaper = new ImageIcon("images/kore_wa_kuroi_sekai_desu.png").getImage();
    Image title = new ImageIcon("images/Ramen Nigga.png").getImage();
    static boolean running, paused;
    Start_Panel()
    {
        Main.rnt.Audio();
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.add(startButton);

            startButton.setBounds(175,300,100,50);
            startButton.setFocusable(false);
            startButton.setForeground(Color.PINK);
            startButton.setBackground(Color.BLACK);
            startButton.setBorder(BorderFactory.createLineBorder(Color.PINK,3,true));

        this.add(exitButton);

            exitButton.setBounds(175,375,100,50);
            exitButton.setFocusable(false);
            exitButton.setForeground(Color.PINK);
            exitButton.setBackground(Color.BLACK);
            exitButton.setBorder(BorderFactory.createLineBorder(Color.PINK,3,true));

        setStartButton();
        setExitButton();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        draw(graphics2D);
    }
    private void draw(Graphics2D graphics2D)
    {
        /**for(int i = 0; i < WIDTH/UNIT_SIZE; i++)
        {
            graphics2D.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,HEIGHT);
            graphics2D.drawLine(0,i*UNIT_SIZE,WIDTH,i*UNIT_SIZE);
        }**/
        graphics2D.drawImage(wallpaper,0,0,null);
        graphics2D.drawImage(title,25,UNIT_SIZE,null);
    }
    private void setStartButton()
    {
        Start_Screen startScreen = new Start_Screen();

        ActionListener start = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                running = true;

                Main.rnt.clip.stop();

                if(running)
                {
                    Game_Panel gamePanel = new Game_Panel();
                    Window window = new Window(gamePanel);
                    Start_Screen.screen.setVisible(false);
                }
            }
        };startButton.addActionListener(start);
    }
    private void setExitButton()
    {
        ActionListener exit = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        };exitButton.addActionListener(exit);
    }
}

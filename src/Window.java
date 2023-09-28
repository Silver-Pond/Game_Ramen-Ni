import javax.swing.*;

public class Window extends JFrame
{
    final String TITLE = "Kuroimaru Murasaki";
    Window(Game_Panel gamePanel)
    {
        this.setTitle(TITLE);
        this.add(gamePanel);
        this.pack();
        this.setResizable(false);
        this.addKeyListener(new KeyChecker(gamePanel));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

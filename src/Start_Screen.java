import javax.swing.*;

public class Start_Screen
{
    static JFrame screen = new JFrame();

    void Screen(Start_Panel startPanel)
    {
        screen.setTitle(null);
        screen.add(startPanel);
        screen.pack();
        screen.setResizable(false);
        screen.setLocationRelativeTo(null);
        screen.setDefaultCloseOperation(screen.DISPOSE_ON_CLOSE);
        screen.setVisible(true);
    }
}

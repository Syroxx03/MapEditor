import javax.swing.*;
import java.awt.*;
/****/
public class UserInterface extends JFrame implements Runnable
{
    /****/
    public UserInterface() throws Exception
    {
        super();
        this.setReducedSize();
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.black);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }
    /****/
    private void setReducedSize()
    {
        int vScreenW = Toolkit.getDefaultToolkit().getScreenSize().width;
        int vScreenH = Toolkit.getDefaultToolkit().getScreenSize().height;
        int vFW = (int)(vScreenW / 2 - 0.3 * vScreenW);
        int vFH = (int)(vScreenH / 2 - 0.3 * vScreenH);
        this.setBounds(vFW , vFH, (int)(0.6 * vScreenW),(int)(0.6 * vScreenH));
    }
    /****/
    @Override public void run()
    {
        int vFPS = 60;
        while(this.isActive())
        {
            this.repaint();
            try {Thread.sleep(1000/vFPS);}
            catch (InterruptedException e){throw new RuntimeException(e);}
        }
    }
}
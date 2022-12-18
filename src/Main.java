import javax.swing.*;
import java.awt.*;
/****/
public class Main
{
    public static void main(String[] args) throws Exception
    {
        try {javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch(Exception e){System.out.println("setLookAndFeel error");}

        OptionPanel vOptionPanel = new OptionPanel();
        TilePanel vTilePanel = new TilePanel();
        DrawArea vDrawArea = new DrawArea();

        UserInterface vGUI = new UserInterface();
        JPanel vPanel = new JPanel(new BorderLayout());
        vPanel.add(vOptionPanel, BorderLayout.NORTH);
        vPanel.add(vTilePanel);
        vGUI.add(vPanel, BorderLayout.EAST);
        vGUI.add(vDrawArea);
        vGUI.revalidate();
        vTilePanel.revalidate();
        vTilePanel.setButtonsImageSize();

        Thread vRepaintThread = new Thread(vGUI);
        vRepaintThread.start();

        Mouse vMouse = new Mouse();
        GameEngine vGE = new GameEngine(vMouse);
        vOptionPanel.addActionListener(vGE);
        vTilePanel.addActionListener(vGE);
        vDrawArea.addMouseListener(vMouse);
        vDrawArea.addMouseMotionListener(vMouse);

    }
}
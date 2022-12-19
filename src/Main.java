import java.awt.*;
import java.io.File;

/****/
public class Main
{
    public static void main(String[] args) throws Exception
    {
        FilePanel vFilePanel = new FilePanel();
        ToolPanel vToolPanel  = new ToolPanel();
        TilePanel vTilePanel = new TilePanel();
        InfoPanel vInfoPanel = new InfoPanel();
        DrawArea vDrawArea = new DrawArea();

        vFilePanel.setPreferredSize(new Dimension(-1,32));
        vToolPanel.setPreferredSize(new Dimension(32,-1));
        vTilePanel.setPreferredSize(new Dimension(200,-1));
        vInfoPanel.setPreferredSize(new Dimension(-1,32));

        UserInterface vGUI = new UserInterface();
        vGUI.add(vTilePanel, BorderLayout.EAST);
        vGUI.add(vToolPanel, BorderLayout.WEST);
        vGUI.add(vFilePanel, BorderLayout.NORTH);
        vGUI.add(vInfoPanel, BorderLayout.SOUTH);
        vGUI.add(vDrawArea, BorderLayout.CENTER);

        Thread vRepaintThread = new Thread(vGUI);
        vRepaintThread.start();

        Mouse vMouse = new Mouse();
        GameEngine vGE = new GameEngine(vMouse);
        vToolPanel.addActionListener(vGE);
        vTilePanel.addActionListener(vGE);
        vDrawArea.addMouseListener(vMouse);
        vDrawArea.addMouseMotionListener(vMouse);

    }
}
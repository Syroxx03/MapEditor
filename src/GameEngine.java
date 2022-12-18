import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
/****/
public class GameEngine implements ActionListener
{
    private final Mouse aMouse;
    private final HashMap<String , Tile> aTiles;
    private final HashMap<String , Tool> aTools;
    public GameEngine (final Mouse pMouse)
    {
        this.aMouse = pMouse;
        this.aTiles = new HashMap<>();
        this.aTools = new HashMap<>();
        this.setTools();
    }
    /****/
    private void setTools()
    {
        this.aTools.put("Pen" , new Pen());
        this.aTools.put("Gum" , new Gum());
    }
    /****/
    @Override public void actionPerformed(ActionEvent e)
    {
        JButton vB = (JButton)e.getSource();
        switch(e.getActionCommand())
        {
            case "Option":break;
            case "Tool": if(this.aTools.containsKey(vB.getText()))
                this.aMouse.setTool(this.aTools.get(vB.getText()));
                else System.out.println(vB.getText());break;
            default:try {this.setTile(e.getActionCommand());}catch (IOException ioe)
            {System.out.println("Image not found :"+ e.getActionCommand());}
        }
    }
    /****/
    private void setTile(final String pImagePath) throws IOException
    {
        if(!this.aTiles.containsKey(pImagePath))
            this.aTiles.put(pImagePath, new Tile(pImagePath));
        this.aTools.get("Pen").setTile(this.aTiles.get(pImagePath));
        this.aMouse.setTool(this.aTools.get("Pen"));
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/****/
public class ToolPanel extends JPanel
{
    /****/
    public ToolPanel()
    {
        this.setBackground(new Color(20,20,30));
        this.addButton("Pen");
        this.addButton("Gum");
    }
    /****/
    public void addActionListener(final ActionListener pAL)
    {
        for(Component vC: this.getComponents())
            ((JButton)vC).addActionListener(pAL);
    }
    /****/
    public void addButton(final String pText)
    {
        JButton vB = new JButton(pText);
        vB.setForeground(new Color(125,125,150));
        vB.setContentAreaFilled(false);
        vB.setActionCommand("Tool");
        vB.setFocusable(false);
        vB.setSize(vB.getSize());
        this.add(vB);
    }
}

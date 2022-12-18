import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
/****/
public class OptionPanel extends JPanel
{
    private ArrayList<JButton> aButtons;
    static int ClmnNbr = 3;
    /****/
    public OptionPanel()
    {
        this.setLayout( new GridLayout(4, ClmnNbr));
        this.setBackground(new Color(20,20,30));
        this.setPreferredSize(new Dimension(200,100));
        this.createButtons();
    }
    /****/
    public void addActionListener(final ActionListener pAL)
    {
        for(JButton vButton: this.aButtons)
            vButton.addActionListener(pAL);
    }
    /****/
    @Override public void setSize(final Dimension pSize){this.setSize(pSize.width, pSize.height);}
    @Override public void setSize(final int pW, final int pH)
    {
        super.setSize(pW, pH);
        for(JButton vButton: this.aButtons)
        {
           int vButtonWidth = this.getWidth() / ClmnNbr;
           Font vFont = this.getFontFromWidth(vButton.getText(), vButtonWidth);
           vButton.setFont(vFont);
        }
    }
    /****/
    private void createButtons()
    {
        this.aButtons = new ArrayList<>();
        String[] vOptions = new String[]{"Save", "Open","Clear","Undo","Redo", "Hitbox", "Grid"};
        String[] vTools = new String[]{"Select", "Square", "Gum", "Pen","Move"};
        for(String vO: vOptions){this.aButtons.add(this.getButton(vO,"Option"));}
        for(String vO: vTools){this.aButtons.add(this.getButton(vO,"Tool"));}
        for(JButton vB:this.aButtons){this.addButton(vB);}
    }
    /****/
    private void addButton(final JButton pButton)
    {
        int vButtonNbr = this.getComponentCount();
        this.add(pButton);
        int vClmn = vButtonNbr % ClmnNbr;
        int vRow = (vButtonNbr-1)/ClmnNbr;
        pButton.setBackground(new Color(255, 43 + 27 * ((3 - vClmn) + vRow), 255 - 42 * ((3 - vClmn) + vRow)));
    }
    /****/
    private JButton getButton(final String pT, final String pAC)
    {
        JButton vButton = new JButton();
        vButton.setText(this.getResizedText(pT));
        vButton.setActionCommand(pAC);
        vButton.setFocusable(false);
        vButton.setBorder(null);
        return vButton;
    }
    /****/
    private String getResizedText(final String pText)
    {
        StringBuilder vText = new StringBuilder(pText);
        while (vText.length() < 8)
            vText = new StringBuilder(" " + vText + " ");
        return vText.toString();
    }
    /****/
    private Font getFontFromWidth(final String pText,final  int pButtonWidth)
    {
        Font vFont =  new  Font("Poor Richard", Font.BOLD, 0);
        FontRenderContext vFRC =  new  FontRenderContext( new  AffineTransform(), true, true);
        int vCharSize = 0;
        while (vFont.getStringBounds(pText, vFRC).getWidth() < (pButtonWidth - 0.15*pButtonWidth))
        {
            vFont =  new  Font("Poor Richard", Font.BOLD, vCharSize);
            vCharSize++;
        }
        return vFont;
    }
}
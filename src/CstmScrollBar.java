import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**ScrollBar without buttons*/
public class CstmScrollBar extends BasicScrollBarUI
{
    /****/
    @Override public Dimension getPreferredSize(final JComponent c)
    {
        return  new  Dimension(6, 6);
    }
    /****/
    @Override protected JButton createIncreaseButton(final int pOrientation)
    {
        return this.getEmptyButton();
    }
    /****/
    @Override protected JButton createDecreaseButton(final int pOrientation)
    {
        return this.getEmptyButton();
    }
    /****/
    @Override protected void paintThumb(final Graphics g,final JComponent pComponent,final Rectangle pRct)
    {
        pComponent.setBackground(Color.black);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color vC1 =  new  Color(255, 205, 19);
        Color vC2 = new  Color(255, 43, 255);
        g2.setPaint( new  GradientPaint(pRct.x, pRct.y, vC1, pRct.width, pRct.height, vC2));
        g2.fillRoundRect(pRct.x, pRct.y + 8, pRct.width, pRct.height - 16, 10, 10);
        g2.dispose();
    }
    /****/
    private JButton getEmptyButton()
    {
        JButton vJButton =  new  JButton();
        vJButton.setBorder(BorderFactory.createEmptyBorder());
        return vJButton;
    }
}
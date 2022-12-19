import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/****/
public class Mouse implements MouseListener, MouseMotionListener
{
    private Tool aTool;
    public Mouse()
    {
        this.aTool = new Pen();
    }
    /****/
    public void setTool(final Tool pTool){this.aTool = pTool; }
    /****/
    @Override public void mousePressed(MouseEvent e)
    {
        if(e.getSource() instanceof DrawArea vDA)
        {
            vDA.setCursor(this.aTool.getCursor(e.getButton()));
            this.aTool.setPressedButton(e.getButton());
            this.aTool.setPressedPoint(e.getPoint());
            this.aTool.edit(vDA , e);
        }
    }
    /****/
    @Override public void mouseReleased(MouseEvent e)
    {
        if(e.getSource() instanceof DrawArea vDA)
        {
            vDA.setCursor(this.aTool.getCursor(0));
            this.aTool.setPressedButton(0);
            this.aTool.setPressedPoint(null);
            this.aTool.releasedEdit(vDA);
        }
    }
    @Override public void mouseClicked(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseMoved(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}
    @Override public void mouseDragged(MouseEvent e)
    {
        if(e.getSource() instanceof DrawArea vDA)
        {
            this.aTool.edit(vDA , e);
        }
    }
}

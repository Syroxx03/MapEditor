import java.awt.event.MouseEvent;

public class Pen extends Tool
{
    /****/
    public Pen()
    {

    }
    /****/
    @Override void rightEdit(DrawArea pDA, MouseEvent e)
    {
        if(aTile != null)
            pDA.setTile(aTile , e.getPoint());
    }
    /****/
    @Override void leftEdit(DrawArea pDA, MouseEvent e)
    {
        if(aTile != null)
            pDA.setTile(aTile , e.getPoint());

    }
    /****/
    @Override void releasedEdit(DrawArea pDA)
    {

    }
}
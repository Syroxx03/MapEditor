import java.awt.event.MouseEvent;

public class Gum extends Tool
{
    /****/
    public Gum()
    {
        this.aDefaultC = this.createCursor(CursorFolder+"/Gum.png");
    }
    /****/
    @Override void rightEdit(DrawArea pDA, MouseEvent e)
    {
        pDA.setTile(null , e.getPoint());
    }
    /****/
    @Override void leftEdit(DrawArea pDA, MouseEvent e)
    {
        pDA.setTile(null , e.getPoint());
    }
    /****/
    @Override void releasedEdit(DrawArea pDA)
    {

    }
}

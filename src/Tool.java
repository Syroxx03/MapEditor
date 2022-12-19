import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
/****/
public abstract class Tool
{
    public final static String CursorFolder = "Images/Cursors";
    protected static Tile aTile = null;
    protected static Point aPressedPoint = null;
    protected static int aPressedButton = 0;
    protected Cursor aDefaultC, aRightC, aLeftC, aMiddleC;
    /****/
    public Tool()
    {
        this.aDefaultC = this.createCursor(CursorFolder+"/Released.png");
        this.aRightC = this.createCursor(CursorFolder+"/Pressed.png");
        this.aMiddleC = this.createCursor(CursorFolder+"/Grab.png");
        this.aLeftC = this.aRightC;
    }
    /****/
    public void setTile(final Tile pTile){aTile = pTile;}
    /****/
    public void setPressedPoint(final Point pPoint){aPressedPoint = pPoint;}
    public void setPressedButton(final int pButton){aPressedButton = pButton;}
    /****/
    protected Cursor getCursor(final int pButton)
    {
        return switch (pButton)
                {
                    case 1 -> this.aRightC;
                    case 2 -> this.aMiddleC;
                    case 3 -> this.aLeftC;
                    default -> this.aDefaultC;
                };
    }
    /****/
    protected void edit(final DrawArea pDA, final MouseEvent e)
    {
        switch (aPressedButton)
        {
            case 0 -> this.releasedEdit(pDA);
            case 1 -> this.rightEdit(pDA, e);
            case 2 -> this.middleEdit(pDA, e);
            case 3 -> this.leftEdit(pDA, e);
        }
    }
    /****/
    abstract void rightEdit(final DrawArea pDA, final MouseEvent e);
    protected void middleEdit(final DrawArea pDA, final MouseEvent e)
    {
        pDA.shiftX(e.getPoint().x-aPressedPoint.x);
        pDA.shiftY(e.getPoint().y-aPressedPoint.y);
        aPressedPoint = e.getPoint();
    }
    abstract void leftEdit(final DrawArea pDA, final MouseEvent e);
    abstract void releasedEdit(final DrawArea pDA);

    /****/
    protected Cursor createCursor(final String pPath)
    {
        try
        {
            Image vImg = ImageIO.read(new File(pPath));
            String vName = pPath.substring(pPath.lastIndexOf("/") + 1 , pPath.lastIndexOf("."));
            return Toolkit.getDefaultToolkit().createCustomCursor(vImg,new Point(10,13),vName);
        }
        catch(IOException | IllegalArgumentException e)
        {
            System.out.println("Image not found: "+ pPath);
            return Cursor.getDefaultCursor();
        }
    }
}

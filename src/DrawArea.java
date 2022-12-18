import javax.swing.*;
import java.awt.*;
/****/
public class DrawArea extends JComponent
{
    public static final int TileDim = 16;
    private final Tile[][] aTiles;
    private final Point aOrigin;//Pos of the point (0,0) on the DrawPanel
    /****/
    public DrawArea()
    {
        this.aTiles = new Tile[80][50];// ClmnNbr , RowNbr
        this.aOrigin = new Point(0,0);

        this.setLayout(new OverlayLayout(this));
        this.setForeground(new Color(105,105,120));
        this.setBackground(Color.black);
    }
    /****/
    @Override public void paint(Graphics g)
    {
        this.drawTiles(g);
        this.drawGrid(g);
    }
    /****/
    public void setTile(final Tile pTile, final Point pPos)
    {
        int vClmn = (int)((pPos.getX() - this.aOrigin.x) / TileDim) ;
        int vRow = (int)((pPos.getY() - this.aOrigin.y) / TileDim) ;
        if(vClmn >= 0 && vRow >= 0 &&  vClmn < this.aTiles.length && vRow < this.aTiles[0].length)
            this.aTiles[vClmn][vRow] = pTile;
    }
    /****/
    public void shiftY(final int pShift)
    {
        if(pShift > 0)
            this.aOrigin.y = Math.min(50 , this.aOrigin.y + pShift);
        else if (pShift < 0)
            this.aOrigin.y = Math.max((this.getHeight() - 50) -this.aTiles[0].length * TileDim, this.aOrigin.y + pShift);
    }
    /****/
    public void shiftX(final int pShift)
    {
        if(pShift > 0)
            this.aOrigin.x = Math.min(50 , this.aOrigin.x + pShift);
        else if (pShift < 0)
            this.aOrigin.x = Math.max((this.getWidth() - 50) -this.aTiles.length * TileDim, this.aOrigin.x + pShift);
    }
    /****/
    private void drawTiles(final Graphics g)
    {
        for(int vRow = 0; vRow < this.aTiles[0].length; vRow++)  {
            for (int vClmn = 0; vClmn < this.aTiles.length; vClmn++) {
                if (this.aTiles[vClmn][vRow] != null){

                    int vX = this.aOrigin.x + vClmn * TileDim;
                    int vY = this.aOrigin.y + vRow * TileDim;
                    g.drawImage(this.aTiles[vClmn][vRow].getImage(), vX, vY, this);
                }
            }
        }
    }
    /****/
    private void drawGrid(final Graphics g)
    {
        int vLastX = this.aOrigin.x + this.aTiles.length * TileDim;
        int vLastY = this.aOrigin.y + this.aTiles[0].length * TileDim;
        for(int vX = this.aOrigin.x; vX <= vLastX ; vX += TileDim)
            g.drawLine(vX ,this.aOrigin.y ,vX , vLastY);
        for(int vY = this.aOrigin.y; vY <= vLastY ; vY += TileDim)
            g.drawLine(this.aOrigin.x, vY, vLastX, vY);
    }
}

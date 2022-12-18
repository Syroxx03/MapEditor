import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/****/
class Tile
{
    private final String aImagePath;
    private final BufferedImage aImage;
    /****/
    public Tile(final String pImagePath) throws IOException
    {
        this.aImagePath = pImagePath;
        this.aImage = ImageIO.read(new File(pImagePath));
    }
    /** Accesseurs **/
    public String getImagePath() {return this.aImagePath;}
    public Image getImage() {return this.aImage;}
}


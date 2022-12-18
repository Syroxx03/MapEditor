import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.io.File;
import javax.swing.*;
import java.awt.*;
/****/
public class TilePanel extends JScrollPane implements ActionListener
{
    public static final String TileFolder = "Images/Tiles";
    public static final String IconFolder = "Images/Icons";
    public static final int ColumnNbr = 4;
    /* key = Folder path, value = JButton array of all files inside*/
    private final HashMap<JButton, ArrayList<JButton>> aFoldersMap;
    private final ArrayList<JButton> aImagesB;
    private final JPanel aPanel;
    /****/
    public TilePanel()
    {
        super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
        this.getVerticalScrollBar().setUI(new CstmScrollBar());
        this.getVerticalScrollBar().setUnitIncrement(16);
        this.setBorder(null);

        this.aPanel = new JPanel();
        this.aPanel.setBackground(new Color(20,20,30));
        this.setViewportView(this.aPanel);

        this.aImagesB = new ArrayList<>();
        this.aFoldersMap = new HashMap<>();
        JButton vFolderB = this.createButton(TileFolder);
        this.fillBMap(vFolderB , null);
        this.displayChildsOf(vFolderB);
    }
    /****/
    public void addActionListener(final ActionListener pAL)
    {
        for(JButton vButton: this.aImagesB)
            vButton.addActionListener(pAL);
    }
    /****/
    @Override public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() instanceof JButton vB)
            this.displayChildsOf(vB);
    }
    /**(Recursive) Create JButton with the folder (Key) and JButton List of
       every files inside + of the folder where is the folder(backButton) (Value)**/
    private void fillBMap(final JButton pFolderB, final JButton pFolderOfFolderB)
    {
        String vFolderPath = pFolderB.getActionCommand();
        File vFolder = new File(vFolderPath);
        String[] vFileNames = vFolder.list();
        if(vFileNames != null && vFileNames.length > 0 && !this.aFoldersMap.containsKey(pFolderB))
        {
            ArrayList<JButton> vFilesB = new ArrayList<>();
            this.aFoldersMap.put(pFolderB, vFilesB);
            if(pFolderOfFolderB != null)
                vFilesB.add(pFolderOfFolderB);
            Arrays.sort(vFileNames);
            for (String vFileName : vFileNames)
                vFilesB.add(this.createButton(vFolderPath + "/" + vFileName));
            //Make the same for each files
            for (final JButton vFileB : vFilesB)
                    this.fillBMap(vFileB , pFolderB);
        }
    }
    /****/
    private void displayChildsOf(JButton pFolderB)
    {
        if(this.aFoldersMap.containsKey(pFolderB))
        {
            this.aPanel.removeAll();
            ArrayList<JButton> vFilesB = this.aFoldersMap.get(pFolderB);
            int vRowNbr = (int)Math.ceil(((double)vFilesB.size()) / ColumnNbr);
            this.aPanel.setLayout(new GridLayout(vRowNbr, ColumnNbr));
            for(final JButton vFileB : vFilesB)
                this.aPanel.add(vFileB);
            this.setButtonsImageSize();
            this.revalidate();
        }
    }
    /****/
    public void setButtonsImageSize()
    {
        if(this.aPanel.getWidth() <= 0){return;}
        for(Component vC : this.aPanel.getComponents())
            if (vC instanceof JButton vB)
            {
                Image vImg = ((ImageIcon)vB.getIcon()).getImage();
                if(vImg != null)
                {
                    int vButtonW = this.aPanel.getWidth()/ColumnNbr;
                    int vButtonH = Math.min(vImg.getHeight(null) * vButtonW / vImg.getWidth(null),4*vButtonW);
                    vB.setIcon(new ImageIcon(vImg.getScaledInstance(vButtonW, vButtonH, Image.SCALE_DEFAULT)));
                }
            }
    }
    /****/
    private JButton createButton(final String pFilePath)
    {
        JButton vButton = new JButton();
        vButton.setActionCommand(pFilePath);
        vButton.setContentAreaFilled(false);
        vButton.setFocusable(false);
        vButton.setBorder(null);
        this.addContent(vButton , pFilePath);
        return vButton;
    }
    /****/
    private void addContent(final JButton pB, final String pPath)
    {
        if(pPath.contains(".png") || pPath.contains(".jpg") || pPath.contains(".gif"))
        {
            pB.setIcon(new ImageIcon(pPath));
            this.aImagesB.add(pB);
        }
        else
        {
            pB.addActionListener(this);
            pB.setForeground(new Color(125,125,150));
            pB.setHorizontalTextPosition(JButton.CENTER);
            pB.setVerticalTextPosition(JButton.CENTER);
            pB.setText(pPath.substring(pPath.lastIndexOf("/")+1));
            if(new File(IconFolder +"/folder.png").isFile())
                pB.setIcon(new ImageIcon(IconFolder + "/folder.png"));
        }
    }
}
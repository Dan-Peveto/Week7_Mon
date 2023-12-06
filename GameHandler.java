import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;

public class GameHandler extends JFrame{
    // fields

    public JPanel panel; // create panel
    private final int COLUMNS = 2, ROWS = 2; // create grid size
    private BufferedImage imageSource; // image var
    private BufferedImage imageResized; //resized var
    private ArrayList<FancyButton> buttonList = new ArrayList<FancyButton>(); // create button array 
    private ArrayList<String> pathList = new ArrayList<String>();
    private ArrayList<ImageIcon> imageList = new ArrayList<ImageIcon>();

    // methods

    //to do 
    
    private ImageIcon transformImage(String image)
    {
        ImageIcon imageIcon = new ImageIcon(image); //load the image to an imageIcon
        Image img = imageIcon.getImage(); // transform image to Image 
        Image newimg = img.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH); // scale it withimage smooth
        ImageIcon transformedImage = new ImageIcon(newimg);
        return transformedImage;
    }
    // ctor
    public GameHandler() {
        super("Puzzle Game");
        panel = new JPanel(); // create the panel 
        panel.setLayout(new GridLayout(COLUMNS, ROWS)); //add columns and rows tow the panel via layout
        add(panel); // add panel
        // add images to filePath
        pathList.add("Assets/Liverpool.jpg");
        pathList.add("Assets/ManCity.png");
        pathList.add("Assets/ManUnit.jpg");
        pathList.add("Assets/Arsenal.png");

        // convert image size and add to list
        for(String path : pathList)
        {
            imageList.add(transformImage(path));
        }
        

        //add buttons to the list
        for(int i=0; i<COLUMNS*ROWS; i++)
        {
            // Formula for adding row and col 
            int row = i / COLUMNS;
            int col = i % COLUMNS; 

            FancyButton btn = new FancyButton(); 
            panel.add(btn);
            btn.setIcon(imageList.get(i));
            buttonList.add(btn);
        }

        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(1500, 1500);
        setLocation(700, 0);
    }
}

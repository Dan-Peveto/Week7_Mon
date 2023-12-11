// Dan and Trevor
// required imports 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

public class GameHandler extends JFrame
{
    // fields

    public JPanel panel; // create panel
    private final int COLUMNS = 2, ROWS = 2; // create grid size
    private ArrayList<FancyButton> buttonList = new ArrayList<FancyButton>(); // create button array 
    private ArrayList<String> pathList = new ArrayList<String>(); // create array to hold paths
    private ArrayList<ImageIcon> imageList = new ArrayList<ImageIcon>(); // create array to hold images
    
    // methods

    // Method for button clicked
    private void myClickEventHandler(ActionEvent e)
    {
        FancyButton btnClicked = (FancyButton)e.getSource();
        int i = buttonList.indexOf(btnClicked);
        PuzzleGame startGame = new PuzzleGame(pathList.get(i), 3, 4);
        setVisible(false);
    }

    // Method to resize image
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
            FancyButton btn = new FancyButton(); 
            panel.add(btn);
            btn.setIcon(imageList.get(i));
            buttonList.add(btn);
            btn.addActionListener(e -> myClickEventHandler(e));
        }
        
        
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(1000, 1000);
        setLocation(700, 0);
    }
}

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;

public class GameHandler extends JFrame{
    // fields
    private final int WIDTH = 5000; //set width
    private final int HEIGHT = 5000; //set height
    public JPanel panel; // create panel
    private final int COLUMNS = 2, ROWS = 2; // create grid size
    private BufferedImage imageSource; // image var
    private BufferedImage imageResized; //resized var
    private ArrayList<FancyButton> buttonList = new ArrayList<FancyButton>(); // create button array 
    private ImageIcon icon1 = new ImageIcon("Liverpool.jpg");
    private ImageIcon icon2 = new ImageIcon("ManUnit.jpg");
    private ImageIcon icon3 = new ImageIcon("ManCity.png");
    private ImageIcon icon4 = new ImageIcon("Arsenal.png");
    private ArrayList<ImageIcon> imageList = new ArrayList<ImageIcon>();

    // methods
    
    // ctor
    public GameHandler() {
        super("Puzzle Game");
        panel = new JPanel(); // create the panel 
        panel.setLayout(new GridLayout(COLUMNS, ROWS)); //add columns and rows tow the panel via layout
        add(panel);
        imageList.add(icon1);
        imageList.add(icon2);
        imageList.add(icon3);
        imageList.add(icon4);
        
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

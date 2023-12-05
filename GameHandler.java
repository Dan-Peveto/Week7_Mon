import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

public class GameHandler extends JFrame{
    // fields
    private final int WIDTH = 1000; //set width
    private final int HEIGHT = 1000; //set height
    public JPanel panel; // create panel
    private final int COLUMNS = 2, ROWS = 2; // create grid size
    private BufferedImage imageSource; // image var
    private BufferedImage imageResized; //resized var
    private ArrayList<FancyButton> buttonList = new ArrayList<FancyButton>(); // create button array 

    // methods
    
    // ctor
    public GameHandler() {
        super("Puzzle Game");
        panel = new JPanel(); // create the panel 
        panel.setLayout(new GridLayout(COLUMNS, ROWS)); //add columns and rows tow the panel via layout
        add(panel);

        // Set up forr GUI interface
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(1000, 1000);
        setLocation(700, 0);
    }
}

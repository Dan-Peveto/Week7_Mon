// Imports
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// Puzzle game class

public class PuzzleGame extends JFrame{

    private JPanel panel; // create panle
    private JComboBox userColNumField; // create text field
    private JComboBox userRowNumField; // create text field
    private int numRows; // for user input
    private int numCols; // for user input
    private final int  width = 1000; // set width to final (can't be changed)
    private int height = 1000; // set height will change in future
    private final int COLUMNS = 3, ROWS = 4; // create COLUMNS and ROWS to-do make these dynamic 
    private ArrayList<FancyButton> buttonList; // create button list
    private BufferedImage imageSource; // create buffered image var
    private BufferedImage imageResized; // create resizble image var
    private ArrayList<FancyButton> buttonSolution = new ArrayList<FancyButton>();  // create array list to hold button solution
    private String filePath; // create file path var to allow for diff images
    private JButton resetBtn; // create reset button
    private int count = 0; // create count to track how many turns it took

    // puzzle game constructor
    public PuzzleGame(String filePath) // input filePath from GameHandler
    {
        super("Puzzle Game"); // Title
        this.filePath = filePath; 
        panel = new JPanel(); // initiate the panel
        panel.setLayout(new GridLayout(ROWS, COLUMNS)); // have to manually import java.awt.*; set panel to predefined size
        add(panel); // add panel
        try { // load image and run game
            imageSource = loadImage(); 
            int sourceWidth = imageSource.getWidth(); // get image width
            int sourceHeight = imageSource.getHeight(); // get image height
            double aspectRatio = (double)sourceHeight / sourceWidth;
            height = (int)(aspectRatio * width); //reset height var
            imageResized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); // resize image
            Graphics2D graphics = imageResized.createGraphics(); // create graphics from image
            graphics.drawImage(imageSource, 0, 0, width, height, null);
            graphics.dispose();
            JPanel southJPanel = new JPanel(); // new JPanel
            JPanel northJPanel = new JPanel();
            northJPanel.setSize(50, 100);
            add(southJPanel, BorderLayout.SOUTH); // add it to the southern portion of gui
            resetBtn = new JButton("Reset Game"); // reset the game
            resetBtn.addActionListener(e -> resetGame(e)); // listener event
            southJPanel.add(resetBtn); // add reset button to panel

            userColNumField = new JComboBox<String>();
            userRowNumField = new JComboBox<Integer>();
            
            for(int count = 2; count <= 10; count += 2) 
            {
                userColNumField.addItem("Choose Cols " + String.valueOf(count));
                userRowNumField.addItem("Choose Row " + String.valueOf(count));
            }
            
            northJPanel.add(userColNumField);
            northJPanel.add(userRowNumField);
            JButton resetbtn = new JButton("Reset Size");
            northJPanel.add(resetbtn);
            add(northJPanel, BorderLayout.NORTH);

            
        } catch(IOException e) {
            JOptionPane.showMessageDialog(rootPane, "Error loading the image"); // error message for image 
        }
        buttonList = new ArrayList<FancyButton>(); // initiate button list
        for(int i = 0; i < COLUMNS * ROWS; i++) // iterate over col and rows
        {
            // formula for columns and rows
            int row = i / COLUMNS; 
            int column = i % COLUMNS;
            // resize image
            Image imageslice = createImage(
                new FilteredImageSource(imageResized.getSource(), 
                new CropImageFilter(column * width/COLUMNS,row *height/ROWS, 
                width/COLUMNS, height/ROWS)));
            
            
            FancyButton btn = new FancyButton(); // create button
            btn.addActionListener(e -> myClickEventHandler(e)); // make button work

            if(i == COLUMNS * ROWS -1) // the last button
            {
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(false);
            } else 
            {
                btn.setIcon(new ImageIcon(imageslice));
            }
            buttonSolution.add(btn);
            buttonList.add(btn);
        }
        Collections.shuffle(buttonList); // shuffle the images
        for(var button : buttonList) {
            panel.add(button);
        }
        // Gui initial set up 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(width, height);
        setLocation(700, 0);
    }

    private BufferedImage loadImage() throws IOException // load the image
    {
        return ImageIO.read(new File(filePath));
    }
    private void myClickEventHandler(ActionEvent e) // method to move the buttons
    {
        FancyButton btnClicked = (FancyButton)e.getSource();
        count+=1;
        int i = buttonList.indexOf(btnClicked);
        int column = i % COLUMNS;
        int row = i / COLUMNS;

        int emptyIndex = -1;
        for(int j=0; j<buttonList.size(); j++) 
        {
            if(buttonList.get(j).getIcon() == null)
            {
                emptyIndex = j;
            }
        }
        int emptyColumn = emptyIndex % COLUMNS;
        int emptyRow = emptyIndex / COLUMNS;

        if(emptyRow == row && Math.abs(column - emptyColumn) == 1 || emptyColumn == column && Math.abs(row - emptyRow) == 1) 
        {
            Collections.swap(buttonList, i, emptyIndex);
            updateButtons();
        } 
        if(buttonSolution == buttonList)
        {
            JOptionPane.showMessageDialog(btnClicked, "Great Success! It only took you " + count + " moves to complete this game");
        }
    }
    public void updateButtons() { // method to update the buttons
        panel.removeAll();
        for(var btn : buttonList) {
            panel.add(btn);
        }
        panel.validate();
    }
    public void resetGame(ActionEvent e) // method to reset the game
    {
        GameHandler gameHanlder = new GameHandler();
        setVisible(false);
    } 
}
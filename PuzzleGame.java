
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

public class PuzzleGame extends JFrame{


    private final int  width = 1000;
    private int height = 1000;
    public JPanel panel;
    private final int COLUMNS = 3, ROWS = 4;
    private ArrayList<FancyButton> buttonList;
    private BufferedImage imageSource;
    private BufferedImage imageResized;
    private ArrayList<FancyButton> buttonSolution = new ArrayList<FancyButton>();
    private String filePath;


    public PuzzleGame(String filePath) {
        super("Puzzle Game");
        this.filePath = filePath;
        panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS, COLUMNS)); // have to manually import java.awt.*; 
        add(panel);
        try {
            imageSource = loadImage();
            int sourceWidth = imageSource.getWidth();
            int sourceHeight = imageSource.getHeight();
            double aspectRatio = (double)sourceHeight / sourceWidth;
            height = (int)(aspectRatio * width);
            imageResized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = imageResized.createGraphics();
            graphics.drawImage(imageSource, 0, 0, width, height, null);
            graphics.dispose();

        } catch(IOException e) {
            JOptionPane.showMessageDialog(rootPane, "Error loading the image");
        }
        buttonList = new ArrayList<FancyButton>();
        for(int i = 0; i < COLUMNS * ROWS; i++) {
            
            int row = i / COLUMNS; 
            int column = i % COLUMNS;

            Image imageslice = createImage(
                new FilteredImageSource(imageResized.getSource(), 
                new CropImageFilter(column * width/COLUMNS,row *height/ROWS, 
                width/COLUMNS, height/ROWS)));
            
            
            FancyButton btn = new FancyButton();
            btn.addActionListener(e -> myClickEventHandler(e));

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
        Collections.shuffle(buttonList);
        for(var button : buttonList) {
            panel.add(button);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(width, height);
        setLocation(700, 0);
    }

    private BufferedImage loadImage() throws IOException {
        return ImageIO.read(new File(filePath));
    }
    private void myClickEventHandler(ActionEvent e){
        FancyButton btnClicked = (FancyButton)e.getSource();
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
            JOptionPane.showMessageDialog(btnClicked, "Oh my god you spent how long doing his stupid puzzle?");
        }
    }
    public void updateButtons() {
        panel.removeAll();
        for(var btn : buttonList) {
            panel.add(btn);
        }
        panel.validate();
    }
}
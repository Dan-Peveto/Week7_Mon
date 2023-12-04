
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PuzzleGame extends JFrame{


    private final int  width = 1000;
    private int height = 1000;
    public JPanel panel;
    private final int COLUMNS = 3, ROWS = 4;
    private ArrayList<FancyButton> buttonList;
    private BufferedImage imageSource;
    private BufferedImage imageResized;


    public PuzzleGame() {
        super("Puzzle Game");

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
            btn.setIcon(new ImageIcon(imageslice));
            buttonList.add(btn);
            panel.add(btn);
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(width, height);
        setLocation(700, 0);
    }

    private BufferedImage loadImage() throws IOException {
        return ImageIO.read(new File("Liverpool.jpg"));
    }
}
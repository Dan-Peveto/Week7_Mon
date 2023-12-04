
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PuzzleGame extends JFrame{


    private final int  width = 1000;
    private int height = 1000;
    public JPanel panel;
    private final int COLUMNS = 3, ROWS = 4;
    private ArrayList<FancyButton> buttonList;

    public PuzzleGame() {
        super("Puzzle Game");

        panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS, COLUMNS)); // have to manually import java.awt.*; 
        add(panel);
        try {
            loadImage();
        } catch(IOException e) {
            JOptionPane.showMessageDialog(rootPane, "Error loading the image");
        }
        buttonList = new ArrayList<FancyButton>();
        for(int i = 0; i < COLUMNS * ROWS; i++) {
            FancyButton btn = new FancyButton();
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
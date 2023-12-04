import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class FancyButton extends JButton{
    
    public FancyButton() {
        super();
        MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        
            }
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        };
        addMouseListener(mouseAdapter);
    }
}

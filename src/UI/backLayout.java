package UI;

import javax.swing.*;
import java.awt.*;

public class backLayout extends JPanel {
    protected ImageIcon icon ;
    public backLayout(String s) {
        super();
        icon=new ImageIcon(s);
        int width = icon.getIconWidth(), height = icon.getIconHeight();
        setSize(width, height);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, icon.getIconWidth(),
                icon.getIconHeight(), icon.getImageObserver());
    }
}

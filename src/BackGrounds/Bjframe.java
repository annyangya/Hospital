package BackGrounds;

import javax.swing.*;
import java.awt.*;

public class Bjframe extends JFrame {

    public static final int width=600;
    public static final int height=600;
    private JLabel label;

    public Bjframe(){
        setSize(width,height);
        Bjpanel p1=new Bjpanel("login.jpg");
        Container contentpain=getContentPane();
        contentpain.add(p1);

        label=new JLabel("lalla");


        p1.setOpaque(true);
    }

    public static void main(String[] args) {
        Bjframe bjframe=new Bjframe();
        bjframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bjframe.setVisible(true);
    }
}

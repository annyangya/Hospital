package BackGrounds;

//import javafx.scene.image.Image;

import javax.swing.*;
import java.awt.*;


public class Bjpanel extends JPanel {
    Image im;

    public Bjpanel(){

    }
    public Bjpanel(String s){
        im=Toolkit.getDefaultToolkit().getImage(s);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width=im.getWidth(this);
        int height=im.getHeight(this);//定义图片宽高
        int Fwidth=getWidth();
        int Fheight=getHeight();//定义窗体宽高
        int x=(Fwidth-width)/2;
        int y=(Fheight-height)/2;
        g.drawImage(im,x,y,null);
    }
}

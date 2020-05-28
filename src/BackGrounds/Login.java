package BackGrounds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JButton loginButton,exitButton;
    private JLabel userNameLabel,passwordLabel;
    private JTextField userNameText;
    private JPasswordField passwordText;

    private static JPanel loginPanel;

    private Toolkit toolKit=null;
    private Dimension screenSize=null;

    public  Login(){
        Init();
    }

    public void Init(){
        //获取当前窗口，并且获取屏幕的尺寸
        toolKit=Toolkit.getDefaultToolkit();
        screenSize=toolKit.getScreenSize();
        //加载封装了图片的Panel子类，并添加到当前JFrame容器里面
        loginPanel=new LoginPanel();
        loginPanel.setLayout(null);
        add(loginPanel);
        //将整个图框居中
        setBounds(screenSize.width/2-loginPanel.getWidth()/2
                ,screenSize.height/2-loginPanel.getHeight()/2
                ,loginPanel.getWidth(),loginPanel.getHeight());

        this.setIconImage(new ImageIcon("login.jpg").getImage());
        //向loginPanel容器加入两个Jlabel,两个按钮，一个文本框，一个密码框
        userNameLabel=new JLabel("用户名");
        userNameLabel.setBounds(100, 120, 200, 18);
        loginPanel.add(userNameLabel);

        userNameText=new JTextField();
        userNameText.setBounds(150, 120, 150, 18);
        loginPanel.add(userNameText);

        passwordLabel=new JLabel("密码");
        passwordLabel.setBounds(100, 180, 200, 18);
        loginPanel.add(passwordLabel);

        passwordText=new JPasswordField();
        passwordText.setBounds(150, 180, 150, 18);
        loginPanel.add(passwordText);

        loginButton=new JButton("登录");
        loginButton.setBounds(150, 230, 60, 18);
        loginPanel.add(loginButton);

        exitButton=new JButton("退出");
        exitButton.setBounds(230, 230, 60, 18);
        loginPanel.add(exitButton);

        this.setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }


    public static void main(String [] args){
        Login in=new Login();
    }
}

class LoginPanel extends JPanel {
    protected ImageIcon icon = new ImageIcon("resource/login.jpg");
    public int width = icon.getIconWidth(), height = icon.getIconHeight();
    public LoginPanel() {
        super();
        setSize(width, height);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, icon.getIconWidth(),
                icon.getIconHeight(), icon.getImageObserver());
    }
}

package UI;


import JdbcConnect.jdbcUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends JFrame implements ActionListener {
    JFrame frame=new JFrame("用户登录");

    private JLabel userNameLabel,passwordLabel;
    private JTextField userNameText,passwordText;

    private backLayout loginPanel;
    private JButton loginButton,exitButton;
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;


    public login(){
        setResizable(false);
        Font font=new Font("黑体",Font.BOLD,15);
        setLayout(null);
        setBounds(300,400,600,600);
        loginPanel=new backLayout("login.jpg");
        loginPanel.setLayout(null);
        add(loginPanel);
        userNameLabel=new JLabel("用户名:");
        userNameLabel.setBounds(200, 350, 80, 20);
        loginPanel.add(userNameLabel);

        userNameText=new JTextField();
        userNameText.setBounds(260, 350, 150, 20);
        loginPanel.add(userNameText);

        passwordLabel=new JLabel("密码:");
        passwordLabel.setBounds(200, 400, 80, 20);
        loginPanel.add(passwordLabel);

        passwordText=new JPasswordField();
        passwordText.setBounds(260, 400, 150, 20);
        loginPanel.add(passwordText);

        loginButton=new JButton("登录");
        loginButton.setBounds(340, 450, 60, 18);//340, 450, 60, 18
        loginPanel.add(loginButton);

        exitButton=new JButton("退出");
        exitButton.setBounds(270, 450, 60, 18);
        loginPanel.add(exitButton);

        userNameLabel.setFont(font);
        passwordLabel.setFont(font);
        loginButton.setFont(font);
        exitButton.setFont(font);

        loginButton.addActionListener(this);
        exitButton.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exitButton){
            setVisible(false);
        }else if(e.getSource()==loginButton){
            String nameText=userNameText.getText().toString().trim();
            String password=passwordText.getText().toString().trim();
            String sql="select * from user WHERE username=? and password=?";
            if(!nameText.isEmpty()&&!password.isEmpty()){
                connection= jdbcUtils.getConn();
                try {
                    preparedStatement=connection.prepareStatement(sql);
                    preparedStatement.setString(1,nameText);
                    preparedStatement.setString(2,password);
                    resultSet=preparedStatement.executeQuery();
                    if(resultSet.next()){
                        setVisible(false);
                        new ManageAll();
                    }else{
                        JOptionPane.showMessageDialog(login.this,"用户名或密码错误！");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(login.this,"输入框不能为空！");
            }
        }
    }
}

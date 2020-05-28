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

public class passwordManage extends JFrame implements ActionListener {
    JFrame frame=new JFrame("用户密码管理");

    private JLabel idLabel,oldpassLabel,newpassLabel,newpassaginLabel;
    private JTextField idText,oldpassText;
    private JButton btn_confirm,btn_back;
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    private JPasswordField newpassText,newpassTextaginText;



    public passwordManage(){
        setResizable(false);
        setSize(300, 300);
        setLayout(null);
        setLocation(300, 300);
        Font font = new Font("黑体", Font.BOLD, 15);
        idLabel=new JLabel("用户id:");
        idLabel.setFont(font);
        idLabel.setBounds(75,50,80,20);
        idText=new JTextField(10);
        idText.setBounds(125,50,80,20);
        oldpassLabel=new JLabel("旧密码:");
        oldpassLabel.setFont(font);
        oldpassLabel.setBounds(75,90,80,20);
        add(oldpassLabel);
        oldpassText=new JTextField(10);
        oldpassText.setBounds(125,90,80,20);
        add(oldpassLabel);add(oldpassText);
        newpassLabel=new JLabel("新密码:");
        newpassLabel.setFont(font);
        newpassLabel.setBounds(75,130,80,20);
        add(oldpassLabel);add(newpassLabel);
        newpassText=new JPasswordField(10);
        newpassText.setBounds(125,130,80,20);
        add(oldpassLabel);add(newpassText);
        newpassaginLabel=new JLabel("请再次输入新密码:");
        newpassaginLabel.setFont(font);
        newpassaginLabel.setBounds(0,170,130,20);
        add(oldpassLabel);add(newpassaginLabel);
        newpassTextaginText=new JPasswordField(10);
        newpassTextaginText.setBounds(125,170,80,20);
        add(oldpassLabel);add(newpassTextaginText);

        btn_back=new JButton("返回");
        btn_back.setFont(font);
        btn_back.setBounds(50,220,50,20);
        add(oldpassLabel);add(btn_back);

        btn_confirm=new JButton("确认");
        btn_confirm.setBounds(190,220,50,20);
        btn_confirm.setFont(font);
        add(oldpassLabel);add(btn_confirm);

        btn_back.addActionListener(this);
        btn_confirm.addActionListener(this);
        oldpassText.addActionListener(this);
        newpassTextaginText.addActionListener(this);


        add(oldpassLabel);add(idLabel);
        add(oldpassLabel);add(idText);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_back){
            setVisible(false);
        }else if(e.getSource()==oldpassText){
            String id=idText.getText().toString().trim();
            String oldpass=oldpassText.getText().toString().trim();
            if(checkExist(id,oldpass)){
                JOptionPane.showMessageDialog(passwordManage.this,"匹配成功！请输入新密码！");
            }else{
                JOptionPane.showMessageDialog(passwordManage.this,"输入的用户id和密码不匹配！");
            }
        }else if(e.getSource()==newpassTextaginText){
            String newpass=newpassText.getText().toString().trim();
            String newpass2=newpassTextaginText.getText().toString().trim();
            if(newpass.equals(newpass2)){
                JOptionPane.showMessageDialog(passwordManage.this,"两次新密码输入一致！");
            }else{
                JOptionPane.showMessageDialog(passwordManage.this,"两次新密码输入不一致！");
            }
        }
        else if(e.getSource()==btn_confirm){
            String id=idText.getText().toString().trim();
            String oldpass=oldpassText.getText().toString().trim();
            String newpass=newpassText.getText().toString().trim();
            String newpass2=newpassTextaginText.getText().toString().trim();
            if(!id.isEmpty()&&!oldpass.isEmpty()&&!newpass.isEmpty()&&!newpass2.isEmpty()){
                modifypass(id,newpass2);
            }else{
                JOptionPane.showMessageDialog(passwordManage.this,"输入框不能为空！");
            }

        }
    }


    public boolean checkExist(String id,String oldpass){
        connection= jdbcUtils.getConn();
        String sql="SELECT * from user where id=? and password=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,oldpass);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("success!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    public void modifypass(String id,String newpass){
        String sql="update user set password=? where id=?";
        connection=jdbcUtils.getConn();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,newpass);
            preparedStatement.setString(2,id);
            int result=preparedStatement.executeUpdate();
            if(result>0){
                JOptionPane.showMessageDialog(passwordManage.this,"密码修改成功！");
                idText.setText("");
                oldpassText.setText("");
                newpassText.setText("");
                newpassTextaginText.setText("");
                setVisible(false);
            }else{
                JOptionPane.showMessageDialog(passwordManage.this,"密码修改失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new passwordManage();
    }
}

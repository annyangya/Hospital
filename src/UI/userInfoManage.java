package UI;

import JdbcConnect.jdbcUtils;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userInfoManage extends JFrame implements ActionListener {

    JFrame frame=new JFrame("用户信息管理");
    JTable usertable;
    private JButton btn_modify,btn_delete,btn_add,btn_back;
    private JLabel namelabel,idLabel,infolabel,passLabel;
    private JTextField nameText,idText,passwordText;
    private Object[][] info;
    private String[] title={"id","username","password"};
    private JScrollPane scrollPane;
    private int size=0;
    backLayout backLayout=new backLayout("a.jpg");


    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;

    public userInfoManage(){
        setResizable(false);
        backLayout.setLayout(null);
        add(backLayout);
        scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 80, 550, 250);
        setSize(600, 600);
        setLayout(null);
        setLocation(100, 50);
        Font font = new Font("黑体", Font.BOLD, 15);
        infolabel=new JLabel("用户信息管理");
        infolabel.setFont(font);
        infolabel.setBounds(250,40,150,20);
        btn_add = new JButton("添加");
        btn_delete = new JButton("删除");
        btn_modify = new JButton("修改");
        btn_add.setBounds(200, 500, 50, 20);
        btn_delete.setBounds(270, 500, 50, 20);
        btn_modify.setBounds(340, 500, 50, 20);
        btn_back = new JButton("返回");
        btn_back.setBounds(410, 500, 50, 20);
        btn_back.addActionListener(this);
        btn_back.setFont(font);
        btn_modify.setFont(font);
        btn_delete.setFont(font);
        btn_add.setFont(font);

        idLabel=new JLabel("用户id:");
        idLabel.setBounds(80,350,80,20);
        idLabel.setFont(font);
        backLayout.add(idLabel);
        idText=new JTextField(10);
        idText.setBounds(130,350,100,20);
        backLayout.add(idText);

        namelabel=new JLabel("用户名:");
        namelabel.setFont(font);
        namelabel.setBounds(300,350,80,20);
        backLayout.add(namelabel);
        nameText=new JTextField(10);
        nameText.setBounds(350,350,80,20);
        backLayout.add(nameText);

        passLabel=new JLabel("密码:");
        passLabel.setFont(font);
        passLabel.setBounds(80,400,80,20);
        backLayout.add(passLabel);
        passwordText=new JTextField(10);
        passwordText.setBounds(130,400,100,20);
        backLayout.add(passwordText);



        btn_add.addActionListener(this);
        btn_delete.addActionListener(this);
        btn_modify.addActionListener(this);

        backLayout.add(btn_add);
        backLayout.add(btn_back);
        backLayout.add(btn_delete);
        backLayout.add(btn_modify);
        backLayout.add(scrollPane);
        backLayout.add(infolabel);
        backLayout.add(namelabel);


        showView(false,false,false);

        showdata();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_add){
            add();
        }else if(e.getSource()==btn_delete){
            delete();
        }else if(e.getSource()==btn_modify){
            modify();
        }else if(e.getSource()==btn_back){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new userInfoManage();
    }

    public void showdata(){
        String sql="select * from user";
        int count=getCount(sql);
        info=new Object[count][3];
        count=0;
        try {
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                info=getArr(info,count);
                count++;
            }
            usertable=new JTable(info,title);
            scrollPane.getViewport().add(usertable);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public Object[][] getArr(Object[][] arr,int count){
        try {
            arr[count][0]=resultSet.getString("id");
            arr[count][1]=resultSet.getString("username");
            arr[count][2]=resultSet.getString("password");
            count++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public int getCount(String sql) {
        int count = 0;
        connection = jdbcUtils.getConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void showView(boolean show1,boolean show2,boolean show3){
        idLabel.setVisible(show1);
        idText.setVisible(show1);
        namelabel.setVisible(show2);
        nameText.setVisible(show2);
        passwordText.setVisible(show3);
        passLabel.setVisible(show3);
    }

    public void add(){
        showView(true,true,true);
        String name=nameText.getText().toString().trim();
        String pass=passwordText.getText().toString().trim();
        String id=idText.getText().toString().trim();
        if(!name.isEmpty()&&!pass.isEmpty()&&!id.isEmpty()){
            if(id.matches("[0-9]+")){
                String sql="insert into user values(?,?,?)";
                connection=jdbcUtils.getConn();
                try {
                    preparedStatement=connection.prepareStatement(sql);
                    preparedStatement.setString(1,id);
                    preparedStatement.setString(2,name);
                    preparedStatement.setString(3,pass);
                    int result=preparedStatement.executeUpdate();
                    if(result>0){
                        JOptionPane.showMessageDialog(userInfoManage.this,"添加成功!");
                        nameText.setText("");
                        idText.setText("");
                        passwordText.setText("");
                        refresh();
                    }else{
                        JOptionPane.showMessageDialog(userInfoManage.this,"添加失败！");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(userInfoManage.this,"用户id必须为数字！");
            }
        }else {
            JOptionPane.showMessageDialog(userInfoManage.this,"输入框不能为空！");
        }
    }

    public void refresh(){
        String sql="select * from user";
        usertable.removeAll();
        int count=getCount(sql);
        Object[][] add=new Object[count][3];
        size=count;
        count=0;
        try {
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                add=getArr(add,count);
                count++;
            }
            showdata();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        showView(true,false,false);
        String sql="delete from user where id=?";
        String id=idText.getText().toString().trim();
        if(!id.isEmpty()){
            connection=jdbcUtils.getConn();
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,id);
                int result=preparedStatement.executeUpdate();
                if(result>0){
                    JOptionPane.showMessageDialog(userInfoManage.this,"删除成功！");
                    idText.setText("");
                    refresh();
                }else{
                    JOptionPane.showMessageDialog(userInfoManage.this,"用户不存在，删除失败！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(userInfoManage.this,"输入框不能为空！");
        }
    }

    public void modify(){
        showView(true,true,false);
        String name=nameText.getText().toString().trim();
        String id=idText.getText().toString().trim();
        String sql="UPDATE user set username=? where id=?";
        if(!name.isEmpty()&&!id.isEmpty()){
            connection=jdbcUtils.getConn();
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,id);
                int result=preparedStatement.executeUpdate();
                if(result>0){
                    JOptionPane.showMessageDialog(userInfoManage.this,"修改成功！");
                    idText.setText("");
                    nameText.setText("");
                    refresh();
                }else{
                    JOptionPane.showMessageDialog(userInfoManage.this,"用户不存在，修改失败！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(userInfoManage.this,"输入框不能为空！");
        }
    }


}

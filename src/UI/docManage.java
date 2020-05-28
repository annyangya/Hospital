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

public class docManage extends JFrame implements ActionListener {
    JFrame frame=new JFrame("医生信息管理");
    JScrollPane scrollPane;
    private JLabel infolabel;
    private JTable docTabel;
    private String[] title={"Dno","Dname","Dage","Dsex","Dtel","Doffice"};
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    Object[][] info;
    private int size=0;
    String s="select * from Doctor";

    private JLabel dnoLabel,nameLabel,sexLabel,telLabel,officeLabel,ageLabel;
    private JTextField dnoText,nameText,sexText,telText,officeText,ageText;
    private JButton btn_add,btn_delete,btn_modify,btn_back,btn_query;

    backLayout backLayout=new backLayout("a.jpg");



    public docManage(){
        setResizable(false);
        backLayout.setLayout(null);
        add(backLayout);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 80, 550, 250);
        setSize(600, 600);
        setLayout(null);
        setLocation(100, 50);
        Font font = new Font("黑体", Font.BOLD, 15);
        infolabel=new JLabel("医生信息管理");
        infolabel.setBounds(250,50,100,20);
        infolabel.setFont(font);
        dnoLabel=new JLabel("Dno:");
        dnoLabel.setFont(font);
        dnoLabel.setBounds(80,350,80,20);
        backLayout.add(dnoLabel);
        dnoText=new JTextField(10);
        dnoText.setBounds(130,350,80,20);
        backLayout.add(dnoText);

        nameLabel=new JLabel("name:");
        nameLabel.setFont(font);
        nameLabel.setBounds(300,350,80,20);
        backLayout.add(nameLabel);

        nameText=new JTextField(10);
        nameText.setBounds(350,350,80,20);
        backLayout.add(nameText);

        ageLabel=new JLabel("age:");
        ageLabel.setFont(font);
        ageLabel.setBounds(80,400,80,20);
        backLayout.add(ageLabel);
        ageText=new JTextField(10);
        ageText.setBounds(130,400,80,20);
        backLayout.add(ageText);
        sexLabel=new JLabel("sex:");
        sexLabel.setFont(font);
        sexLabel.setBounds(300,400,80,20);
        sexText=new JTextField(10);
        sexText.setBounds(350,400,80,20);
        backLayout.add(sexText);
        backLayout.add(sexLabel);

        telLabel=new JLabel("tel:");
        telLabel.setFont(font);
        telLabel.setBounds(80,450,80,20);
        backLayout.add(telLabel);
        telText=new JTextField(10);
        telText.setBounds(130,450,80,20);
        backLayout.add(telText);

        officeLabel=new JLabel("office:");
        officeLabel.setFont(font);
        officeLabel.setBounds(300,450,80,20);
        backLayout.add(officeLabel);

        officeText=new JTextField(10);
        officeText.setBounds(350,450,80,20);
        backLayout.add(officeText);

        btn_add=new JButton("添加");
        btn_add.setFont(font);
        btn_add.setBounds(100,500,50,20);
        backLayout.add(btn_add);
        btn_add.addActionListener(this);

        btn_delete=new JButton("删除");
        btn_delete.addActionListener(this);
        btn_delete.setFont(font);
        btn_delete.setBounds(200,500,50,20);
        backLayout.add(btn_delete);

        btn_modify=new JButton("修改");
        btn_modify.addActionListener(this);
        btn_modify.setBounds(300,500,50,20);
        btn_modify.setFont(font);
        backLayout.add(btn_modify);

        btn_back=new JButton("返回");
        btn_back.addActionListener(this);
        btn_back.setFont(font);
        btn_back.setBounds(500,500,50,20);
        backLayout.add(btn_back);

        btn_query=new JButton("查询");
        btn_query.setBounds(400,500,50,20);
        btn_query.setFont(font);
        btn_query.addActionListener(this);
        backLayout.add(btn_query);

        backLayout.add(scrollPane);
        backLayout.add(infolabel);
        showview(false,false,false);
        setVisible(true);

        showdata();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void showdata(){
        String sql="select * from Doctor";
        int count=getCount(sql);
        info=new Object[count][6];
        size=count;
        count=0;
        try {
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                info=getInfo(info,count);
                count++;
            }
            docTabel=new JTable(info,title);
            scrollPane.getViewport().add(docTabel);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new docManage();
    }

    public int getCount(String sql){
        int count=0;
        connection= jdbcUtils.getConn();
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Object[][] getInfo(Object[][] info,int count) {
        try {
            info[count][0]=resultSet.getString("Dno");
            info[count][1]=resultSet.getString("Dname");
            info[count][2]=resultSet.getString("Dage");
            info[count][3]=resultSet.getString("Dsex");
            info[count][4]=resultSet.getString("Dtel");
            info[count][5]=resultSet.getString("Doffice");
            count++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_back){
            setVisible(false);
        }else if(e.getSource()==btn_add){
            add();
        }else if(e.getSource()==btn_delete){
            delete();
        }else if(e.getSource()==btn_modify){
            modify();
        }else if(e.getSource()==btn_query){
            query();
        }
    }

    public void add(){
        showview(true,true,true);
        String sql="insert into Doctor values(?,?,?,?,?,?)";
        String dno=dnoText.getText().toString().trim();
        String name=nameText.getText().toString().trim();
        String age=ageText.getText().toString().trim();
        String sex=sexText.getText().toString().trim();
        String tel=telText.getText().toString().trim();
        String office=officeText.getText().toString().trim();
        if(!dno.isEmpty()&&!name.isEmpty()&&!age.isEmpty()&&!sex.isEmpty()&&!tel.isEmpty()&&!office.isEmpty()){
            connection=jdbcUtils.getConn();
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,dno);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,age);
                preparedStatement.setString(4,sex);
                preparedStatement.setString(5,tel);
                preparedStatement.setString(6,office);
                int result=preparedStatement.executeUpdate();
                if(result>0){
                    JOptionPane.showMessageDialog(docManage.this,"添加成功！");
                    dnoText.setText("");
                    nameText.setText("");
                    ageText.setText("");
                    sexText.setText("");
                    telText.setText("");
                    officeText.setText("");
                    showdata();
                }else{
                    JOptionPane.showMessageDialog(docManage.this,"添加失败！");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(docManage.this,"输入框不能为空！");
        }

    }

    public void showview(boolean show1,boolean show2,boolean show3){
        dnoLabel.setVisible(show1);
        dnoText.setVisible(show1);
        nameLabel.setVisible(show3);
        nameText.setVisible(show3);
        ageLabel.setVisible(show2);
        ageText.setVisible(show2);
        sexLabel.setVisible(show3);
        sexText.setVisible(show3);
        telLabel.setVisible(show2);
        telText.setVisible(show2);
        officeLabel.setVisible(show3);
        officeText.setVisible(show3);
    }

    public void delete(){
        showview(true,false,false);
        String sql="delete from Doctor where Dno=?";
        String dno=dnoText.getText().toString().trim();
        if(!dno.isEmpty()){
            connection=jdbcUtils.getConn();
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,dno);
                int result=preparedStatement.executeUpdate();
                if(result>0){
                    JOptionPane.showMessageDialog(docManage.this,"删除成功！");
                    dnoText.setText("");
                    showdata();
                }else{
                    JOptionPane.showMessageDialog(docManage.this,"删除失败！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(docManage.this,"输入框不能为空！");
        }
    }

    public void modify(){
        showview(true,true,false);
        String sql="update Doctor set Dage=? , Dtel=? where Dno=?";
        String age=ageText.getText().toString().trim();
        String tel=telText.getText().toString().trim();
        String dno=dnoText.getText().toString().trim();
        if(!age.isEmpty()&&!tel.isEmpty()&&!dno.isEmpty()){
            connection=jdbcUtils.getConn();
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,age);
                preparedStatement.setString(2,tel);
                preparedStatement.setString(3,dno);
                int result=preparedStatement.executeUpdate();
                if(result>0){
                    JOptionPane.showMessageDialog(docManage.this,"修改成功！");
                    refresh(s);
                    dnoText.setText("");
                    ageText.setText("");
                    telText.setText("");
                }else{
                    JOptionPane.showMessageDialog(docManage.this,"修改失败！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                jdbcUtils.closeAll(connection,preparedStatement,resultSet);
            }
        }else {
            JOptionPane.showMessageDialog(docManage.this,"输入框不能为空！");
        }
    }

    public void refresh(String sql){
        //String sql="select * from Doctor";
        docTabel.removeAll();
        int count=getCount(sql);
        Object[][] add=new Object[count][6];
        size=count;
        count=0;
        try {
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                add=getInfo(add,count);
                count++;
            }
            showdata();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void query(){
        String dno=dnoText.getText().toString().trim();
        String sql="select * from Doctor where Dno='"+dno+"'";
        showview(true,false,false);
        if(!dno.isEmpty()){
            intiTable(sql);
            dnoText.setText("");
        }else{
            JOptionPane.showMessageDialog(docManage.this,"输入框不能为空！");
        }
    }


    public void intiTable(String sql) {
        int count = 0;
        connection = jdbcUtils.getConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
            size = count;
            count = 0;
            resultSet = preparedStatement.executeQuery();
            Object[][] query = new Object[size][6];
            while (resultSet.next()) {
                query[count][0]=resultSet.getString("Dno");
                query[count][1]=resultSet.getString("Dname");
                query[count][2]=resultSet.getString("Dage");
                query[count][3]=resultSet.getString("Dsex");
                query[count][4]=resultSet.getString("Dtel");
                query[count][5]=resultSet.getString("Doffice");
                count++;
            }
            for (int i = 0; i < info.length; i++) {
                for (int j = 0; j < 6; j++) {
                    docTabel.setValueAt("", i, j);
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < 6; j++) {
                    docTabel.setValueAt(query[i][j], i, j);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

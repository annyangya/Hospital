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

public class bedQuery extends JFrame implements ActionListener {
    JFrame frame=new JFrame("病房床位分配管理");
    private JTextField officeText;
    private JTable bedTable;
    private JLabel officeLabel;
    private backLayout backLayout=new backLayout("b.jpg");
    private JLabel bedinfo;
    private JScrollPane scrollPane;
    private JButton btn_querybed,btn_back;
    private Object[][] info;
    private String[] bedtitle={"Bno","Wno","Binform","Boffice"};


    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;

    public bedQuery(){
        setResizable(false);
        setBounds(100, 100, 600, 600);
        backLayout.setLayout(null);
        add(backLayout);
        Font font=new Font("黑体",Font.BOLD,18);
        bedinfo=new JLabel("空床位查询");
        bedinfo.setFont(font);
        bedinfo.setBounds(240,20,150,20);
        scrollPane=new JScrollPane();
        scrollPane.setBounds(25,50,550,250);
        officeLabel=new JLabel("科室名:");
        officeLabel.setFont(font);
        officeLabel.setBounds(50,350,80,20);
        officeText=new JTextField(8);
        officeText.setBounds(110,350,80,20);
        btn_querybed=new JButton("空床位");
        btn_back=new JButton("返回");
        btn_back.setFont(font);
        btn_back.addActionListener(this);
        btn_back.setBounds(300,500,80,20);
        btn_querybed.addActionListener(this);
        btn_querybed.setFont(font);
        btn_querybed.setBounds(100,500,140,20);




        backLayout.add(btn_querybed);
        backLayout.add(officeLabel);
        backLayout.add(officeText);
        backLayout.add(scrollPane);
        backLayout.add(bedinfo);
        backLayout.add(btn_back);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new bedQuery();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_querybed){
            querybed();
        }else if(e.getSource()==btn_back){
            setVisible(false);
        }
    }



    public void querybed(){
        String boffice=officeText.getText().toString().trim();
        String sql="select * from Bed where Boffice=? and Binform=0";
        int count=0;
        if(!boffice.isEmpty()){
            try {
                connection=jdbcUtils.getConn();
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,boffice);
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                   count++;
                }
                info=new Object[count][4];
                count=0;
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    info[count][0]=resultSet.getString("Wno");
                    info[count][1]=resultSet.getString("Bno");
                    info[count][2]=resultSet.getString("Binform");
                    info[count][3]=resultSet.getString("Boffice");
                    count++;
                }
                bedTable=new JTable(info,bedtitle);
                scrollPane.getViewport().add(bedTable);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(bedQuery.this,"输入框不能为空！");
        }
        officeText.setText("");
    }






}

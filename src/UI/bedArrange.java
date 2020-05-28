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

public class bedArrange extends JFrame implements ActionListener {


    private JScrollPane scrollPane;
    private JTable patientTable;
    private String[] title={"Pno","Pward","Pbed","Pdoc","Poffice"};
    private JButton btn_add,btn_back;
    private JLabel pward,pbed;
    private Object[][] patient1;

    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;


    public bedArrange(){
        setResizable(false);
        Font font=new Font("黑体",Font.BOLD,18);
        setBounds(100,100,400,400);
        setLayout(null);
        setResizable(false);
        scrollPane=new JScrollPane();
        scrollPane.setBounds(20,30,350,200);
        pward=new JLabel("Pward");
        pward.setFont(font);

        btn_back=new JButton("返回");
        btn_back.setFont(font);
        btn_back.setBounds(200,300,50,20);
        add(btn_back);
        btn_back.addActionListener(this);

        patientquery();
        add(scrollPane);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new bedArrange();
    }

    public void patientquery(){
        String sql="select Pno,Pward,Pbed,Pdoc,Boffice from Patient p,Bed b where p.Pward=b.Wno and p.Pbed=b.Bno";
        int count=getCount(sql);
        patient1=new Object[count][5];
        count=0;
        try {
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                patient1[count][0]=resultSet.getString("Pno");
                patient1[count][1]=resultSet.getString("PWard");
                patient1[count][2]=resultSet.getString("Pbed");
                patient1[count][3]=resultSet.getString("Pdoc");
                patient1[count][4]=resultSet.getString("Boffice");
                count++;
            }
            patientTable=new JTable(patient1,title);
            scrollPane.getViewport().add(patientTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_back){
            setVisible(false);
        }
    }
}

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

public class bedDidtributed extends JFrame implements ActionListener {
    JFrame frame=new JFrame("床位分配查询");
    private JScrollPane scrollPane;
    private JTable bedTable;
    private Object[][] info;
    private String[] title = {"Pno", "Pname","Poffice", "Pward", "Pbed"};
    String s = "select * from Patient";
    private JButton btn_back;
    private JLabel showinfo;
    private backLayout backLayout=new backLayout("a.jpg");
    private JButton btn_add;
    private JLabel PwardLabel,PbedLabel,pnoLabel;
    private JTextField PwardText,PbedText,pnoText;
    private JButton showBed;
   // private JButton btn_showPatientInfo;

    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;

    public bedDidtributed(){
        setResizable(false);
        backLayout.setLayout(null);
        add(backLayout);
        setSize(600, 600);
        setLayout(null);
        setLocation(100, 50);
        Font font = new Font("黑体", Font.BOLD, 15);
        showinfo=new JLabel("床位分配");
        showinfo.setFont(font);
        showinfo.setBounds(250,30,150,20);
        scrollPane=new JScrollPane();
        scrollPane.setBounds(25, 80, 550, 200);
        btn_back=new JButton("返回");
        btn_back.addActionListener(this);
        btn_back.setFont(font);
        btn_back.setBounds(300,500,50,20);
        showBed=new JButton("显示空床位");
        showBed.setFont(font);
        showBed.setBounds(100,500,80,20);
        showBed.addActionListener(this);
        btn_add=new JButton("添加床位");
        btn_add.setFont(font);
        btn_add.setBounds(200,500,80,20);
        btn_add.addActionListener(this);

        pnoLabel=new JLabel("Pno");
        pnoLabel.setFont(font);
        pnoLabel.setBounds(100,300,80,20);
        pnoText=new JTextField(10);
        pnoText.setBounds(160,300,80,20);
        PwardLabel=new JLabel("Pward:");
        PwardLabel.setFont(font);
        PwardLabel.setBounds(100,350,80,20);
        PwardText=new JTextField(10);
        PwardText.setBounds(160,350,80,20);
        PbedLabel=new JLabel("Pbed:");
        PbedLabel.setFont(font);
        PbedLabel.setBounds(100,400,80,20);
        PbedText=new JTextField(10);
        PbedText.setBounds(150,400,80,20);



        backLayout.add(PbedLabel);
        backLayout.add(PbedText);
        backLayout.add(PwardText);
        backLayout.add(PwardLabel);
        backLayout.add(btn_add);
        backLayout.add(scrollPane);
        backLayout.add(btn_back);
        backLayout.add(showinfo);
        backLayout.add(pnoLabel);
        backLayout.add(pnoText);
        backLayout.add(showBed);
        showView(false);
        showdata();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new bedDidtributed();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_back){
            setVisible(false);
        }else if(e.getSource()==btn_add){
            add();
        }else if(e.getSource()==showBed){
            new bedQuery();
        }
    }

    public void showdata(){
        String sql="select DISTINCT Pno,Pname,Boffice,Pward,Pbed from Patient p,Doctor d,Bed b WHERE p.Pdoc=d.Dno and d.Doffice=b.Boffice";
        int count=0;
        connection= jdbcUtils.getConn();
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                count++;
            }
            info=new Object[count][5];
            count=0;
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                info[count][0]=resultSet.getString("Pno");
                info[count][1]=resultSet.getString("Pname");
                info[count][2]=resultSet.getString("Boffice");
                info[count][3]=resultSet.getString("Pward");
                info[count][4]=resultSet.getString("Pbed");
                count++;
            }
            bedTable=new JTable(info,title);
            scrollPane.getViewport().add(bedTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(){
        showView(true);
        String Wno=PwardText.getText().toString().trim();
        String Bno=PbedText.getText().toString().trim();
        String pno=pnoText.getText().toString().trim();
        String sql="update Patient SET Pward=?,Pbed=? where Pno=?";
        if(!Wno.isEmpty()&&!Bno.isEmpty()&&!pno.isEmpty()){
            connection=jdbcUtils.getConn();
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,Wno);
                preparedStatement.setString(2,Bno);
                preparedStatement.setString(3,pno);
                int result=preparedStatement.executeUpdate();
                if(result>0){
                    JOptionPane.showMessageDialog(bedDidtributed.this,"添加成功！");
                    refresh();
                    PwardText.setText("");
                    PbedText.setText("");
                    pnoText.setText("");
                    showdata();
                }else{
                    JOptionPane.showMessageDialog(bedDidtributed.this,"添加失败！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(bedDidtributed.this,"输入框不能为空！");
        }

    }

    public void showView(boolean show){
        PwardLabel.setVisible(show);
        PwardText.setVisible(show);
        PbedText.setVisible(show);
        PbedLabel.setVisible(show);
        pnoText.setVisible(show);
        pnoLabel.setVisible(show);
    }

    public void refresh(){
        String Wno=PwardText.getText().toString().trim();
        String Bno=PbedText.getText().toString().trim();
        String sql="update Bed set Binform=1 where Wno=? and Bno=?";
        connection=jdbcUtils.getConn();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,Wno);
            preparedStatement.setString(2,Bno);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

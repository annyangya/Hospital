package UI;

import JdbcConnect.jdbcUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class patientManage extends JFrame implements ActionListener {

    private JScrollPane scrollPane;
    private JLabel PnoLabel;
    private JButton btn_add, btn_query, btn_delete, btn_update, btn_refresh,btn_back;
    private JTextField pnoText;
    private JTable patientTable;
    private Object[][] info;
    private int size=0;
    private String[] title = {"Pno", "Pname", "Page", "Psex", "Pid", "Ptel", "Pdoc", "Pward", "Pbed", "Penter", "Pout"};
    String s = "select * from Patient";
    backLayout backLayout=new backLayout("a.jpg");

    private JLabel updatepnoLabel,updatepebdLabel,updatepoutLabel,updatewnoLabel;
    private JTextField updatepnoText,updatepbedText,updatePoutText,updatewnoText;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    JFrame frame = new JFrame("病人管理");

    public patientManage() {
        init();
    }

    public void init() {
        setResizable(false);
        backLayout.setLayout(null);
        add(backLayout);
        setSize(800, 760);
        setLayout(null);
        setLocation(100, 50);
        Font font = new Font("黑体", Font.BOLD, 15);
        PnoLabel = new JLabel("请输入病例号");
        PnoLabel.setBounds(180, 30, 100, 20);
        PnoLabel.setFont(font);
        pnoText = new JTextField(5);
        pnoText.setBounds(280, 30, 80, 20);
        btn_query = new JButton("查询");
        btn_query.setFont(font);
        btn_query.setBounds(400, 30, 50, 20);
        scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 80, 750, 350);

        btn_add = new JButton("添加");
        btn_add.setBounds(200, 700, 50, 20);
        btn_add.setFont(font);

        btn_delete = new JButton("删除");
        btn_delete.setFont(font);
        btn_delete.setBounds(300, 700, 50, 20);

        btn_update = new JButton("修改");
        btn_update.setFont(font);
        btn_update.setBounds(400, 700, 50, 20);

        btn_refresh = new JButton("刷新");
        btn_refresh.setFont(font);
        btn_refresh.setBounds(500, 700, 50, 20);

        btn_back=new JButton("返回");
        btn_back.setFont(font);
        btn_back.setBounds(600,700,50,20);

        updatepnoLabel=new JLabel("病例号:");
        updatepnoLabel.setFont(font);
        updatepnoLabel.setBounds(150,500,100,20);
        updatepnoText=new JTextField(10);
        updatepnoText.setBounds(200,500,80,20);

        updatepebdLabel=new JLabel("床位号:");
        updatepebdLabel.setBounds(400,550,100,20);
        updatepebdLabel.setFont(font);
        updatepbedText=new JTextField(10);
        updatepbedText.setBounds(450,550,80,20);

        updatewnoLabel=new JLabel("病房号:");
        updatewnoLabel.setFont(font);
        updatewnoLabel.setBounds(400,500,100,20);
        updatewnoText=new JTextField(10);
        updatewnoText.setBounds(450,500,80,20);


        updatepoutLabel=new JLabel("出院时间:");
        updatepoutLabel.setFont(font);
        updatepoutLabel.setBounds(150,550,100,20);
        updatePoutText=new JTextField(10);
        updatePoutText.setBounds(220,550,80,20);

        btn_add.addActionListener(this);
        btn_refresh.addActionListener(this);
        btn_update.addActionListener(this);
        btn_delete.addActionListener(this);
        btn_query.addActionListener(this);
        btn_back.addActionListener(this);


        backLayout.add(PnoLabel);
        backLayout.add(pnoText);
        backLayout.add(btn_query);
        backLayout.add(scrollPane);

        backLayout.add(btn_add);
        backLayout.add(btn_delete);
        backLayout.add(btn_refresh);
        backLayout.add(btn_update);

        backLayout.add(updatepnoLabel);
        backLayout.add(updatepnoText);
        backLayout.add(updatepebdLabel);
        backLayout.add(updatepbedText);
        backLayout.add(updatepoutLabel);
        backLayout.add(updatePoutText);
        backLayout.add(btn_back);
        backLayout.add(updatewnoLabel);
        backLayout.add(updatewnoText);
        showView(false,false,false);

        showdata();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void showdata() {
        String sql = "SELECT * from Patient";
        int count = getCount(sql);
        info = new Object[count][11];
        count = 0;
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                info=getObject(info, count);
                count++;
            }
            patientTable = new JTable(info, title);
            scrollPane.getViewport().add(patientTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void refreshTable(String sql) {
        int count = getCount(sql);
        Object[][] query = new Object[count][11];
        count = 0;
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                query=getObject(query, count);
                count++;
            }
//            for (int i = 0; i < info.length; i++) {
//                for (int j = 0; j < 11; j++) {
//                    patientTable.setValueAt("", i, j);
//                }
//            }
//            for (int i = 0; i < query.length; i++) {
//                for (int j = 0; j < 11; j++) {
//                    patientTable.setValueAt(query[i][j], i, j);
//                }
//            }
            showdata();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_add) {
            new patientAddInfo();
        } else if (e.getSource() == btn_refresh) {
            refreshTable(s);
        }else if(e.getSource()==btn_delete){
            delete();
        }else if(e.getSource()==btn_update){
            modify();
        }
        else if(btn_query==e.getSource()){
            query();
        }
        else if(btn_back==e.getSource()){
            this.setVisible(false);
        }
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


    public static void main(String[] args) {
        new patientManage();
    }

    public Object[][] getObject(Object[][] query, int count) {
        try {
            query[count][0] = resultSet.getString("Pno");
            query[count][1] = resultSet.getString("Pname");
            query[count][2] = resultSet.getString("Page");
            query[count][3] = resultSet.getString("Psex");
            query[count][4] = resultSet.getString("Pid");
            query[count][5] = resultSet.getString("Ptel");
            query[count][6] = resultSet.getString("Pdoc");
            query[count][7] = resultSet.getString("Pward");
            query[count][8] = resultSet.getString("Pbed");
            query[count][9] = resultSet.getString("Penter");
            query[count][10] = resultSet.getString("Pout");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;

    }


    public void showView(boolean show1,boolean show2,boolean show3){
        updatepnoLabel.setVisible(show1);
        updatepnoText.setVisible(show1);
        updatepbedText.setVisible(show2);
        updatepebdLabel.setVisible(show2);
        updatepoutLabel.setVisible(show3);
        updatePoutText.setVisible(show3);
        updatewnoText.setVisible(show3);
        updatewnoLabel.setVisible(show3);
    }

    public void delete(){
        showView(true,false,false);
        String text=updatepnoText.getText().toString().trim();
        if(!text.isEmpty()){
            String sql="delete from Patient where Pno=?";
            connection=jdbcUtils.getConn();
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,text);
                int result=preparedStatement.executeUpdate();
                if(result>0){
                    JOptionPane.showMessageDialog(patientManage.this,"删除成功！");
                }else {
                    JOptionPane.showMessageDialog(patientManage.this,"删除失败！");
                }
                refreshTable(s);
                showView(false,false,false);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(patientManage.this,"输入框不能为空！");
        }
    }

    public void modify(){
        showView(true,true,true);
        String pnoText=updatepnoText.getText().toString().trim();
        String bedText=updatepbedText.getText().toString().trim();
        String outText=updatePoutText.getText().trim();
        String wnoText=updatewnoText.getText().trim();
        if(!pnoText.isEmpty()&&!bedText.isEmpty()&&!outText.isEmpty()&&!wnoText.isEmpty()){
            String sql="update Patient  set Pout=? where Pno=?";
            connection=jdbcUtils.getConn();
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,outText);
                preparedStatement.setString(2,pnoText);
                int result=preparedStatement.executeUpdate();
                if(result>0){
                    JOptionPane.showMessageDialog(patientManage.this,"修改成功！");
                }
                else {
                    JOptionPane.showMessageDialog(patientManage.this,"修改失败！");
                }
                refreshTable(s);
                updatebed();
                updatePoutText.setText("");
                updatepnoText.setText("");
                updatepbedText.setText("");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            showView(false,false,false);
        }else{
            JOptionPane.showMessageDialog(patientManage.this,"输入框不能为空！");
        }
    }

    public void query(){
        showView(false,false,false);
        String text=pnoText.getText().toString().trim();
        if(!text.isEmpty()){
            String sql="select * from Patient where Pno='"+text+"'";
            init(sql);
        }else{
            JOptionPane.showMessageDialog(patientManage.this,"输入框不能为空！");
        }
    }

    public void updatebed(){
        //String pnoText=updatepnoText.getText().toString().trim();
        String bnoText=updatepbedText.getText().toString().trim();
        String wnoText=updatewnoText.getText().toString().trim();
        String sql="update Bed set Binform=1 where Bno=? and Wno=?";
        connection=jdbcUtils.getConn();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,bnoText);
            preparedStatement.setString(2,wnoText);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void init(String sql){
        int count = 0;
        connection = jdbcUtils.getConn();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
            size=count;
            Object[][] query = new Object[count][11];
            count = 0;
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                query=getObject(query,count);
                count++;
            }
            for (int i = 0; i < info.length; i++) {
                for (int j = 0; j < 11; j++) {
                    patientTable.setValueAt("", i, j);
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < 11; j++) {
                    patientTable.setValueAt(query[i][j], i, j);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

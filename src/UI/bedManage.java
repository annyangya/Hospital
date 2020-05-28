package UI;

import JdbcConnect.jdbcBed;
import JdbcConnect.jdbcUtils;
import entity.Bed;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class bedManage extends JFrame implements ActionListener {
    private JScrollPane scrollPane;
    private JLabel roomLabel;
    private JButton btn_add, btn_query, btn_delete, btn_update;
    private JTextField roomText;
    private JTextField bedText;
    private JTable bedTable;
    private int size = 0;
    private Object[][] info=new Object[100][4];
    private String[] title = {"Wno", "Bno", "Binform", "Boffice"};
    private JLabel addWno, addBno, addBinform, addBoffice;
    private JTextField addWnoText, addBnoText, addBinformText, addBofficeText;
    private JButton btn_back;
    private backLayout backLayout=new backLayout("a.jpg");
    private JButton btn_refresh;
    private JButton btn_showPatientInfo;



    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    
    JFrame frame = new JFrame("病房床位管理");

    public bedManage() {
        initView();
    }

    public void initView() {
        setResizable(false);
        backLayout.setLayout(null);
        add(backLayout);
        setSize(600, 600);
        setLayout(null);
        setLocation(100, 50);
        Font font = new Font("黑体", Font.BOLD, 15);
        Font font1 = new Font("黑体", Font.BOLD, 10);
        btn_query = new JButton("查询");
        btn_add = new JButton("添加");
        btn_delete = new JButton("删除");
        btn_update = new JButton("修改");
        roomText = new JTextField(10);
        roomLabel = new JLabel("请输入病房号和床位号:");
        roomLabel.setBounds(140, 30, 180, 20);
        roomLabel.setFont(font);
        roomText.setBounds(300, 30, 80, 20);
        bedText=new JTextField(5);
        bedText.setBounds(400, 30, 80, 20);
        btn_query.setBounds(500, 30, 50, 20);
        btn_query.setFont(font);
        btn_query.addActionListener(this);
        scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 80, 550, 200);
        btn_add.setBounds(100, 500, 50, 20);
        btn_delete.setBounds(170, 500, 50, 20);
        btn_update.setBounds(240, 500, 50, 20);
        btn_back = new JButton("返回");
        btn_back.setBounds(310, 500, 50, 20);
        btn_back.addActionListener(this);
        btn_back.setFont(font);
        btn_refresh=new JButton("刷新");
        btn_refresh.addActionListener(this);
        btn_refresh.setBounds(380,500,50,20);
        btn_refresh.setFont(font);

        btn_showPatientInfo=new JButton("展示病人床位信息");
        btn_showPatientInfo.setFont(font);
        btn_showPatientInfo.setBounds(450,500,140,20);
        backLayout.add(btn_showPatientInfo);
        btn_showPatientInfo.addActionListener(this);


        addWno = new JLabel("Wno:");
        addWno.setBounds(150, 350, 150, 20);
        addWno.setFont(font);
        addWnoText = new JTextField(30);
        addWnoText.setBounds(190, 350, 80, 20);

        addBno = new JLabel("Bno:");
        addBno.setFont(font);
        addBno.setBounds(300, 350, 50, 20);

        addBnoText = new JTextField(30);
        addBnoText.setBounds(340, 350, 80, 20);

        addBinform = new JLabel("Binform:");
        addBinform.setFont(font);
        addBinform.setBounds(120, 450, 100, 20);

        addBinformText = new JTextField(30);
        addBinformText.setBounds(190, 450, 80, 20);

        addBoffice = new JLabel("Boffice:");
        addBoffice.setFont(font);
        addBoffice.setBounds(275, 450, 80, 20);

        addBofficeText = new JTextField(30);
        addBofficeText.setBounds(340, 450, 80, 20);


        btn_add.setFont(font);
        btn_delete.setFont(font);
        btn_update.setFont(font);
        btn_add.addActionListener(this);
        btn_delete.addActionListener(this);
        btn_update.addActionListener(this);
        btn_update.addActionListener(this);
        btn_back.addActionListener(this);


        showData();
        backLayout.add(roomLabel);
        backLayout.add(roomText);
        backLayout.add(btn_query);
        backLayout.add(scrollPane);
        backLayout.add(btn_add);
        backLayout.add(btn_delete);
      //  backLayout.add(btn_update);
        backLayout.add(addWno);
        backLayout.add(addWnoText);
        backLayout.add(addBno);
        backLayout.add(addBnoText);
        backLayout.add(addBinform);
        backLayout.add(addBinformText);
        backLayout.add(addBoffice);
        backLayout.add(addBofficeText);
        backLayout.add(bedText);
        backLayout.add(btn_back);
        backLayout.add(btn_refresh);
        showAddView(false, false, false, false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==btn_back) {
            setVisible(false);
        }else if(e.getSource()==btn_add) {
            showAddView(true, true, true, true);
            String WnoText = addWnoText.getText().toString().trim();
            String BnoText = addBnoText.getText().toString().trim();
            String BinformText = addBinformText.getText().toString().trim();
            String BofficeText = addBofficeText.getText().toString().trim();

            if (!WnoText.isEmpty() && !BnoText.isEmpty() && !BinformText.isEmpty() && !BofficeText.isEmpty()) {
                if (WnoText.matches("[0-9]+") && BnoText.matches("[0-6]+") && BinformText.matches("[0-1]+")) {
                    Bed bed = new Bed();
                    bed.setWno(WnoText);
                    bed.setBno(BnoText);
                    bed.setBinform(BinformText);
                    bed.setBoffice(BofficeText);
                    jdbcBed jdbcBed = new jdbcBed(null);
                    try {
                        jdbcBed.BedAdd(bed);
                        addWnoText.setText("");
                        addBnoText.setText("");
                        addBinformText.setText("");
                        addBofficeText.setText("");
                        JOptionPane.showMessageDialog(bedManage.this, "添加成功！");
                        showData();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(bedManage.this, "添加失败！");
                    }

                } else {
                    JOptionPane.showMessageDialog(bedManage.this, "请输入正确的病房号、病床号、病床使用情况和科室名");
                }

            }
        }else if(e.getSource()==btn_delete){
            showAddView(true,true,false,false);
            String WnoText = addWnoText.getText().toString().trim();
            String BnoText = addBnoText.getText().toString().trim();
            if(!WnoText.isEmpty() && !BnoText.isEmpty()){
                if(WnoText.matches("[0-9]+") && BnoText.matches("[0-6]+")){
                    String sql="select * from Bed";
                    String s="delete from Bed where Wno=? and Bno=?";
                    try {
                        connection=jdbcUtils.getConn();
                        preparedStatement=connection.prepareStatement(s);
                        preparedStatement.setString(1,WnoText);
                        preparedStatement.setString(2,BnoText);
                        preparedStatement.executeUpdate();
                        addWnoText.setText("");
                        addBnoText.setText("");
                        JOptionPane.showMessageDialog(bedManage.this,"删除成功！");
                        intiTable(sql);
                    } catch (SQLException e3) {
                        e3.printStackTrace();
                        JOptionPane.showMessageDialog(bedManage.this,"删除失败！");
                    }
                }
            }

        }else if(e.getSource()==btn_query){
            String text=roomText.getText().toString();
            String bed=bedText.getText().toString();
            String regex="[0-9]+";
            if(!text.isEmpty()){
                if((Integer.parseInt(text)>100&&Integer.parseInt(text)<=120||Integer.parseInt(text)>200&&Integer.parseInt(text)<=220
                        ||Integer.parseInt(text)>300&&Integer.parseInt(text)<=320||Integer.parseInt(text)>400&&Integer.parseInt(text)<=420||
                        Integer.parseInt(text)>500&&Integer.parseInt(text)<=520||Integer.parseInt(text)>600&&Integer.parseInt(text)<=620)||bed.matches("[0-1]+")||text.matches("[0-9]+]")){
                    String sql="select * from Bed where Wno="+"'"+text+"' and Bno='"+bed+"'";
                    intiTable(sql);
                    roomText.setText("");
                    bedText.setText("");
                }
            }
        }else if(e.getSource()==btn_update){
            showAddView(true,true,true,false);
            String WnoText = addWnoText.getText().toString().trim();
            String BnoText = addBnoText.getText().toString().trim();
            String BinformText = addBinformText.getText().toString().trim();
            if(WnoText.matches("[0-9]+") && BnoText.matches("[0-6]+") && BinformText.matches("[0-1]+")){
                String sql="UPDATE Bed SET Binform=? where Wno=? and Bno=?";
                connection=jdbcUtils.getConn();
                try {
                    preparedStatement=connection.prepareStatement(sql);
                    preparedStatement.setString(1,BinformText);
                    preparedStatement.setString(2,WnoText);
                    preparedStatement.setString(3,BnoText);
                    preparedStatement.executeUpdate();
                    addWnoText.setText("");
                    addBnoText.setText("");
                    addBinformText.setText("");
                    String s="select * from Bed";
                    intiTable(s);
                    JOptionPane.showMessageDialog(bedManage.this,"修改成功！");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(bedManage.this,"修改失败！");
                }
            }
        }else if(e.getSource()==btn_refresh){
            showData();
        }else if(e.getSource()==btn_showPatientInfo){
            new bedArrange();
        }
    }

    public  void showData() {
        String sql = "select * from Bed";
        try {
            connection=jdbcUtils.getConn();
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count++;
            }
            info = new Object[count][4];
            count = 0;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                info[count][0] = resultSet.getString("Wno");
                info[count][1] = resultSet.getString("Bno");
                info[count][2] = resultSet.getString("Binform");
                info[count][3] = resultSet.getString("Boffice");
                count++;
            }
            bedTable = new JTable(info, title);
            scrollPane.getViewport().add(bedTable);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据源错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showAddView(boolean wnoshow, boolean bnoshow, boolean Binformshow, boolean Bofficeshow) {
        addWno.setVisible(wnoshow);
        addWnoText.setVisible(wnoshow);
        addBno.setVisible(bnoshow);
        addBnoText.setVisible(bnoshow);
        addBinform.setVisible(Binformshow);
        addBinformText.setVisible(Binformshow);
        addBoffice.setVisible(Bofficeshow);
        addBofficeText.setVisible(Bofficeshow);
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
            Object[][] query = new Object[size][4];
            while (resultSet.next()) {
                query[count][0] = resultSet.getString("Wno");
                query[count][1] = resultSet.getString("Bno");
                query[count][2] = resultSet.getString("Binform");
                query[count][3] = resultSet.getString("Boffice");
                count++;
            }
            for (int i = 0; i < info.length; i++) {
                for (int j = 0; j < 4; j++) {
                    bedTable.setValueAt("", i, j);
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < 4; j++) {
                    bedTable.setValueAt(query[i][j], i, j);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new bedManage();
    }
}

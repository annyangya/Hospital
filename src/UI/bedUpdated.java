package UI;

import JdbcConnect.jdbcBed;
import JdbcConnect.jdbcUtils;
import entity.Bed;

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
import java.util.HashMap;

public class bedUpdated extends JFrame implements ActionListener {
    private JScrollPane scrollPane;
    private JLabel roomLabel;
    private JButton btn_add,btn_query,btn_delete,btn_update;
    private JPanel panel;
    private JTextField roomText;
    private JTable bedTable;
    private JTableHeader bedHeader;
    private DefaultTableModel defaultTableModel;
    private int size=0;
    private Object[][] info;
    private  String[] title={"Wno","Bno","Binform","Boffice"};
    private JLabel addWno,addBno,addBinform,addBoffice;
    private JTextField addWnoText,addBnoText,addBinformText,addBofficeText;
    private JButton btn_confirm;

     Connection connection=null;
     PreparedStatement preparedStatement=null;
     ResultSet resultSet=null;

    JFrame frame=new JFrame("病房床位管理");

    public bedUpdated(){
        initView();
    }

    public void initView(){
        setResizable(false);
        setSize(600,600);
        setLayout(null);
        setLocation(100,50);
        Font font=new Font("黑体",Font.BOLD,15);
        Font font1=new Font("黑体",Font.BOLD,10);
        btn_query=new JButton("查询");
        btn_add=new JButton("添加");
        btn_delete=new JButton("删除");
        btn_update=new JButton("修改");
        roomText=new JTextField(5);
        roomLabel=new JLabel("请输入病房号:");
        roomLabel.setBounds(180,30,100,20);
        roomLabel.setFont(font);
        roomText.setBounds(280,30,100,20);
        btn_query.setBounds(400,30,50,20);
        btn_query.setFont(font);
        btn_query.addActionListener(this);
        scrollPane=new JScrollPane();
        scrollPane.setBounds(25,80,550,200);
        defaultTableModel=new DefaultTableModel();
        btn_add.setBounds(200,500,50,20);
        btn_delete.setBounds(270,500,50,20);
        btn_update.setBounds(340,500,50,20);
        btn_confirm=new JButton("确认");
        btn_confirm.setBounds(410,500,50,20);
        btn_confirm.addActionListener(this);
        btn_confirm.setFont(font);

        addWno=new JLabel("Wno:");
        addWno.setBounds(150,350,150,20);
        addWno.setFont(font);
        addWnoText=new JTextField(30);
        addWnoText.setBounds(190,350,80,20);

        addBno=new JLabel("Bno:");
        addBno.setFont(font);
        addBno.setBounds(300,350,50,20);

        addBnoText=new JTextField(30);
        addBnoText.setBounds(340,350,80,20);

        addBinform=new JLabel("Binform:");
        addBinform.setFont(font);
        addBinform.setBounds(120,450,100,20);

        addBinformText=new JTextField(30);
        addBinformText.setBounds(190,450,80,20);

        addBoffice=new JLabel("Boffice:");
        addBoffice.setFont(font);
        addBoffice.setBounds(275,450,80,20);

        addBofficeText=new JTextField(30);
        addBofficeText.setBounds(340,450,80,20);


        btn_add.setFont(font);
        btn_delete.setFont(font);
        btn_update.setFont(font);
        btn_add.addActionListener(this);
        btn_delete.addActionListener(this);
        btn_update.addActionListener(this);
        btn_update.addActionListener(this);
        btn_confirm.addActionListener(this);




       showData();
        add(roomLabel);
        add(roomText);
        add(btn_query);
        add(scrollPane);
        add(btn_add);
        add(btn_delete);
        add(btn_update);
        add(addWno);
        add(addWnoText);
        add(addBno);
        add(addBnoText);
        add(addBinform);
        add(addBinformText);
        add(addBoffice);
        add(addBofficeText);
        add(btn_confirm);
        showAddView(false,false,false,false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public  void showData(){
        String sql="select * from Bed";
        connection= jdbcUtils.getConn();
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            int count=0;
            while (resultSet.next()){
                count++;
            }
            size=count;
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
            bedTable= new JTable(info,title);
            scrollPane.getViewport().add(bedTable);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new bedUpdated();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_query){
            String text=roomText.getText().toString();
            String regex="[0-9]+";
            if(!text.isEmpty()){
                if((Integer.parseInt(text)>100&&Integer.parseInt(text)<=120||Integer.parseInt(text)>200&&Integer.parseInt(text)<=220
                        ||Integer.parseInt(text)>300&&Integer.parseInt(text)<=320||Integer.parseInt(text)>400&&Integer.parseInt(text)<=420||
                        Integer.parseInt(text)>500&&Integer.parseInt(text)<=520||Integer.parseInt(text)>600&&Integer.parseInt(text)<=620)||text.matches("[0-9]+]")){
                    String sql="select * from Bed where Wno="+"'"+text+"'";
                    int count=0;
                    connection=jdbcUtils.getConn();
                    try {
                        preparedStatement=connection.prepareStatement(sql);
                        resultSet=preparedStatement.executeQuery();
                        while (resultSet.next()){
                            count++;
                        }
                        size=count;
                        count=0;

                        resultSet=preparedStatement.executeQuery();
                        Object[][] query=new Object[size][4];
                        while (resultSet.next()){
                            query[count][0]=resultSet.getString("Wno");
                            query[count][1]=resultSet.getString("Bno");
                            query[count][2]=resultSet.getString("Binform");
                            query[count][3]=resultSet.getString("Boffice");
                            count++;
                        }
                        for(int i=0;i<info.length;i++){
                            for (int j=0;j<4;j++){
                                bedTable.setValueAt("",i,j);
                            }
                        }
                        for(int i=0;i<size;i++){
                            for(int j=0;j<4;j++){
                                bedTable.setValueAt(query[i][j],i,j);
                            }
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(bedUpdated.this,"请输入正确的病房号!");
                }
            }


        }
        else if(e.getSource()==btn_add){
            showAddView(true,true,true,true);

            String WnoText=addWnoText.getText().toString().trim();
            String BnoText=addBnoText.getText().toString().trim();
            String BinformText=addBinformText.getText().toString().trim();
            String BofficeText=addBofficeText.getText().toString().trim();

            if(!WnoText.isEmpty()&&!BnoText.isEmpty()&&!BinformText.isEmpty()&&!BofficeText.isEmpty()){
                if(WnoText.matches("[0-9]+")&&BnoText.matches("[0-6]+")&&BinformText.matches("[0-1]+"))
                {
                    Bed bed=new Bed();
                    bed.setWno(WnoText);
                    bed.setBno(BnoText);
                    bed.setBinform(BinformText);
                    bed.setBoffice(BofficeText);
                    jdbcBed jdbcBed=new jdbcBed(null);
                    try{
                        jdbcBed.BedAdd(bed);
                        JOptionPane.showMessageDialog(bedUpdated.this,"添加成功！");
                    }catch (Exception e1){
                        JOptionPane.showMessageDialog(bedUpdated.this,"添加失败！");
                    }

                }else{
                    JOptionPane.showMessageDialog(bedUpdated.this,"请输入正确的病房号、病床号、病床使用情况和科室名");
                }

            }
            else if(e.getSource()==btn_confirm) {
                jdbcBed jdbcBed=new jdbcBed(null);
                Bed bed;
                try {
                    jdbcBed.BedQuery();
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(bedUpdated.this,"Error!Please Check!");
                }

                ArrayList<Bed> bedlist=jdbcBed.getBed();
                for(int i=0;i<bedlist.size();i++){
                    bed=bedlist.get(i);
                    String [] data={bed.getWno(),bed.getBno(),
                            bed.getBinform(),bed.getBoffice()};
                    for(int j=0;j<4;j++){
                        bedTable.setValueAt(data[j], i, j);
                    }
                }
                for(int i=0;i<4;i++){
                   bedTable.setValueAt("", bedlist.size(), i);
                }
                //initTable();
            }
        }
        else if(e.getSource()==btn_delete){
            Bed bed=new Bed.BedBuilder().addBedWno(addWnoText.getText().toString()).addBedBno(addBnoText.getText().toString()).createBed();
            jdbcBed jdbcBed=new jdbcBed(null);
            try{
                jdbcBed.BedDelete(bed);
                addWnoText.setText("");
                addBofficeText.setText("");
                addBnoText.setText("");
                addBinformText.setText("");
                JOptionPane.showMessageDialog(bedUpdated.this,"删除成功");

            }catch (Exception e1){
                JOptionPane.showMessageDialog(bedUpdated.this,"删除失败");
            }
        }
    }

    public void showAddView(boolean wnoshow,boolean bnoshow,boolean Binformshow,boolean Bofficeshow){
        addWno.setVisible(wnoshow);
        addWnoText.setVisible(wnoshow);
        addBno.setVisible(bnoshow);
        addBnoText.setVisible(bnoshow);
        addBinform.setVisible(Binformshow);
        addBinformText.setVisible(Binformshow);
        addBoffice.setVisible(Bofficeshow);
        addBofficeText.setVisible(Bofficeshow);
    }

    public void initTable(){
        String sql="select * from Bed";
        connection= jdbcUtils.getConn();
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            int count=0;
            while (resultSet.next()){
                count++;
            }
            size=count;
            info=new Object[count][4];
            count=0;
            resultSet=preparedStatement.executeQuery();
            Object[][] queryInfo=new Object[size][4];
            while (resultSet.next()){
                info[count][0]=resultSet.getString("Wno");
                info[count][1]=resultSet.getString("Bno");
                info[count][2]=resultSet.getString("Binform");
                info[count][3]=resultSet.getString("Boffice");
                count++;
            }
            for(int i=0;i<info.length;i++){
                for (int j=0;j<4;j++){
                    bedTable.setValueAt("",i,j);
                }
            }
            for(int i=0;i<size;i++){
                for(int j=0;j<4;j++){
                    bedTable.setValueAt(queryInfo[i][j],i,j);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
}

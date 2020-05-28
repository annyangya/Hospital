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

public class patientAddInfo extends JFrame implements ActionListener {

    private JLabel pNo,pName,pSex,pAge,pDoc,pId,pWard,pEnter,pOut,pTel,pBed;
    private JTextField pnoText,nameText,ageText,sexText,docText,wardText,bedText,enterText,outText,idText,telText;
    private JButton btn_confirm,btn_back;
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;

    JFrame frame=new JFrame("病人信息添加");

    public patientAddInfo(){
        setResizable(false);

        setLayout(null);//默认是流逝布局
        setBounds(100,100,500,500);

        pNo=new JLabel("Pno:");
        Font font=new Font("黑体",Font.BOLD,20);
        pNo.setBounds(80,100,80,20);
        pNo.setFont(font);
        pName=new JLabel("Pname：");
        pName.setBounds(280,100,100,20);
        pName.setFont(font);

        nameText=new JTextField(10);
        nameText.setBounds(380,100,80,20);

        pnoText=new JTextField(10);
        pnoText.setBounds(160,100,80,20);

        pAge=new JLabel("Page:");
        pAge.setBounds(280,150,80,20);
        pAge.setFont(font);
        ageText=new JTextField(10);
        ageText.setBounds(350,150,80,20);

        pSex=new JLabel("Psex:");
        pSex.setBounds(80,150,80,20);
        pSex.setFont(font);
        sexText=new JTextField(10);
        sexText.setBounds(160,150,80,20);

        pId=new JLabel("Pid:");
        pId.setBounds(280,200,80,20);
        pId.setFont(font);
        idText=new JTextField(35);
        idText.setBounds(330,200,170,20);

        pTel=new JLabel("Ptel:");
        pTel.setBounds(80,200,80,20);
        pTel.setFont(font);
        telText=new JTextField(20);
        telText.setBounds(160,200,100,20);

        pWard=new JLabel("Pward:");
        pWard.setBounds(80,250,80,20);
        pWard.setFont(font);
        wardText=new JTextField(10);
        wardText.setBounds(160,250,80,20);

        pBed=new JLabel("Pbed:");
        pBed.setBounds(280,250,80,20);
        pBed.setFont(font);
        bedText=new JTextField(10);
        bedText.setBounds(350,250,80,20);

        pDoc=new JLabel("Pdoc:");
        pDoc.setBounds(80,250,80,20);
        pDoc.setFont(font);
        docText=new JTextField(10);
        docText.setBounds(160,250,80,20);

        pEnter=new JLabel("Penter:");
        pEnter.setBounds(80,300,80,20);
        pEnter.setFont(font);
        enterText=new JTextField(15);
        enterText.setBounds(160,300,100,20);

        pOut=new JLabel("Pout:");
        pOut.setBounds(280,350,80,20);
        pOut.setFont(font);
        outText=new JTextField(15);
        outText.setBounds(350,350,100,20);

        btn_confirm=new JButton("确认");
        btn_confirm.setFont(font);
        btn_confirm.setBounds(130,400,100,20);
        btn_confirm.addActionListener(this);

        btn_back=new JButton("返回");
        btn_back.setFont(font);
        btn_back.setBounds(270,400,100,20);
        btn_back.addActionListener(this);

        add(pNo);
        add(pnoText);
        add(pName);
        add(nameText);
        add(pSex);
        add(pAge);
        add(ageText);
        add(sexText);
        add(telText);
        add(pTel);
        add(pId);
        add(idText);
//        add(pWard);
//        add(wardText);
//        add(bedText);
//        add(pBed);
        add(docText);
        add(pDoc);
        add(pEnter);
        add(enterText);
//        add(pOut);
//        add(outText);
        add(btn_confirm);
        add(btn_back);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new patientAddInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_confirm){
            String pno=pnoText.getText().toString().trim();
            String pname=nameText.getText().toString().trim();
            String page=ageText.getText().toString().trim();
            String psex=sexText.getText().toString().trim();
            String ptel=telText.getText().toString().trim();
            String pid=idText.getText().toString().trim();
            String pdoc=docText.getText().toString().trim();
            String pward=wardText.getText().toString().trim();
            String pbed=bedText.getText().toString().trim();
            String penter=enterText.getText().toString().trim();
            String pout=outText.getText().toString().trim();

//            if(!pno.isEmpty()&&!pname.isEmpty()&&!page.isEmpty()&&!psex.isEmpty()&&!ptel.isEmpty()&&!pid.isEmpty()
//                    &&!pdoc.isEmpty()&&!penter.isEmpty()&&!pward.isEmpty()&&!pbed.isEmpty()&&!pout.isEmpty()){
//                String sql="insert into Patient values(?,?,?,?,?,?,?,?,?,?,?)";
//                connection= jdbcUtils.getConn();
//                try {
//                    preparedStatement=connection.prepareStatement(sql);
//                    preparedStatement.setString(1,pno);
//                    preparedStatement.setString(2,pname);
//                    preparedStatement.setString(3,page);
//                    preparedStatement.setString(4,psex);
//                    preparedStatement.setString(5,pid);
//                    preparedStatement.setString(6,ptel);
//                    preparedStatement.setString(7,pdoc);
//                    preparedStatement.setString(8,pward);
//                    preparedStatement.setString(9,pbed);
//                    preparedStatement.setString(10,penter);
//                    preparedStatement.setString(11,pout);
//                    int result=preparedStatement.executeUpdate();
//                    if(result>0){
//                        JOptionPane.showMessageDialog(patientAddInfo.this,"添加成功！");
//
//                    }
//                   else {
//                        JOptionPane.showMessageDialog(patientAddInfo.this,"添加失败！");
//                    }
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//
//            }else{
//                JOptionPane.showMessageDialog(patientAddInfo.this,"输入框内容不能为空！");
//            }

            if(!pno.isEmpty()&&!pname.isEmpty()&&!page.isEmpty()&&!psex.isEmpty()&&!pid.isEmpty()&&!ptel.isEmpty()&&!pdoc.isEmpty()&&!penter.isEmpty()){
                String sql="insert into Patient(Pno,Pname,Page,Psex,Pid,Ptel,Pdoc,penter) values(?,?,?,?,?,?,?,?)";
                connection=jdbcUtils.getConn();
                try {
                    preparedStatement=connection.prepareStatement(sql);
                    preparedStatement.setString(1,pno);
                    preparedStatement.setString(2,pname);
                    preparedStatement.setString(3,page);
                    preparedStatement.setString(4,psex);
                    preparedStatement.setString(5,pid);
                    preparedStatement.setString(6,ptel);
                    preparedStatement.setString(7,pdoc);
                    preparedStatement.setString(8,penter);

                    int result=preparedStatement.executeUpdate();
                    if(result>0){
                        JOptionPane.showMessageDialog(patientAddInfo.this,"添加成功！");
                    }else{
                        JOptionPane.showMessageDialog(patientAddInfo.this,"添加失败！");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(patientAddInfo.this,"输入框内容不能为空！");
            }


        }else if(e.getSource()==btn_back){
            setVisible(false);
        }

    }
}

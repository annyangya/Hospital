package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAll extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu menuBarPatient,menuBarDoctor,menuBedArrange,menuuerManage;
    JMenuItem patientUpdate;
    JMenuItem doctorUpdate;
    JMenuItem bedUpdate;
    JMenuItem bedArrange;
    JMenuItem bedQuery;
    JMenuItem userUpdate,passwordManage;

    JLabel label;
    JFrame frame=new JFrame("医院病房管理系统");

    public ManageAll(){
        setResizable(false);
        menuBarDoctor=new JMenu("医生管理");
        menuBarPatient=new JMenu("病人管理");
        menuBedArrange=new JMenu("床位管理");
        menuuerManage=new JMenu("用户管理");
        patientUpdate=new JMenuItem("病人信息管理");
        doctorUpdate=new JMenuItem("医生信息管理");
        bedUpdate=new JMenuItem("床位信息管理");
        bedArrange=new JMenuItem("床位分配管理");
        bedQuery=new JMenuItem("床位分配查询");
        userUpdate=new JMenuItem("用户信息管理");
        passwordManage=new JMenuItem("用户密码管理");

        label=new JLabel(new ImageIcon("aa.jpg"));
        menuBar=new JMenuBar();
        menuBarPatient.add(patientUpdate);
      //  menuBarBed.add(bedUpdate);
        menuBarDoctor.add(doctorUpdate);
        menuBedArrange.add(bedUpdate);
        menuBedArrange.add(bedQuery);
        //menuBedArrange.add(bedArrange);
        menuuerManage.add(userUpdate);
        menuuerManage.add(passwordManage);

        bedUpdate.addActionListener(this);
        patientUpdate.addActionListener(this);
        doctorUpdate.addActionListener(this);
        bedArrange.addActionListener(this);
        userUpdate.addActionListener(this);
        passwordManage.addActionListener(this);
        bedQuery.addActionListener(this);

        menuBar.add(menuBarPatient);
        menuBar.add(menuBarDoctor);
       // menuBar.add(menuBarBed);
        menuBar.add(menuBedArrange);
        menuBar.add(menuuerManage);
        setJMenuBar(menuBar);
        add(label);
        setVisible(true);
        setBounds(100,100,600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
       ManageAll manageAll=new ManageAll();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bedUpdate){
            new bedManage();
        }else if(e.getSource()==patientUpdate){
            new patientManage();
        }else if(e.getSource()==bedQuery){
            new bedDidtributed();
        }else if(e.getSource()==passwordManage){
            new passwordManage();
        }else if(e.getSource()==userUpdate){
            new userInfoManage();
        }else if(e.getSource()==doctorUpdate){
            new docManage();
        }else if(e.getSource()==bedQuery){
            new bedQuery();
        }else if(e.getSource()==bedArrange){
            new bedDidtributed();
        }
    }
}

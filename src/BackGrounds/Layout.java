package BackGrounds;

import javax.swing.*;

public class Layout extends JFrame{
    JMenuBar menuBar;
    JMenu menuBarPatient,menuBarDoctor,menuBarBed,menuBedArrange;

    JLabel label;
    JFrame frame=new JFrame("医院病房管理系统");

    public Layout(){
        menuBarBed=new JMenu("床位管理");
        menuBarDoctor=new JMenu("医生管理");
        menuBarPatient=new JMenu("病人管理");
        menuBedArrange=new JMenu("床位分配");

        label=new JLabel(new ImageIcon("backgrounds.jpg"));
        menuBar=new JMenuBar();
        menuBar.add(menuBarPatient);
        menuBar.add(menuBarDoctor);
        menuBar.add(menuBarBed);
        menuBar.add(menuBedArrange);
        setJMenuBar(menuBar);
        add(label);
        setVisible(true);
        setBounds(100,100,600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Layout layout=new Layout();
    }
}

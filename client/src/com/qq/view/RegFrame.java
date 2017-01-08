package com.qq.view;


import com.qq.util.ImageManager;
import com.qq.ui.BackgroundPanel;
import com.qq.ui.ComboBoxRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class RegFrame  extends ClientJFrame implements ActionListener{

    BackgroundPanel contentPanel = new BackgroundPanel("register");

    JButton btn_OK;
    JButton btn_Cancel;
    JFrame frame;
    JTextField name;
    JTextArea sign;
    JRadioButton sex_man,sex_woman;
    JComboBox year,month,day;  //生日
    JComboBox constellation; //星座
    JTextField bloodType;  //血型
    JTextField diploma;  //学历
    JTextField telephone;  //电话
    JTextField email;  //电子邮箱
    JTextField address; //地址
    JPasswordField password;
    JPasswordField resure_password;

    //标签

    JLabel l_name;
    JLabel l_password;
    JLabel l_repassword;
    JLabel l_sex;
    JLabel l_bir;
    JLabel l_xz;
    JLabel l_pho;
    JLabel l_bloodType;
    JLabel l_email;
    JLabel l_adr;
    JLabel l_xl;
    JLabel l_icon;
    JLabel l_qq;
    JTextField qq;

    JButton btn_min,btn_close;

    JButton btn_findpwd;

    JComboBox comboBox_icon;

    public RegFrame(){
        super();
        setUndecorated(true);
        setResizable(false);
        setBounds(500, 50, 500, 524);
        setTitle("注册");

        l_icon=new JLabel("头像");
        l_icon.setFont(new Font("楷体",0,18));
        l_icon.setBounds(40,130,100,20);
        l_icon.setVisible(true);
        contentPanel.add(l_icon);

        Map<String,ImageIcon> content=new LinkedHashMap<String,ImageIcon>();
        int num=0;int n=0;;
        for(int i=1;i<10;i++){
            String str_headicon="Head/"+i+".gif";
            content.put(""+i, ImageManager.getIcon(str_headicon));
            //	System.out.println(str_headicon);

        }
        comboBox_icon=new JComboBox(content.keySet().toArray());
        comboBox_icon.setBounds(5, 150, 120, 100);
        comboBox_icon.setOpaque(false);
        ComboBoxRenderer renderer=new ComboBoxRenderer(content);
        comboBox_icon.setRenderer(renderer);
        comboBox_icon.setMaximumRowCount(3);
        contentPanel.add(comboBox_icon);

        l_qq=new JLabel("  QQ号码:");
        l_qq.setFont(new java.awt.Font("楷体",0,18));
        l_qq.setBounds(140, 150,100,20);
        l_qq.setVisible(false);
        contentPanel.add(l_qq);

        qq = new JTextField();
        qq.setBounds(230,145,200,30);
        qq.setForeground(Color.green);
        qq.setFont(new Font("楷体",2,22));
        qq.setVisible(false);

        qq.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar=e.getKeyChar();
                if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {

                } else {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        contentPanel.add(qq);

        btn_findpwd = new JButton();
        btn_findpwd.setFont(new Font("楷体",1,18));
        btn_findpwd.setBounds(223, 464,181,49);
        btn_findpwd.setContentAreaFilled(false);;
        contentPanel.add(btn_findpwd);
        btn_findpwd.setVisible(false);
        btn_findpwd.addActionListener(this); //添加事件处理

        String str_gm = "    昵称:";
        l_name = new JLabel(str_gm);
        l_name.setFont(new Font("楷体",0,18));
        l_name.setBounds(140, 80,100,20);
        contentPanel.add(l_name);

        str_gm = "    密码:";
        l_password = new JLabel(str_gm);
        l_password.setFont(new Font("楷体",0,18));
        l_password.setBounds(140,115,100,20);
        contentPanel.add(l_password);

        str_gm = "确认密码:";
        l_repassword = new JLabel(str_gm);
        l_repassword.setFont(new Font("楷体",0,18));
        l_repassword.setBounds(140,150,100,20);
        contentPanel.add(l_repassword);

        l_sex= new JLabel("    性别:");
        l_sex.setFont(new Font("楷体",0,18));
        l_sex.setBounds(140,185,100,20);
        contentPanel.add(l_sex);

        l_bir=new JLabel("    生日:");
        l_bir.setFont(new Font("楷体",0,18));
        l_bir.setBounds(140,220,100,20);
        contentPanel.add(l_bir);

        l_xz = new JLabel("    星座:");
        l_xz.setFont(new Font("楷体",0,18));
        l_xz.setBounds(140,255,100,20);
        contentPanel.add(l_xz);

        l_xl= new JLabel("    学历:");
        l_xl.setFont(new Font("楷体",0,18));
        l_xl.setBounds(140,290,100,20);
        contentPanel.add(l_xl);

        l_bloodType = new JLabel("    血型:");
        l_bloodType.setFont(new Font("楷体",0,18));
        l_bloodType.setBounds(140,325,100,20);
        contentPanel.add(l_bloodType);

        l_pho= new JLabel("    电话:");
        l_pho.setFont(new Font("楷体",0,18));
        l_pho.setBounds(140,360,100,20);
        contentPanel.add(l_pho);

        l_email = new JLabel("电子邮箱:");
        l_email.setFont(new Font("楷体",0,18));
        l_email.setBounds(140,395,100,20);
        contentPanel.add(l_email);

        l_adr= new JLabel("  所在地:");
        l_adr.setFont(new Font("楷体",0,18));
        l_adr.setBounds(140,420,100,20);
        contentPanel.add(l_adr);

        name = new JTextField();
        name.setBounds(230,75,200,30);
        name.setForeground(Color.green);
        name.setFont(new Font("楷体",2,22));
        contentPanel.add(name);

        password = new JPasswordField();
        password.setBounds(230,110,200,30);
        password.setFont(new Font("宋体",0,14));
        contentPanel.add(password);


        resure_password = new JPasswordField();
        resure_password.setBounds(230,145,200,30);
        resure_password.setFont(new Font("宋体",0,14));
        contentPanel.add(resure_password);

        sex_man = new JRadioButton("男");
        sex_woman= new JRadioButton("女");
        sex_man.setOpaque(false);
        sex_woman.setOpaque(false);
        sex_man.setFont(new Font("楷体",0,16));
        sex_woman.setFont(new Font("楷体",0,16));
        sex_man.setBounds(230,180,60,30);
        sex_woman.setBounds(305,180,60,30);
        sex_man.addActionListener(this);
        sex_woman.addActionListener(this);
        contentPanel.add(sex_man);
        contentPanel.add(sex_woman);

        sex_man.setSelected(true);

        int i;
        year = new JComboBox();
        for(i=2014;i>1894;i--){
            year.addItem(""+i+"年");
        }
        year.setBounds(230,215,70,30);
        year.setOpaque(false);
        year.setFont(new Font("楷体",0,14));
        month = new JComboBox();
        for(i=1;i<13;i++){
            month.addItem(""+i+"月");
        }
        month.setBounds(310,215,55,30);
        month.setOpaque(false);
        month.setFont(new Font("楷体",0,14));
        day = new JComboBox();
        for(i=1;i<32;i++){
            day.addItem(""+i+"日");
        }
        day.setBounds(375,215,55,30);
        day.setOpaque(false);
        day.setFont(new Font("楷体",0,14));
        contentPanel.add(year);
        contentPanel.add(month);
        contentPanel.add(day);

        constellation= new JComboBox();
        constellation.addItem("白羊座(3.21-4.19)");
        constellation.addItem("金牛座(4.20-5.20)");
        constellation.addItem("双子座(5.21-6.21)");
        constellation.addItem("巨蟹座(6.21-7.22)");
        constellation.addItem("狮子座(7.23-8.22)");
        constellation.addItem("处女座(8.23-9.22)");
        constellation.addItem("天秤座(9.23-10.23)");
        constellation.addItem("天蝎座(10.24-11.22)");
        constellation.addItem("射手座(11.23-12.21)");
        constellation.addItem("魔蝎座(12.22-1.19)");
        constellation.addItem("水瓶座(1.20-2.18)");
        constellation.addItem("双鱼座(2.21-3.20)");
        constellation.setFont(new Font("楷体",0,14));
        constellation.setBounds(230,250,200,30);
        contentPanel.add(constellation);

        diploma = new JTextField();
        diploma.setBounds(230,285,200,30);
        diploma.setFont(new Font("楷体",0,14));
        contentPanel.add(diploma);

        bloodType = new JTextField();
        bloodType.setBounds(230,320,200,30);
        bloodType.setFont(new Font("楷体",0,14));
        contentPanel.add(bloodType);

        telephone = new JTextField();
        telephone.setBounds(230,355,200,30);
        telephone.setFont(new Font("楷体",0,14));
        telephone.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                int keyChar=e.getKeyChar();
                if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {

                } else {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

        });

        contentPanel.add(telephone);

        email= new JTextField();
        email.setBounds(230,390,200,30);
        email.setFont(new Font("楷体",0,14));
        contentPanel.add(email);

        address= new JTextField();
        address.setBounds(230,430,200,30);
        address.setFont(new Font("楷体",0,14));
        contentPanel.add(address);

        btn_OK = new JButton();
        btn_OK.setFont(new java.awt.Font("楷体",1,18));
        btn_OK.setBounds(223, 464,181,49);
        btn_OK.setContentAreaFilled(false);;
        contentPanel.add(btn_OK);
        btn_OK.addActionListener(this); //添加事件处理

        btn_min=new JButton();
        btn_min.setBounds(425,0,35,28);
        btn_min.setContentAreaFilled(false);
        contentPanel.add(btn_min);
        btn_min.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setExtendedState(JFrame.ICONIFIED);
            }
        });//添加事件处理

        btn_close=new JButton();
        btn_close.setBounds(460,0,35,28);
        btn_close.setContentAreaFilled(false);
        contentPanel.add(btn_close);
        btn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegFrame.this.dispose();
            }
        });

        contentPanel.setLayout(null);
        contentPanel.addMouseMotionListener(new MouseAdapter() {
            private Point draggingAnchor = null;
            @Override
            public void mouseMoved(MouseEvent e) {
                draggingAnchor = new Point(e.getX() + contentPanel.getX(), e.getY() + contentPanel.getY());


            }

            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getLocationOnScreen().x - draggingAnchor.x, e.getLocationOnScreen().y - draggingAnchor.y);
            }
        });

        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                RegFrame regFrame = new RegFrame();
                regFrame.setVisible(true);
            }
        });
    }
}

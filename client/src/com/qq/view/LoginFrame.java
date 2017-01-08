package com.qq.view;

import com.qq.ClientApplication;
import com.qq.bean.Message;
import com.qq.bean.MessageType;
import com.qq.bean.UserInfo;
import com.qq.ui.BackgroundPanel;
import com.qq.ui.MyButton;
import com.qq.ui.MyOptionPane;
import com.qq.ui.listener.CloseActionListener;
import com.qq.ui.listener.MinActionListener;
import com.qq.ui.listener.MouseDragListener;
import com.qq.util.MD5;
import com.qq.util.PasswordSave;
import sun.rmi.runtime.Log;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * 登录窗口
 */
public class LoginFrame  extends ClientJFrame implements ActionListener {

    private static final long serialVersionUID = 1L;


    private BackgroundPanel contentPanel = new BackgroundPanel("login1");
    private JPanel loginpanel = new JPanel();
    private MyButton btn_Login;
    private MyButton btn_Cancel;
    private MyButton btn_Register;
    private MyButton btn_ForgotPwd;
    private MyButton btn_close;
    private MyButton btn_min;
    private JTextField name;
    private JCheckBox cb_pwd;
    private JCheckBox cb_auto;
    private JPasswordField password;
    private int number = 0;

    private RegFrame regFrame;


    public LoginFrame() {



        setUndecorated(true);
        setResizable(false);
        setTitle("JavaQQ");
        setBounds(500, 250, 429, 330);



        //用户名输入框
        name = new JTextField();
        name.setOpaque(false);
        name.setBounds(133, 195, 194, 30);
        name.addActionListener(this);
        name.addCaretListener(new CaretListener(){

            @Override
            public void caretUpdate(CaretEvent e) {
                // TODO Auto-generated method stub
                name.setOpaque(true);
                if(new String(password.getPassword()).equals("")){
                    contentPanel.repaint();
                    password.setOpaque(false);
                }
            }


        });
        contentPanel.add(name);


        //密码输入框
        password = new JPasswordField();
        password.setBounds(133,223,194,30);
        password.setOpaque(false);
        password.addCaretListener(new CaretListener(){

            @Override
            public void caretUpdate(CaretEvent e) {
                password.setOpaque(true);
                if(name.getText().equals("")){
                    contentPanel.repaint();
                    name.setOpaque(false);
                }
            }


        });
        contentPanel.add(password);

        ////记住密码
        cb_pwd = new JCheckBox();
        cb_pwd.setBounds(129, 254,72,27);
        cb_pwd.setOpaque(false);
        contentPanel.add(cb_pwd);
        cb_pwd.addActionListener(this); //添加事件处理

        //自动登录
        cb_auto = new JCheckBox();
        cb_auto.setBounds(256,254,72,27);
        cb_auto.setOpaque(false);
        contentPanel.add(cb_auto);
        cb_auto.addActionListener(this);


        //登录按钮
        btn_Login = new MyButton();
        btn_Login.setBounds(133, 286,195,33);
        btn_Login.setContentAreaFilled(false);
        contentPanel.add(btn_Login);
        btn_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        }); //添加事件处理

        //注册按钮
        btn_Register = new MyButton();//注册
        btn_Register.setBounds(328,195,63,26);
        btn_Register.setContentAreaFilled(false);
        contentPanel.add(btn_Register);
        btn_Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(regFrame == null){
                    regFrame = new RegFrame();
                }
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        regFrame.setVisible(true);
                    }
                });
            }
        });//添加事件处理

        //找回密码
        btn_ForgotPwd=new MyButton();
        btn_ForgotPwd.setBounds(328,226,63,26);
        btn_ForgotPwd.setContentAreaFilled(false);
        contentPanel.add(btn_ForgotPwd);
        btn_ForgotPwd.addActionListener(this);//添加事件处理

        //取消按钮
        btn_Cancel = new MyButton();

        btn_Cancel.setBounds(114, 252,159,34);
        btn_Cancel.setContentAreaFilled(false);
        btn_Cancel.setVisible(false);
        contentPanel.add(btn_Cancel);
        btn_Cancel.addActionListener(this); //添加事件处理

        //最小化
        btn_min=new MyButton();
        btn_min.setBounds(370,2,28,28);
        btn_min.setContentAreaFilled(false);
        contentPanel.add(btn_min);
        btn_min.addActionListener(new MinActionListener(this));

        //关闭窗体
        btn_close=new MyButton();
        btn_close.setBounds(400,2,28,28);
        btn_close.setContentAreaFilled(false);
        contentPanel.add(btn_close);
        btn_close.addActionListener(new CloseActionListener(this));

        //加载记住的密码
        UserInfo userInfo = PasswordSave.load();
        if(userInfo!=null){
            name.setText(String.valueOf(userInfo.getQq()));
            password.setText(userInfo.getPassword());
            cb_pwd.setSelected(true);
        }

        contentPanel.setLayout(new BorderLayout());
        contentPanel.addMouseMotionListener(new MouseDragListener(contentPanel,this));

        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);    //让swing窗口居中，注意：必须在整个frame初始化完成后再加上此语句，否则将显示在屏幕右下角

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }

    private void doLogin(){
        //登录
        String user = name.getText();
        String pwd = new String(password.getPassword());
        long qq = 0;
        boolean isNumber = true;
        try {
            qq = Long.valueOf(user);
        }catch (NumberFormatException e){
            isNumber = false;
        }

        if(user.isEmpty()||pwd.isEmpty()){
            MyOptionPane.showMessageDialog(this,"您的输入有误！","提示");
            return;
        }

        if(!isNumber){
            MyOptionPane.showMessageDialog(this,"请输入正确的QQ号！","提示");
            return;
        }

        if(cb_pwd.isSelected()){
            PasswordSave.save(qq,pwd);
        }else{
            PasswordSave.remove();
        }

        //封装登录消息，并发送
        Message loginMessage = new Message(MessageType.LOGIN);
        UserInfo userInfo = new UserInfo();
        userInfo.setQq(qq);
        userInfo.setPassword(MD5.getMD5(pwd));
        loginMessage.setFrom(userInfo);

        try {
            ClientApplication.getClient().send(loginMessage);
            Message message = awaitOrAlert();
            if(message!=null){
                switch (message.getType()){
                    case SUCCESS:
                        //登录成功，进入主窗口
                        System.out.println("登录成功！");
                        UserInfo u = message.getFrom();
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                MainFrame mainFrame = new MainFrame(u);
                                mainFrame.setVisible(true);
                                LoginFrame.this.dispose();
                            }
                        });
                        break;
                    case ERROR:
                        MyOptionPane.showMessageDialog(this,"登录失败："+message.getContent(),"提示");break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            MyOptionPane.showMessageDialog(this,"发送失败："+e.getLocalizedMessage(),"错误");
        }

    }
}

package com.qq.view;

import com.qq.bean.Message;
import com.qq.bean.MessageType;
import com.qq.bean.UserInfo;
import com.qq.ui.BackgroundPanel;
import com.qq.ui.MyButton;
import com.qq.ui.MyOptionPane;
import com.qq.ui.listener.CloseActionListener;
import com.qq.ui.listener.MinActionListener;
import com.qq.ui.listener.MouseDragListener;
import com.qq.ui.listener.ScrollBotListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatFrame  extends ClientJFrame{

    private BackgroundPanel chatPanel = new BackgroundPanel("chat");

    private MyButton btn_down;
    private MyButton btn_min; //最小化按钮
    private MyButton btn_max; //最大化按钮
    private MyButton btn_close; //关闭按钮

    //下方按钮
    private MyButton btm_close; //关闭
    private MyButton btm_send; // 发送

    private JTextArea txt_message; //发送的消息

    private JLabel lbl_title; //标题

    private JEditorPane editorPane; //中间的聊天内容
    private JScrollPane editorScrollPane;


    private static final int[][] btnPos = {
            //定义上方的几个按钮
            {5,50,28,31},{43,50,28,31},{80,50,49,31},{130,50,49,31},{178,50,49,31},{228,50,30,31},{264,50,49,31},
            //定义下方的几个按钮
            {7,377,22,22},{35,377,22,22},{63,377,22,22},{91,377,34,22},{126,377,22,22},{153,377,34,22},{192,377,22,22},{217,377,34,22}
    };

    private UserInfo userInfo;
    private UserInfo self;

    public void setSelf(UserInfo self) {
        this.self = self;
    }


    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        setTitle("与"+userInfo.getNickname()+"聊天中...");
        lbl_title.setText(userInfo.getNickname()+"（"+userInfo.getQq()+"）");
    }



    public ChatFrame(){
        super();


        setUndecorated(true);
        setResizable(false);
        setTitle("与好友聊天中...");

        setBounds(500,250,587,510);

        btn_down=new MyButton();
        btn_down.setBounds(468,5,28,28);
        btn_down.setContentAreaFilled(false);
        chatPanel.add(btn_down);

        btn_min = new MyButton();
        btn_min.setBounds(498,5,28,28);
        btn_min.setContentAreaFilled(false);
        btn_min.addActionListener(new MinActionListener(this));
        chatPanel.add(btn_min);

        btn_max = new MyButton();
        btn_max.setBounds(528,5,28,28);
        btn_max.setContentAreaFilled(false);
        chatPanel.add(btn_max);

        btn_close = new MyButton();
        btn_close.setBounds(558,5,28,28);
        btn_close.setContentAreaFilled(false);
        btn_close.addActionListener(new CloseActionListener(this));
        chatPanel.add(btn_close);


        btm_close = new MyButton();
        btm_close.setBounds(283,478,70,24);
        btm_close.setContentAreaFilled(false);
        btm_close.addActionListener(new CloseActionListener(this,false));
        chatPanel.add(btm_close);


        btm_send = new MyButton();
        btm_send.setBounds(357,478,87,24);
        btm_send.setContentAreaFilled(false);
        btm_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        chatPanel.add(btm_send);

        lbl_title = new JLabel("飞翔的企鹅（88888888）");
        lbl_title.setFont(new Font("微软雅黑",Font.BOLD,14));
        lbl_title.setBounds(57,5,384,28);
        chatPanel.add(lbl_title);

        for(int[] top: btnPos){
            MyButton myButton = new MyButton();
            myButton.setBounds(top[0],top[1],top[2],top[3]);
            myButton.setContentAreaFilled(false);
            chatPanel.add(myButton);
        }

        txt_message = new JTextArea();
        txt_message.setBackground(new Color(247,242,232));
        txt_message.setBorder(BorderFactory.createEmptyBorder());
        txt_message.getDocument().addDocumentListener(new ScrollBotListener(txt_message));
        txt_message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER && e.isControlDown()){
                    sendMessage();
                }
            }
        });
        txt_message.setLineWrap(true);
        txt_message.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txt_message);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBounds(2,405,443,69);
        chatPanel.add(scrollPane);

        editorPane = new JEditorPane();
        editorPane.setOpaque(false);
        editorPane.setFont(new Font("微软雅黑",Font.BOLD,14));
        editorPane.setBorder(BorderFactory.createEmptyBorder());
        editorScrollPane = new JScrollPane(editorPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        editorScrollPane.setBounds(2,84,443,287);
        editorScrollPane.setOpaque(false);
        editorScrollPane.setBackground(null);
        editorScrollPane.setBorder(BorderFactory.createEmptyBorder());
        editorScrollPane.getViewport().setOpaque(false);
        editorScrollPane.setHorizontalScrollBar(null);
        chatPanel.add(editorScrollPane);

        chatPanel.setLayout(new BorderLayout());
        chatPanel.addMouseMotionListener(new MouseDragListener(chatPanel,this));
        getContentPane().add(chatPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
    }

    private StringBuilder databuf = new StringBuilder("<style>.title{font-size:10px} .content{font-size:14px}</style>");

    public void appendText(Message message){
        databuf.append("<div class='title'>").append(message.getFrom().getNickname()).append(' ').append(message.getTime()).append(':').append("</div>");
        databuf.append("<div class='content'>").append(message.getContent()).append("</div><br>");
        editorPane.setContentType("text/html");
        editorPane.setText(databuf.toString());

        JScrollBar scrollBar = editorScrollPane.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMaximum());
    }



    private void sendMessage(){


        String txt = txt_message.getText();
        txt_message.setText("");
        if(txt.isEmpty()){
            MyOptionPane.showMessageDialog(this,"请输入内容！","提示");
            return;
        }
        //封装chatMessage
        Message message = new Message();
        message.setFrom(self);
        message.setTo(userInfo);
        message.setType(MessageType.MESSAGE);
        message.setTime(getNow());
        message.setContent(txt);
        sendOrAlert(message);

        Message res = awaitOrAlert();
        switch (res.getType()){
            case SUCCESS:
                appendText(message);
                break;
            case ERROR:
                MyOptionPane.showMessageDialog(this,res.getContent(),"错误");
                break;
        }
    }



    private static String getNow() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(d);
    }


}
package com.qq.view;


import com.qq.util.ImageManager;
import com.qq.ui.BackgroundPanel;
import com.qq.ui.MyButton;
import com.qq.ui.MyOptionPane;
import com.qq.ui.listener.MouseDragListener;
import com.qq.util.StringUtil;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends AutoHideFrame{

    private BackgroundPanel mainPanel = new BackgroundPanel("main");
    private JButton btn_min;
    private JButton btn_close;
    private TrayIcon trayIcon;

    private JTree tree;
    private JPopupMenu popupMenu;
    private JMenuItem add;
    private JMenuItem del;
    private JMenuItem detail;

    private JLabel lblNick;
    private JLabel lblSign;


    public MainFrame(){
        setUndecorated(true);
        setResizable(false);
        setTitle("QQ");
        setBounds(500,250,281,698);

        btn_min = new MyButton();
        btn_min.setBounds(233,4,20,20);
        btn_min.setContentAreaFilled(false);
        btn_min.addActionListener(new ActionListener() {
            @Override
            public synchronized void actionPerformed(ActionEvent e) {
                if(SystemTray.isSupported()){
                    setVisible(false);
                }else{
                    setExtendedState(JFrame.ICONIFIED);
                }
            }
        });
        mainPanel.add(btn_min);

        btn_close = new MyButton();
        btn_close.setBounds(258,4,20,20);
        btn_close.setContentAreaFilled(false);
        btn_close.addActionListener(new ActionListener() {
            @Override
            public synchronized void actionPerformed(ActionEvent e) {
                confirmExit();
            }
        });
        mainPanel.add(btn_close);

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("我的好友");
        top.add(new DefaultMutableTreeNode("好友"));
        top.add(new DefaultMutableTreeNode("好友"));
        top.add(new DefaultMutableTreeNode("好友"));

        tree = new JTree(top);
        tree.setRowHeight(30);
        tree.setBounds(6,180,270,450);
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setClosedIcon(ImageManager.getIcon("MainIcon/treeNode.png"));
        renderer.setOpenIcon(ImageManager.getIcon("MainIcon/treeNode1.png"));
        renderer.setFont(new Font("微软雅黑",Font.PLAIN,22));
        tree.setCellRenderer(renderer);
        mainPanel.add(tree);

        popupMenu = new JPopupMenu();
        Font font = new Font("微软雅黑",Font.PLAIN,15);
        add = new JMenuItem("添加好友");
        add.setFont(font);
        del = new JMenuItem("删除好友");
        del.setFont(font);
        detail = new JMenuItem("好友资料");
        detail.setFont(font);
        popupMenu.add(add);
        popupMenu.add(del);
        popupMenu.addSeparator();
        popupMenu.add(detail);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = MyOptionPane.showInputDialog(MainFrame.this,"查找好友","请输入好友QQ号");
                if(id!=null&&!id.isEmpty()){
                    MyOptionPane.showMessageDialog(MainFrame.this,"加好友信息已发送，请等待对方同意！","提示");
                }
            }
        });
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MyOptionPane.showConfirmDialog(MainFrame.this,"删除好友","您真要删除该好友吗？","是","否")==JOptionPane.OK_OPTION){
                    MyOptionPane.showMessageDialog(MainFrame.this,"该好友已经删除！","提示");
                }
            }
        });

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                if (path == null) {
                    return;
                }
                tree.setSelectionPath(path);

                if (e.getButton() == 3) {
                    popupMenu.show(tree, e.getX(), e.getY());
                }
            }
        });

        lblNick = new JLabel();
        lblNick.setBounds(77,40,58,25);
        lblNick.setOpaque(false);
        lblNick.setForeground(Color.white);
        lblNick.setFont(new Font("微软雅黑",Font.BOLD,14));
        mainPanel.add(lblNick);

        lblSign = new JLabel();
        lblSign.setBounds(77,68,138,12);
        lblSign.setOpaque(false);
        lblSign.setForeground(Color.white);
        lblSign.setFont(new Font("微软雅黑",Font.PLAIN,12));
        mainPanel.add(lblSign);


        mainPanel.setLayout(new BorderLayout());
        mainPanel.addMouseMotionListener(new MouseDragListener(mainPanel,this));
        getContentPane().add(mainPanel,BorderLayout.CENTER);

        initTray();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setType(JFrame.Type.UTILITY);
        setLocationRelativeTo(null);
    }

    public void setNickName(String nickName){
        lblNick.setToolTipText(nickName);
        lblNick.setText(StringUtil.maxlen(nickName,3));
    }

    public void setSign(String sign){
        lblSign.setToolTipText(sign);
        lblSign.setText(StringUtil.maxlen(sign,10));
    }


    private void initTray(){
        if(SystemTray.isSupported()){
            SystemTray tray = SystemTray.getSystemTray();
            Image image = ImageManager.getImage("MainIcon/tray.png");
            trayIcon = new TrayIcon(image,"QQ - Swing QQ");
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount()==2){
                        setVisible(true);
                    }
                }
            });
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }

        }
    }

    private void confirmExit(){
        //确认是否退出
        if(MyOptionPane.showConfirmDialog(this,"确认退出","您是否要退出QQ程序？","是","否")==0){
            if(SystemTray.isSupported()&&trayIcon!=null){
                SystemTray.getSystemTray().remove(trayIcon);
            }
            dispose();
        }
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setNickName("剑圣");
                mainFrame.setSign("无极之道：我的剑就是你的剑！");
                mainFrame.setVisible(true);
            }
        });
    }
}

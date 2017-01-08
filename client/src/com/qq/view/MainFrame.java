package com.qq.view;


import com.qq.ClientApplication;
import com.qq.bean.Message;
import com.qq.bean.MessageType;
import com.qq.bean.UserInfo;
import com.qq.util.Audio;
import com.qq.util.ImageManager;
import com.qq.ui.BackgroundPanel;
import com.qq.ui.MyButton;
import com.qq.ui.MyOptionPane;
import com.qq.ui.listener.MouseDragListener;
import com.qq.util.StringUtil;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private UserInfo userInfo;

    private DefaultMutableTreeNode top;
    private List<UserInfo> friends;
    private DefaultMutableTreeNode selectedNode;
    private Map<Integer,ChatFrame> windowMap = new ConcurrentHashMap<>();

    public MainFrame(UserInfo userInfo){
        this();
        this.userInfo = userInfo;
        pollFriends();
    }


    public MainFrame(){
        setAlwaysOnTop(true);
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

        top = new DefaultMutableTreeNode("我的好友");

        tree = new JTree(top);
        tree.setRowHeight(30);
        tree.setBounds(6,180,270,450);
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setClosedIcon(ImageManager.getIcon("MainIcon/treeNode.png"));
        renderer.setOpenIcon(ImageManager.getIcon("MainIcon/treeNode1.png"));
        renderer.setFont(new Font("微软雅黑",Font.PLAIN,22));
        tree.setCellRenderer(renderer);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            }
        });
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

            @Override
            public void mouseClicked(MouseEvent e) {
                if(!trayMessageList.isEmpty()){
                    //托盘双击有待处理的消息，先处理消息。
                    return;
                }
                if(e.getClickCount() == 2){
                    if(selectedNode!=null&&selectedNode!=top){
                        int index = top.getIndex(selectedNode);
                        final UserInfo userInfo = friends.get(index);
                        if(userInfo==null){
                            return;
                        }
                        System.out.println("跟好友"+userInfo.getNickname()+"聊天.");
                        ChatFrame window = windowMap.get(userInfo.getId());
                        if(window == null){
                            EventQueue.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    ChatFrame chatFrame = new ChatFrame();
                                    chatFrame.setUserInfo(userInfo);
                                    chatFrame.setSelf(MainFrame.this.userInfo);
                                    chatFrame.setVisible(true);
                                    windowMap.put(userInfo.getId(),chatFrame);
                                }
                            });
                        }else{
                            window.setVisible(true);
                        }
                    }
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


        ClientApplication.setMainFrame(this);
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

    private List<Message> trayMessageList = new LinkedList<>();

    public void onMessage(Message message){
        UserInfo userInfo = message.getFrom();
        ChatFrame window = windowMap.get(userInfo.getId());
        if(window!=null){
            window.appendText(message);
        }else{
            //此时托盘闪烁，然后播放MSG声音。
            trayMessageList.add(message); //将待收的消息放到一条队列中，每次双击托盘图标的时候，都显示一条
            Audio.playAsync(Audio.MSG);
            trayShake();
        }
    }

    private void trayShake(){

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
    private static void expandTree(JTree tree) {    //展开jtree下的所有节点
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        expandAll(tree, new TreePath(root), true);
    }
    private static void expandAll(JTree tree, TreePath parent, boolean expand) {

        //Traverse children
        TreeNode node = (TreeNode) parent.
                getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements(); ) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path, expand);
            }
        }

        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
    }


    @SuppressWarnings("unchecked")
    private void pollFriends(){
        //发送一条poll的消息表示需要所有的好友.
        Message req = new Message(MessageType.POLL);
        req.setFrom(userInfo);
        sendOrAlert(req);
        Message res = awaitOrAlert();
        switch (res.getType()){
            case SUCCESS:
                top.removeAllChildren();
                friends = (List<UserInfo>) res.getData();
                for(UserInfo friend:friends){
                    top.add(new DefaultMutableTreeNode(friend.getNickname()));
                }
                expandTree(tree);
                break;
            case ERROR:
                MyOptionPane.showMessageDialog(this,"获取好友信息失败："+res.getContent(),"提示");break;
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

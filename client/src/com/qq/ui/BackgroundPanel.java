package com.qq.ui;

import com.qq.util.ImageManager;

import javax.swing.*;
import java.awt.*;



public class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private String knight ; //要加载的图片资源的文件名

    private ImageIcon icon;

    public String getKnight() {
        return knight;
    }

    public void setKnight(String knight) {
        this.knight = knight;
    }

    public BackgroundPanel(String name){
        knight= "Chat/"+name+".png";
        this.icon = new ImageIcon();

        this.icon.setImage(ImageManager.getImage(knight));

    }
    //重写绘制组件方法
    public void paintComponent(Graphics g)
    {
        int x = 0,y = 0;
        //绘制窗口
        g.drawImage(icon.getImage(),x,y,icon.getIconWidth(),icon.getIconHeight(),this);
    }
}

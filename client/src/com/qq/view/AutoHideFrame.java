package com.qq.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 类似QQ的停靠在桌面边缘时自动隐藏
 */
public abstract class AutoHideFrame extends JFrame {
    private Rectangle rect;
    private int frameLeft;// 窗体离屏幕左边的距离
    private int frameRight;// 窗体离屏幕右边的距离；
    private int frameTop;// 窗体离屏幕顶部的距离
    private int frameWidth; // 窗体的宽
    private int frameHeight;    // 窗体的高

    private int screenXX;// 屏幕的宽度；
    private Point point;    // 鼠标在窗体的位置

    private Timer timer = new Timer(50, new ActionListener() {
        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            if(!isShowing()){
                return;
            }
            frameLeft = getLocationOnScreen().x;
            frameTop = getLocationOnScreen().y;
            frameWidth = getWidth();
            frameHeight = getHeight();
            screenXX = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
            frameRight =screenXX- frameLeft - frameWidth;

            // 获取窗体的轮廓
            rect = new Rectangle(0, 0, frameWidth, frameHeight);
            // 获取鼠标在窗体的位置
            point =getMousePosition();

            if (frameLeft < 0 && isPtInRect(rect, point)) {
                setLocation(0, frameTop); // 隐藏在左边，鼠标指到后显示窗体；
            } else if (frameLeft > -5 && frameLeft < 5 && !(isPtInRect(rect, point))) {
                setLocation(frameLeft - frameWidth + 1, frameTop); // 窗体移到左边边缘隐藏到左边；
            } else if ((frameTop < 0 && frameLeft < 0) && isPtInRect(rect, point)) {// 窗体在左上角；
                setLocation(0, 0);// 窗口隐藏了，鼠标指到他，就显示出来；
            } else if ((frameTop > -5 && frameTop < 5) && (frameLeft > -5 && frameLeft < 5) && !(isPtInRect(rect, point))) {
                // 当窗体的上边框与屏幕的顶端的距离小于5时 ，
                // 并且鼠标不再窗体上将窗体隐藏到屏幕的顶端
                setLocation(frameLeft - frameWidth + 1, 1);
            } else if ((frameTop < 0) && isPtInRect(rect, point)) {
                setLocation(frameLeft, 0);// 窗口隐藏了，鼠标指到他，就显示出来；
            } else if (frameTop > -5 && frameTop < 5 && !(isPtInRect(rect, point))) {
                // 当窗体的上边框与屏幕的顶端的距离小于5时 ，
                // 并且鼠标不再窗体上将窗体隐藏到屏幕的顶端
                setLocation(frameLeft, 1 - frameHeight);
            } else if (frameRight < 0 && isPtInRect(rect, point)) {
                setLocation(screenXX - frameWidth + 1, frameTop);// 隐藏在右边，鼠标指到后显示；
            } else if (frameRight > -5 && frameRight < 5 && !(isPtInRect(rect, point))) {
                setLocation(screenXX - 1, frameTop); // 窗体移到屏幕右边边缘隐藏到右边；
            } else if (frameRight < 0 && frameTop < 0 && isPtInRect(rect, point)) {// 窗体在右上角；
                setLocation(screenXX - frameWidth + 1, 0);// 隐藏在右边，鼠标指到后显示；
            } else if ((frameRight > -5 && frameRight < 5) && (frameTop > -5 && frameTop < 5) && !(isPtInRect(rect, point))) {
                setLocation(screenXX - 1, 1); // 窗体移到屏幕右边边缘隐藏到右边；
            }
        }
    });
    private int xx, yy;
    private boolean isDraging = false;


    protected AutoHideFrame(){
        timer.start();

        setAlwaysOnTop(true);
        addMouseListener(new MouseAdapter() {
            public synchronized void mousePressed(MouseEvent e) {
                isDraging = true;
                xx = e.getX();
                yy = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                isDraging = false;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public synchronized void mouseDragged(MouseEvent e) {
                if (isDraging) {
                    int left = getLocation().x;
                    int top = getLocation().y;
                    setLocation(left + e.getX() - xx, top + e.getY() - yy);
                    repaint();
                }
            }
        });

    }

    @Override
    public void dispose() {
        timer.stop();
        super.dispose();
    }

    private boolean isPtInRect(Rectangle rect, Point point) {
        if (rect != null && point != null) {
            int x0 = rect.x;
            int y0 = rect.y;
            int x1 = rect.width;
            int y1 = rect.height;
            int x = point.x;
            int y = point.y;

            return x >= x0 && x < x1 && y >= y0 && y < y1;
        }
        return false;
    }

}

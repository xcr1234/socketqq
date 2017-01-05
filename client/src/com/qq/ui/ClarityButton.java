package com.qq.ui;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

public class ClarityButton extends JComponent {
    Font f = new Font("黑体", 30, 20);
    boolean isChange;
    MouseListener ml;
    String label;
    Image i;
    Color c;

    public ClarityButton(String buttonShow) {
        label = buttonShow;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        this.setVisible(true);
        c = new Color(0);
    }

    public void setColor(Color c) {
        this.c = c;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFont(Font f) {
        this.f = f;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.draw3DRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, !isChange);
    }

    public void addMouseListener(MouseListener ml) {
        this.ml = ml;
    }

    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_ENTERED) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (ml != null) ml.mouseEntered(e);
        }
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            isChange = true;
            this.repaint();
            if (ml != null)
                ml.mousePressed(e);
        }
        if (e.getID() == MouseEvent.MOUSE_RELEASED) {
            isChange = false;
            this.repaint();
            if (ml != null)
                ml.mouseReleased(e);
        }
        if (e.getID() == MouseEvent.MOUSE_CLICKED) {
            if (ml != null) ml.mouseClicked(e);
        }
    }
}
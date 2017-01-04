package com.qq.ui.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 最小化
 */
public class MinActionListener implements ActionListener{

    private JFrame jFrame;

    public MinActionListener(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.jFrame.setExtendedState(JFrame.ICONIFIED);
    }
}

package com.qq.ui.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 关闭
 */
public class CloseActionListener implements ActionListener{

    private Window window;

    public CloseActionListener(Window window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.dispose();
    }
}

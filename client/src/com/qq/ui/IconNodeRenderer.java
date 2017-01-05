/**
 * @Title:df.java
 * @Package:��дJTree
 * @Description:TODO(�������ļ���ʲô)
 * @author:  ShiLuoDeQin 
 * @date:2013-12-26����12:29:54
 * @version V1.0
 */

package com.qq.ui;

/**
 * @ClassName:df
 * @author Administrator
 * @Description:TODO(��������������)
 * @date 2013-12-26 ����12:29:54
 *
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class IconNodeRenderer extends DefaultTreeCellRenderer// �̳и���
{
	// ��д�÷���
	IconNodeRenderer	main;
	private String text1 = "";
	private String text2 = "";
	private String text3 = "";
	private Icon touxiang = null;
	private boolean isSelect;
	private boolean isleaf;
	String beijing = "C:\\Users\\Administrator\\Desktop\\beijing.png";
	Image Ibeijing = Toolkit.getDefaultToolkit().getImage(beijing);
	public IconNodeRenderer(){
		main=this;
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				text1="fuck";
				main.repaint();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus); // ���ø���ĸ÷���
		Icon icon = ((IconNode) value).getIcon();// �ӽڵ��ȡͼƬ
		this.touxiang = icon;
		String[] txt = ((IconNode) value).getText(); // �ӽڵ��ȡ�ı�
		setIcon(icon);// ����ͼƬ
		setText(text1);// �����ı�
		// this.text1=txt;
		this.text1 = txt[0];
		this.text2 = txt[1];
		this.text3 = txt[2];
		this.setPreferredSize(new Dimension(280, 55));
		isSelect = sel;
		isleaf = leaf;
		return this;
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (isleaf) {
			if (isSelect) {
				g.setColor(Color.LIGHT_GRAY);
				g.fill3DRect(0, 0, 290, 50, true);
				if (touxiang != null)
					touxiang.paintIcon(this, g, 2, 2);
				// g.drawImage( touxiang., 2, 2, this);
				g.setColor(Color.black);
				g.setFont(new Font("����", 20, 20));
				g.drawString(this.text1, 50, 20);

				g.setColor(Color.blue);
				g.setFont(new Font("����", 17, 15));
				g.drawString(this.text2, 50, 35);

				g.setColor(Color.BLACK);
				g.setFont(new Font("����", 12, 11));
				g.drawString(this.text3, 50, 50);
			}
			else {
				// g.drawImage(Ibeijing, 0, 0, null);
				g.setColor(this.getBackground());
				
				g.fill3DRect(0, 0, 290, 50, true);
				if(touxiang!=null)
				touxiang.paintIcon(this, g, 2, 2);
				// g.drawImage( touxiang., 2, 2, this);
				g.setColor(Color.black);
				g.setFont(new Font("����", 20, 20));
				g.drawString(this.text1, 50, 20);

				g.setColor(Color.blue);
				g.setFont(new Font("����", 17, 15));
				g.drawString(this.text2, 50, 35);

				g.setColor(Color.BLACK);
				g.setFont(new Font("����", 12, 11));
				g.drawString(this.text3, 50, 50);
			}
		} else {
			 {
				// g.drawImage(Ibeijing, 0, 0, null);
				g.setColor(Color.pink);
				g.fill3DRect(0, 0, 290, 50, true);
				g.setColor(Color.red);
				g.setFont(new Font("����", 20, 25));
				g.drawString(this.text1, 50, 35);
				
				
			}

		}

	}
}




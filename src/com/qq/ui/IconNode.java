package com.qq.ui;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

//����ڵ���
public class IconNode extends DefaultMutableTreeNode {
	protected Icon icon;
	protected String txt1 = " ", txt2 = " ", txt3 = " ";
	protected String QQ;
	// ֻ�����ı��Ľڵ㹹��
	public IconNode(String txt) {
		super();
		this.txt1 = txt;
	}

	// �����ı���ͼƬ�Ľڵ㹹��
	public IconNode(Icon icon, String txt) {
		super();
		this.icon = icon;
		this.txt1 = txt;
	}

	public IconNode(Icon icon, String txt, String txt2) {
		super();
		this.icon = icon;
		this.txt1 = txt;
		this.txt2 = txt2;
	}

	public IconNode(Icon icon, String txt, String txt2, String txt3) {
		super();
		this.icon = icon;
		this.txt1 = txt;
		this.txt2 = txt2;
		this.txt3 = txt3;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setText(String txt) {
		this.txt1 = txt;
	}

	public String[] getText() {
		String txt[] = { "", "", "" };
		txt[0] = txt1;
		txt[1] = txt2;
		txt[2] = txt3;
		return txt;
	}

	public void setText(String txt, String txt2) {
		this.txt1 = txt;
		this.txt2 = txt2;
	}

	public void setText(String txt, String txt2, String txt3) {
		this.txt1 = txt;
		this.txt2 = txt2;
		this.txt3 = txt3;
	}
	
	public void setQQ(String qq) {
		this.QQ = qq;
	}


	public String getQQ() {
		return QQ;
	}

}

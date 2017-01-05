package com.qq.ui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MyTextPane extends JTextPane {

	private StyledDocument doc = null;

	/**
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)
	 */
	private static final long serialVersionUID = 1L;

	public MyTextPane() {
		doc = getStyledDocument();
	}

	public void insert(String str, AttributeSet attrSet) {
		Document doc = getDocument();
		// if(doc.getLength()!=0){
		// str =str+"\n";
		// }
		try {
			doc.insertString(doc.getLength(), str, attrSet);
		} catch (BadLocationException e) {
			System.out.println("BadLocationException:" + e);
		}
	}

	public void setDocs(String str, Color col, String font, boolean bold,
			boolean italic, boolean underline, int fontSize) {
		// SimpleAttributeSet attrSet = new SimpleAttributeSet();
		// StyleConstants.setForeground(attrSet, col); // ��ɫ
		// if (bold == true) {
		// StyleConstants.setBold(attrSet, true); // ����
		// }
		// if (Italic == true) {
		// StyleConstants.setItalic(attrSet, true); // б��
		// }
		// if (Underline == true) {
		// StyleConstants.setUnderline(attrSet, true); // �»���
		// }
		// StyleConstants.setFontFamily(attrSet, font); // ����
		// StyleConstants.setFontSize(attrSet, fontSize); // �����С
		// insert(str, attrSet);
		insertText(str, col, font, bold, italic, underline, fontSize);
	}

	public void insertImage(ImageIcon im) {
		doc = getStyledDocument();
		this.setCaretPosition(doc.getLength()); // ���ò���λ��
		insertIcon(im); // ����ͼƬ
		insertText(new FontAttrib()); // ���������Ի���

	}

	public void insertText(String str, Color col, String font, boolean bold,
			boolean italic, boolean underline, int fontSize) {
		FontAttrib att = new FontAttrib();

		att.setText(str);
		att.setColor(col);
		att.setName(font);
		att.setSize(fontSize);
		att.Underline = underline;
		att.BOLD = bold;
		att.Underline = underline;

		try { // �����ı�
			doc.insertString(doc.getLength(), att.getText(), att.getAttrSet());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

	}

	private void insertText(FontAttrib att) {
		att.setText("");
		att.setColor(Color.black);
		att.setName("����");
		att.setSize(20);
		att.Underline = false;
		att.BOLD = false;
		att.Underline = false;

		try { // �����ı�
			doc.insertString(doc.getLength(), att.getText() + "\n",
					att.getAttrSet());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private class FontAttrib {
		public boolean BOLD = false; // ����
		public boolean ITALIC = false; // б��
		public boolean Underline = false;// �»���
		private SimpleAttributeSet attrSet = null; // ���Լ�
		private String text = null; // �������ݣ�����
		private String name = null; // Ҫ������ı�����������
		private int size = 0; // ��ʽ���ֺ�
		private Color color = null; // ������ɫ

		public FontAttrib() {
		}

		public SimpleAttributeSet getAttrSet() {
			attrSet = new SimpleAttributeSet();

			if (name != null) {// ����
				StyleConstants.setFontFamily(attrSet, name);
			}
			if (BOLD) {
				StyleConstants.setBold(attrSet, true);
			} else {
				StyleConstants.setBold(attrSet, false);
			}
			if (Underline) {
				StyleConstants.setUnderline(attrSet, true);
			} else {
				StyleConstants.setUnderline(attrSet, false);
			}
			if (ITALIC) {
				StyleConstants.setItalic(attrSet, true);
			} else {
				StyleConstants.setItalic(attrSet, false);
			}
			if (color != null) {
				StyleConstants.setForeground(attrSet, color);
			}
			StyleConstants.setFontSize(attrSet, size);
			return attrSet;
		}

		public void setAttrSet(SimpleAttributeSet attrSet) {// ����
			this.attrSet = attrSet;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {// ����
			this.text = text;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {// ������ɫ
			this.color = color;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

	}

}

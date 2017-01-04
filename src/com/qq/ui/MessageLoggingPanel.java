package com.qq.ui;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class MessageLoggingPanel extends JPanel {

	/**
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)
	 */
	private static final long serialVersionUID = 1L;
	public MyTextPane  tp_messagelogging = new MyTextPane();
	public JScrollPane spanel;
	public MessageLoggingPanel(){
		this.setVisible(false);
		setBounds(446,146,362, 342);	
		this.setLayout(null);
		tp_messagelogging.setOpaque(false);   //͸��
		tp_messagelogging.setEditable(false);  //���ɱ༭

		tp_messagelogging.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent mouseEvent){
				tp_messagelogging.setCursor(new Cursor(Cursor.TEXT_CURSOR)); //������Text�����Ϊ�ı�����ָ��
			}
			public void mouseExited(MouseEvent mouseEvent){
				tp_messagelogging.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //����뿪Text����ָ�Ĭ����̬
			}
		});
		spanel=new JScrollPane(tp_messagelogging);
		spanel.setOpaque(false);  //͸��
		spanel.getViewport().setOpaque(false);//͸��
		spanel.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		spanel.setVisible(true);
		spanel.setBounds(0,0,365, 413);
		add(spanel);
	}

}

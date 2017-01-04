/**
 * @Title:CombListRenderer.java
 * @Package:com.client.view
 * @Description:TODO(�������ļ���ʲô)
 * @author:  ShiLuoDeQin 
 * @date:2013-12-29����05:08:42
 * @version V1.0
 */

package com.qq.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


/**
 * @ClassName:CombListRenderer
 * @author Administrator
 * @Description:TODO(��������������)
 * @date 2013-12-29 ����05:08:42
 *
 */

public class CombListRenderer extends JLabel implements ListCellRenderer{

	/* (non-Javadoc)
	 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	@Override
	/**
	 * ��дComblist��Render
	 */
	public Component getListCellRendererComponent(JList list,Object obj,
			int row,boolean sel,boolean hasFocus)
	 {
		// TODO Auto-generated method stub
		Object[] cell=(Object[])obj;
		  setIcon((Icon)cell[0]);//����ͼƬ
		  setText((cell[1].toString()));//�����ı�
		//  setToolTipText(cell[2].toString());//������ʾ�ı�
		//  setBorder(new LineBorder(Color.gray));//���Ʊ߿�
		  
		  
		  if(sel)//���ѡ��
		  {
		   setForeground(Color.green);//��ǰ��ɫΪ��ɫ
		   setBackground(Color.blue);
		   repaint();
		   
		  }
		  else//ûѡ��
		  {
		   setForeground(list.getForeground());//��ǰ��ɫΪĬ��
		   setBackground(list.getBackground());
		  }
		  return this; 
		 }
	

}

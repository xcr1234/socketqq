
package com.qq.ui;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class ColorConvertOp {


	public ColorConvertOp(){
		
	}
	static public  ImageIcon getGrayPicture(String path) throws IOException  {
		BufferedImage originalPic;
	//	originalPic=ImageIO.read(getClass().getResource(path));//��ȡͼƬ�����BufferedImage��
		originalPic=ImageIO.read(new File(path));
		int imageWideth=originalPic.getWidth();
		int imageHeight=originalPic.getHeight();
		BufferedImage newPic=new BufferedImage(imageWideth,imageHeight,BufferedImage.TYPE_INT_ARGB);
		
		for(int i=0;i<imageWideth;i++)
		for(int j=0;j<imageHeight;j++){
			int rgb=originalPic.getRGB(i, j);
			int A=0,R=0,G=0,B=0;
			A = (rgb>> 24)& 0xff ;  
            R = (rgb >> 16)& 0xff ;  
            G = (rgb >> 8)& 0xff ;  
            B = rgb & 0xff;  
                    
//            R=(int)(0.1 * R);
//            G=(int)(0.4 * G);
//            B=(int)(0.4 * B);
         //   A=(int)(0.5*A); 
            int avg=(R+G+B)/3;
         //   A=(int) (0.5*A);
           R=avg;
           G=avg;
           B=avg;
           A = (A& 0xff)<<24 ;  
           R = (R & 0xff)<<16 ;  
           G = (G& 0xff)<<8 ;  
           B = rgb & 0xff; 
           int gray = R+G+B+A;
//           System.out.println("A="+A+"R="+R+"G="+G+"B="+B);	
//			int gray = (int) (0.299 *((rgb >>24 )) + (0.587 *(rgb >>16 )) + (0.114 *(rgb>>8	 	  )));	
//			Gray = 0.299 * red + 0.587 * green + 0.114 * blue;
//            int gray = (int) (0.3*((rgb & 0xff0000 ) >> 16) + 0.59*((rgb & 0xff00 ) >>8) + 0.11*((rgb & 0xff )));
			newPic.setRGB(i, j, gray);
				
		}
		ImageIcon newIcon=new ImageIcon(newPic);
		
		return newIcon;
		
	}
}

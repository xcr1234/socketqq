package com.qq.manager;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageManager {
    private final static String path;

    static {
        URL url = ImageManager.class.getResource("../image");
        if(url == null){
            throw new RuntimeException("找不到image!");
        }
        path = url.getFile();
    }

    public static String getPath(String img){
        return path + "/" + img;
    }

    public static File getFile(String img){
        return new File(path,img);
    }

    public static BufferedImage getImage(String img) {
        File file = getFile(img);
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            return null;
        }
    }

    public static ImageIcon getIcon(String img) {
        Image image = getImage(img);
        ImageIcon imageIcon = new ImageIcon();
        imageIcon.setImage(image);
        return imageIcon;
    }
}

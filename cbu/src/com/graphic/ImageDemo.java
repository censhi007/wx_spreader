package com.graphic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.util.Util;

public class ImageDemo {

    public void binaryImage() throws IOException{
	File file = new File(Util.getClassPath()+"/300.jpg");
	BufferedImage image = ImageIO.read(file);
	
	int width = image.getWidth();
	int height = image.getHeight();
	
	BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
	for(int i= 0 ; i < width ; i++){
	    for(int j = 0 ; j < height; j++){
		int rgb = image.getRGB(i, j);
		grayImage.setRGB(i, j, rgb);
	    }
	}
	
	File newFile = new File(Util.getClassPath()+"/300_bin.jpg");
	ImageIO.write(grayImage, "jpg", newFile);
    }
    
    public void grayImage() throws IOException{
	File file = new File(Util.getClassPath()+"/300.jpg");
	BufferedImage image = ImageIO.read(file);
	
	int width = image.getWidth();
	int height = image.getHeight();
	
	BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
	for(int i= 0 ; i < width ; i++){
	    for(int j = 0 ; j < height; j++){
		int rgb = image.getRGB(i, j);
		grayImage.setRGB(i, j, rgb);
	    }
	}
	
	File newFile = new File(Util.getClassPath()+"/300_grey.jpg");
	ImageIO.write(grayImage, "jpg", newFile);
    }
    
    public static void main(String[] args) throws IOException {
	ImageDemo demo = new ImageDemo();
	demo.binaryImage();
	demo.grayImage();
    }

}

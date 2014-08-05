package com.graphic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
/**
 * 图片处理工具
 * @author BUJUN
 *
 */
public class ImageUtil {
	/**
	 * 将输入流中的图片文件进行灰度化处理
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage grayImage(InputStream is) throws IOException{
		BufferedImage image = ImageIO.read(is);
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		for(int i= 0 ; i < width ; i++){
		    for(int j = 0 ; j < height; j++){
			int rgb = image.getRGB(i, j);
			grayImage.setRGB(i, j, rgb);
		    }
		}

		return grayImage;
	}
	/**
	 *将图片文件pth，进行灰度化处理
	 * @param pth
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage grayImage(String pth) throws IOException{
		BufferedImage image = ImageIO.read(new File(pth));
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		for(int i= 0 ; i < width ; i++){
		    for(int j = 0 ; j < height; j++){
			int rgb = image.getRGB(i, j);
			grayImage.setRGB(i, j, rgb);
		    }
		}

		return grayImage;
	}
	/**
	 * 对输入流中的图片进行二值化处理
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage binaryImage(InputStream is) throws IOException{
		BufferedImage image = ImageIO.read(is);
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage binImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		for(int i= 0 ; i < width ; i++){
		    for(int j = 0 ; j < height; j++){
			int rgb = image.getRGB(i, j);
			binImage.setRGB(i, j, rgb);
		    }
		}
		
		return binImage;
	}
	
	/**
	 * 对图片pth文件进行二值化处理
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage binaryImage(String pth) throws IOException{
		BufferedImage image = ImageIO.read(new File(pth));
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage binImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		for(int i= 0 ; i < width ; i++){
		    for(int j = 0 ; j < height; j++){
			int rgb = image.getRGB(i, j);
			binImage.setRGB(i, j, rgb);
		    }
		}
		
		return binImage;
	}
}

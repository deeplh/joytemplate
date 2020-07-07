package com.keepjoy.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;


/**
 * 图片处理工具
 * @author xuyc
 *
 */
public class ImageUtil {

	/**
	 * 把图片印刷到图片上
	 * 
	 * @param pressImg --
	 *            水印文件
	 * @param targetImg --
	 *            目标文件
	 * @param x
	 *            --x坐标
	 * @param y
	 *            --y坐标
	 */
	public final static void pressImage(String pressImg, String targetImg, int x, int y) {

		try {
			//目标文件
			File _file = new File(targetImg);
			pressImage(pressImg, _file, x, y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把图片印刷到图片上
	 * 
	 * @param pressImg --
	 *            水印文件
	 *            目标文件
	 * @param x
	 *            --x坐标
	 * @param y
	 *            --y坐标
	 * @throws IOException 
	 */
	public final static void pressImage(String pressImg, File targetFile, int x, int y) throws IOException {
		FileOutputStream out =null;
		try {
			//目标文件
			Image src = ImageIO.read(targetFile);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			//水印文件
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			//g.drawImage(src_biao, (wideth - wideth_biao) / 2 - x, (height - height_biao) / 2 - y, wideth_biao, height_biao, null);
			g.drawImage(src_biao, wideth - wideth_biao - x, height - height_biao - y, wideth_biao, height_biao, null);
			//水印文件结束
			g.dispose();
			out = new FileOutputStream(targetFile);
			ImageIO.write(image, FilenameUtils.getExtension(targetFile.getName()), out); 
		} finally{
			if(null!=out)out.close();
		}
	}

	/**
	 * 打印文字水印图片
	 * 
	 * @param pressText
	 *            --文字
	 * @param targetImg --
	 *            目标图片
	 * @param fontName --
	 *            字体名
	 * @param fontStyle --
	 *            字体样式
	 * @param color --
	 *            字体颜色
	 * @param fontSize --
	 *            字体大小
	 * @param x --
	 *            偏移量
	 * @param y
	 * @throws IOException 
	 */

	public static void pressText(String pressText, String targetImg,
			String fontName, int fontStyle, int color, int fontSize, int x, int y) throws IOException {
		FileOutputStream out =null;
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			g.setColor(Color.RED);
			g.setFont(new Font(fontName, fontStyle, fontSize));

			g.drawString(pressText, wideth - fontSize - x, height - fontSize / 2 - y);
			g.dispose();
			out = new FileOutputStream(targetImg);
			ImageIO.write(image, FilenameUtils.getExtension(targetImg), out); 
			out.close();
		} finally{
			if(null!=out)out.close();
		}
	}

	/**
	 * 画图
	 * @param out
	 * @param src
	 * @param imageFile
	 * @param finalWidth
	 * @param finalHeight
	 * @throws IOException
	 */
	public static void drawImg(File imageFile, int finalWidth,int finalHeight) throws IOException{
		BufferedImage image = new BufferedImage(finalWidth,finalHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		g.drawImage(ImageIO.read(imageFile), 0, 0, finalWidth, finalHeight, null);
		g.dispose();
		
		
		ImageIO.write(image, FilenameUtils.getExtension(imageFile.getName()), new FileOutputStream(imageFile)); 
	}

	/**
	 * 图片等比例缩放(按宽高)

	 * @param imgWidth 宽度
	 * @param imgHeight 高度
	 * @throws Exception 
	 */
	public static int[] changeImgSize(File srcFile,File targetFile, int imgWidth,int imgHeight) throws Exception{
		//等比例缩放
		Image src = ImageIO.read(srcFile);
		double sourceWidth = (double) src.getWidth(null); // 得到源图宽
		double sourceHeight = (double) src.getHeight(null); // 得到源图长

		int finalWidth=0;
		int finalHeight=0;

		//宽大于0,高等于0
		if(imgWidth>0 && imgHeight==0){
			double imgbit = imgWidth/sourceWidth;
			finalWidth=imgWidth;
			finalHeight = (int) (sourceHeight*imgbit);
		}else if(imgWidth==0 && imgHeight>0){
			double imgbit = imgHeight/sourceHeight;
			finalHeight=imgHeight;
			finalWidth = (int) (sourceWidth*imgbit);
		}else if(imgWidth>0 && imgHeight>0){
			finalWidth=imgWidth;
			finalHeight=imgHeight;
		}else{
			throw new IllegalArgumentException("imgWidth or imgHeight error");
		}
		if(!targetFile.exists()){
			FileUtils.copyFile(srcFile, targetFile);
		}
		drawImg(targetFile,finalWidth,finalHeight);
		int[] wh={finalWidth,finalHeight};
		return wh;
	}

	/**
	 * 按比例缩放(按比例)

	 * @param rate
	 * @throws Exception
	 */
	public static void changeImgSize(File srcFile,File targetFile,float rate) throws Exception{
		FileOutputStream out =null;
		
		try{
			//等比例缩放
			Image src = ImageIO.read(srcFile);

			int finalWidth=(int) (src.getWidth(null)*rate);
			int finalHeight=(int) (src.getHeight(null)*rate);

			if(!targetFile.exists()){
				FileUtils.copyFile(srcFile, targetFile);
			}
			drawImg(targetFile,finalWidth,finalHeight);
		}finally{
			if(null!=out)out.close();
		}
	}


	/*****************test
	 * @throws Exception ***********/
	public static void main(String[] args) throws Exception {
//		changeImgSize(new File("/Users/deep/Downloads/ttt.jpg"),new File("/Users/deep/Downloads/ttt_abb.jpg"),500,0);
//		changeImgSize(new File("D:\\test\\1.jpg"),new File("D:\\test\\1_2.jpg"),200,0);
	}

}

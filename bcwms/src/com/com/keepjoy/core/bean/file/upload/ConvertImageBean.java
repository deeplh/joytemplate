package com.keepjoy.core.bean.file.upload;

import java.io.File;

/**
 * 转换后的图片
 * @author deep
 */
public class ConvertImageBean {

	private String imageName;//图片名称
	private File imageFile;//图片文件引用
	
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public File getImageFile() {
		return imageFile;
	}
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}
	
	
	
}

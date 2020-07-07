package com.keepjoy.core.bean.file.upload;

import java.io.File;
import java.util.List;

public class FileAttrBean {

	private String suffix;//后缀名称-小写
	private String sourceName;//文件的原始名称
	private String newName;//新的文件名称
	private String absolutePath;//文件上传的绝对路径
	private String relativePath;//文件上传的相对路径
	private String orientation;//文件方向
	private Long size;//文件大小
	private File newFile;//上传后的新文件引用
	private File pdfFile;//转换后的pdf文件
	private Integer width;
	private Integer height;
	private List<ConvertImageBean> convertImageNameList;//转换后的图片文件名称
	
	
	
	
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public File getNewFile() {
		return newFile;
	}
	public void setNewFile(File newFile) {
		this.newFile = newFile;
	}
	public File getPdfFile() {
		return pdfFile;
	}
	public void setPdfFile(File pdfFile) {
		this.pdfFile = pdfFile;
	}
	public List<ConvertImageBean> getConvertImageNameList() {
		return convertImageNameList;
	}
	public void setConvertImageNameList(List<ConvertImageBean> convertImageNameList) {
		this.convertImageNameList = convertImageNameList;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	
	
}

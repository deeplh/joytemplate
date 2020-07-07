package com.keepjoy.core.bean.file.upload;

import java.util.List;

public class UploadFileBean {

	
	private String inputName;//input控件的名称
	private List<FileAttrBean> fileAttrBeanList;
	
	public String getInputName() {
		return inputName;
	}
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
	public List<FileAttrBean> getFileAttrBeanList() {
		return fileAttrBeanList;
	}
	public void setFileAttrBeanList(List<FileAttrBean> fileAttrBeanList) {
		this.fileAttrBeanList = fileAttrBeanList;
	}
	
	
}

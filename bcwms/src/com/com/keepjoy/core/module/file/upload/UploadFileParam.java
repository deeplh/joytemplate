package com.keepjoy.core.module.file.upload;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UploadFileParam {


    private List<MultipartFile> files;

    private String absolutePath;//绝对路径
    private String destFileName;//最终的文件名称
    private Boolean isAutoRename=true;//是否自动重命名
    private Integer permitFileSize=5120;//允许的文件大小,单位为k,默认5120k(5m)
    private String[] permitFileExtensionArray={};//允许的文件格式

    public UploadFileParam(List<MultipartFile> files,String absolutePath) {
        this.absolutePath = absolutePath;
        this.files=files;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }


    public Boolean getAutoRename() {
        return isAutoRename;
    }

    public void setAutoRename(Boolean autoRename) {
        isAutoRename = autoRename;
    }

    public Integer getPermitFileSize() {
        return permitFileSize;
    }

    public void setPermitFileSize(Integer permitFileSize) {
        this.permitFileSize = permitFileSize;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String[] getPermitFileExtensionArray() {
        return permitFileExtensionArray;
    }

    public void setPermitFileExtensionArray(String[] permitFileExtensionArray) {
        this.permitFileExtensionArray = permitFileExtensionArray;
    }

    public String getDestFileName() {
        return destFileName;
    }

    public void setDestFileName(String destFileName) {
        this.destFileName = destFileName;
    }
}

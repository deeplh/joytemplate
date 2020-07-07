package com.keepjoy.core.module.file.upload;

import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.util.DateUtil;
import com.keepjoy.core.util.MyLogUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class KeepJoyUpload {


    private UploadFileParam uploadFileParam;

    public KeepJoyUpload(UploadFileParam uploadFileParam) {
        this.uploadFileParam = uploadFileParam;
    }


    public List<FileAttrBean> doUpload() throws Exception {
        /*******************文件上传************************/
        //获得文件相关上传路径

        MyLogUtil.printlnDebug("upload file folder is [" + uploadFileParam.getAbsolutePath() + "]");


        List<FileAttrBean> files = new ArrayList<FileAttrBean>();
        FileAttrBean fab = null;
        File file = null;

        for (int i = 0; i < uploadFileParam.getFiles().size(); i++) {
            fab = new FileAttrBean();
            files.add(fab);
            file = new File(uploadFileParam.getFiles().get(i).getOriginalFilename());

            //整个文件原始名称(包含后缀)
            String filename = FilenameUtils.getName(file.getName());
            //后缀名称(已经转小写)
            String suffix = FilenameUtils.getExtension(filename);
            if (StringUtils.isEmpty(suffix)) throw new RuntimeException("suffix is null");
            suffix = suffix.toLowerCase();
            //判断允许上传的文件后缀
            if (!isPermitSuffix(uploadFileParam.getPermitFileExtensionArray(), suffix)) {
                String tips = "";
                for (String su : uploadFileParam.getPermitFileExtensionArray()) {
                    tips = tips + " " + su;
                }
                throw new IllegalArgumentException("file suffix[" + filename + "] is wrong!upload is [" + suffix + "],permit is [" + tips + "]");
            }
            //得到最终的文件名称
            String destFileName = null;
            if (StringUtils.isNotEmpty(uploadFileParam.getDestFileName())) {
                destFileName = uploadFileParam.getDestFileName();
            } else {
                if (uploadFileParam.getAutoRename()) {
                    //暂时不使用base64编码新文件名称,因为会出现/,影响正常路径
                    //Base64.encodeBase64String(filename.substring(0, filename.lastIndexOf(".")).getBytes("utf-8"))+"_"+
                    destFileName = DateUtil.dateToStringByDateFormat(new Date(), DateUtil.DATEFORMAT_YYYYMMDDHHMMSSSSSS) + "_" + UUID.randomUUID().toString();
                    //没有后缀名称
                    if (destFileName.indexOf(".") == -1) {
                        destFileName = destFileName + "." + suffix;
                    }
                } else {
                    destFileName = filename;
                    MyLogUtil.printlnDebug("upload file [" + filename + "],new name[" + destFileName + "]  ");

                }
            }
            //判断文件大小是否合法
            if (file.length() > uploadFileParam.getPermitFileSize() * 1024) {
                throw new IllegalArgumentException("permit file size is " + uploadFileParam.getPermitFileSize() + "K(" + uploadFileParam.getPermitFileSize() / 1024 + "M),upload file is " + file.length() / 1024 + "K");
            }
            //生成目标文件
            File folder=new File(uploadFileParam.getAbsolutePath());
            if(!folder.exists()){
                folder.mkdirs();
            }
            File destFile = new File(uploadFileParam.getAbsolutePath(), destFileName);
            destFile.setReadable(true);
            destFile.setExecutable(true);
            destFile.setWritable(true);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
            out.write(uploadFileParam.getFiles().get(i).getBytes());

            fab.setSuffix(suffix);
            fab.setSourceName(filename);
            fab.setNewName(destFileName);
            fab.setNewFile(destFile);
            fab.setAbsolutePath(destFile.getAbsolutePath());
            fab.setSize(file.length());
        }


        return files;

    }


    //判断后缀名是否允许
    private boolean isPermitSuffix(String[] permitFileExtensionArray, String suffix) {
        if (permitFileExtensionArray.length == 0) return true;//什么都不写的时候,表示全都允许
        for (int i = 0; i < permitFileExtensionArray.length; i++) {
            if (suffix.equals(permitFileExtensionArray[i].toLowerCase())) {
                return true;
            }
        }
        return false;
    }


}

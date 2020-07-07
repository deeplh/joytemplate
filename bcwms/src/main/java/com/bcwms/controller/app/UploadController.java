package com.bcwms.controller.app;


import com.bcwms.BcwConstant;
import com.bcwms.factory.CosClientFactory;
import com.bcwms.properties.BcwProperties;
import com.bcwms.service.BcwCommonService;
import com.bcwms.util.CaptureVideoUtil;
import com.bcwms.util.RotateImageUtil;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.module.file.upload.KeepJoyUpload;
import com.keepjoy.core.module.file.upload.UploadFileParam;
import com.keepjoy.core.util.DateUtil;
import com.keepjoy.core.util.ImageUtil;
import com.keepjoy.core.util.UrlUtil;

import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UploadController {


    @RequestMapping(value = "/app/img/upload", method = {RequestMethod.POST})
    public FileAttrBean uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                  @RequestParam(value = "type", required = true) String type,
                                  @RequestParam(value = "orientation", required = false) String orientation) throws Exception {

        String be=BcwConstant.FOLDER_IMG + File.separator + type;
        String path = BcwCommonService.getUploadRootFolder() + be;
        String cosKey=BcwProperties.getUploadFileUrl()+File.separator+be;
        if (BcwConstant.TYPE_EVENT.equals(type)
                || BcwConstant.TYPE_CLOCK.equals(type)) {
            String f=File.separator + DateUtil.dateToStringByDateFormat(BcwCommonService.getNow(), DateUtil.DATEFORMAT_YYYYMM);
            path = path + f;

            cosKey=cosKey+f;
        }

        List<MultipartFile> files = new ArrayList<MultipartFile>();
        files.add(file);
        UploadFileParam uploadFileParam = new UploadFileParam(files, path);
        String[] ars = {"png", "jpg", "jpeg"};
        uploadFileParam.setPermitFileExtensionArray(ars);
        uploadFileParam.setPermitFileSize(1024 * 8);
        KeepJoyUpload kju = new KeepJoyUpload(uploadFileParam);


        List<FileAttrBean> list = kju.doUpload();
        FileAttrBean fab = new FileAttrBean();
        if (list.size() > 0) {

            fab = list.get(0);

            //判断图片方向
            if (StringUtils.isNotEmpty(orientation) && "left".equals(orientation)) {

                BufferedImage des = RotateImageUtil.rotateByClockwise(ImageIO.read(fab.getNewFile()), 90);
                ImageIO.write(des, "jpg", fab.getNewFile());
            }
            cosKey=cosKey+File.separator+fab.getNewName();
            //压缩
            File srcfile = new File(fab.getNewFile().getAbsolutePath());
            int wh[] = ImageUtil.changeImgSize(srcfile, new File(UrlUtil.addAttachUrlBeforeLastDot(fab.getNewFile().getAbsolutePath(), "_" + BcwConstant.ATTACH_MIN_IMG)),
                    0, BcwConstant.ATTACH_MIN_IMG_INT);
            fab.setWidth(wh[0]);
            fab.setHeight(wh[1]);

//			fab.setOrientation(orientation);

            //上传到cos

            // 指定要上传到 COS 上对象键
            // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为 doc1/pic1.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
            PutObjectRequest putObjectRequest = new PutObjectRequest(BcwProperties.getBucketName(), cosKey, fab.getNewFile());
            PutObjectResult putObjectResult = CosClientFactory.getCosClient().putObject(putObjectRequest);

            putObjectRequest = new PutObjectRequest(BcwProperties.getBucketName(), UrlUtil.addAttachUrlBeforeLastDot(cosKey,"_" + BcwConstant.ATTACH_MIN_IMG), fab.getNewFile());
            putObjectResult = CosClientFactory.getCosClient().putObject(putObjectRequest);

        }

        return fab;
    }

    @RequestMapping(value = "/app/video/upload", method = {RequestMethod.POST})
    public List<FileAttrBean> uploadVideo(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                          @RequestParam(value = "type", required = true) String type) throws Exception {

        String be=BcwConstant.FOLDER_VIDEO + File.separator + type;
        String path = BcwCommonService.getUploadRootFolder() + be;


        List<MultipartFile> files = new ArrayList<MultipartFile>();
        files.add(file);
        UploadFileParam uploadFileParam = new UploadFileParam(files, path);
        String[] ars = {"mp4"};
        uploadFileParam.setPermitFileExtensionArray(ars);
        uploadFileParam.setPermitFileSize(1024 * 10);
        KeepJoyUpload kju = new KeepJoyUpload(uploadFileParam);


        List<FileAttrBean> list = kju.doUpload();
        List<FileAttrBean> fabList = new ArrayList<FileAttrBean>();

        if (list.size() > 0) {
            FileAttrBean vfab = list.get(0);

            //上传到cos

            // 指定要上传到 COS 上对象键
            // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为 doc1/pic1.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
            String key = BcwProperties.getUploadFileUrl()+File.separator+be+File.separator+vfab.getNewName();
            PutObjectRequest putObjectRequest = new PutObjectRequest(BcwProperties.getBucketName(), key, vfab.getNewFile());
            PutObjectResult putObjectResult = CosClientFactory.getCosClient().putObject(putObjectRequest);


            String centerPath = BcwConstant.FOLDER_IMG + File.separator + type + File.separator + DateUtil.dateToStringByDateFormat(BcwCommonService.getNow(), DateUtil.DATEFORMAT_YYYYMM);

            String imgRoot = BcwCommonService.getUploadRootFolder() + centerPath;

            List<File> imgs = CaptureVideoUtil.captureVideo(vfab.getAbsolutePath(), imgRoot, FilenameUtils.getBaseName(vfab.getNewName()), 1);

            String urlBegin = BcwCommonService.getUploadRootUrl() + centerPath + File.separator;


            FileAttrBean fab = new FileAttrBean();
            fab.setNewName(vfab.getNewName());

            fabList.add(fab);

            for (File f : imgs) {
                fab = new FileAttrBean();
                fab.setNewName(f.getName());
                fab.setAbsolutePath(f.getAbsolutePath());
                fab.setRelativePath(urlBegin + fab.getNewName());

                String imgCosKey=BcwProperties.getUploadFileUrl()+File.separator+centerPath+File.separator+f.getName();
                putObjectRequest = new PutObjectRequest(BcwProperties.getBucketName(),imgCosKey, f);
                CosClientFactory.getCosClient().putObject(putObjectRequest);


                //压缩
                File srcfile = new File(f.getAbsolutePath());
                File tfile=new File(UrlUtil.addAttachUrlBeforeLastDot(f.getAbsolutePath(), "_" + BcwConstant.ATTACH_MIN_IMG));
                int wh[] = ImageUtil.changeImgSize(srcfile, tfile,0, BcwConstant.ATTACH_MIN_IMG_INT);

                putObjectRequest = new PutObjectRequest(BcwProperties.getBucketName(),UrlUtil.addAttachUrlBeforeLastDot(imgCosKey, "_" + BcwConstant.ATTACH_MIN_IMG) , tfile);
                CosClientFactory.getCosClient().putObject(putObjectRequest);


                fab.setWidth(wh[0]);
                fab.setHeight(wh[1]);

                fabList.add(fab);
            }
        }

        return fabList;
    }

    @RequestMapping(value = "/app/card/upload", method = {RequestMethod.POST})
    public FileAttrBean createCard(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                  @RequestParam(value = "type", required = true) String type,
                                  @RequestParam(value = "id", required = true) Integer id) throws Exception {

//        String path = BcwCommonService.getUploadRootFolder() + BcwConstant.FOLDER_CARD + File.separator + type + File.separator + BcwUserHolder.getDanceUser().getUserId();
//
//        List<MultipartFile> files = new ArrayList<MultipartFile>();
//        files.add(file);
//        UploadFileParam uploadFileParam = new UploadFileParam(files, path);
//        String[] ars = {"png", "jpg", "jpeg"};
//        uploadFileParam.setPermitFileExtensionArray(ars);
//        uploadFileParam.setPermitFileSize(1024 * 5);
//        KeepJoyUpload kju = new KeepJoyUpload(uploadFileParam);
//
//
//        List<FileAttrBean> uploadFileBeanList = kju.doUpload();
//        if (null != uploadFileBeanList && uploadFileBeanList.size() > 0) {
//            if (BcwConstant.TYPE_CLOCK.equals(type)) {
//                TblDanceClock c = get(TblDanceClock.class, this.id);
//                if (null == c) {
//                    throw new KeepJoyServiceException("id数据异常");
//                }
//                if (StringUtils.isNotEmpty(c.getCard())) {
//                    throw new KeepJoyServiceException("无法重复生成");
//                }
//                FileAttrBean fab = uploadFileBeanList.get(0);
//                c.setCard(fab.getNewName());
//                update(c);
//            }
//
//        }
        return null;
    }

//    @RequestMapping(value="/app/img/batch/upload", method=RequestMethod.POST)
//    public @ResponseBody
//    String handleFileUpload(HttpServletRequest request) throws FileUploadException {
//
//        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
//        MultipartFile file = null;
//        BufferedOutputStream stream = null;
//        for (int i =0; i< files.size(); ++i) {
//            file = files.get(i);
//            if (!file.isEmpty()) {
//                try {
//                    byte[] bytes = file.getBytes();
//                    stream =
//                            new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
//                    stream.write(bytes);
//                    stream.close();
//                } catch (Exception e) {
//                    stream =  null;
//                    return "You failed to upload " + i + " =>" + e.getMessage();
//                }
//            } else {
//                return "You failed to upload " + i + " becausethe file was empty.";
//            }
//        }
//        return "upload successful";
//
//    }
}

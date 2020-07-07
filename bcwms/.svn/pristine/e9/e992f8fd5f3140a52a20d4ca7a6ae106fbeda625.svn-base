package com.bcwms.util;


import com.keepjoy.core.util.MyLogUtil;
import com.keepjoy.core.util.UrlUtil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CaptureVideoUtil {


    /**
     *
     * @param videoPath 视频的路径
     * @param imgRootPath 存放截屏图片的根路径
     * @param num 要截的总图片数
     * @throws Exception
     */
    public static List<File> captureVideo(String videoPath, String imgRootPath,String imgNameBegin, int num)throws Exception {
        FFmpegFrameGrabber g = new FFmpegFrameGrabber(videoPath);

        List<File> imgs=new ArrayList<File>();
        try {
            g.start();
            Java2DFrameConverter converter = new Java2DFrameConverter();

            int allFrames = g.getLengthInFrames();
            int average = allFrames / (num + 1);//得到平均3份的

            int sum = 0;

            Frame frame;

            int idx = 1;
            File file=null;
            while (sum < g.getLengthInFrames()) {
                frame = g.grabImage();
                if (sum % average == 0 && sum > 0) {
                    if (null == frame) break;
                    String p=UrlUtil.addLastSlash(imgRootPath)+imgNameBegin+"_" + idx + ".jpg";
                    MyLogUtil.printlnInfo("capture video path:"+p);
                    file=new File(p);

                    imgs.add(file);

                    if(!file.exists()){
                        file.mkdirs();
                    }
                    ImageIO.write(converter.getBufferedImage(frame), "jpg", file);

                    idx++;
                    if(idx>num)break;

                }
                sum++;
            }
        }finally {
            g.stop();
        }

        return imgs;

    }
}

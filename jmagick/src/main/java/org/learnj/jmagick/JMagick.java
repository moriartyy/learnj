package org.learnj.jmagick;

import magick.ImageInfo;
import magick.MagickImage;

/**
 * @author hongmiao.yu on 2016/7/28.
 */
public class JMagick {

    public static void main(String[] args) {
        String picFrom = "D:/Download/a.jpg";
        String picTo = "D:/Temp/a.jpg";
        System.out.println(System.getProperty("java.library.path"));
        try {
            ImageInfo info = new ImageInfo(picFrom);
            MagickImage image = new MagickImage(new ImageInfo(picFrom));
            MagickImage scaled = image.scaleImage(120, 0);
            scaled.setFileName(picTo);
            scaled.writeImage(info);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

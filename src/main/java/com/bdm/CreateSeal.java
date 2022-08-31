package com.bdm;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import static com.bdm.utils.Graphics2DUtil.startGraphics2D;


/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/8/31-8:22
 */
public class CreateSeal {
    public static void main(String[] args) throws Exception{
        Date dNow = new Date( );
        /*SimpleDateFormat ft = new SimpleDateFormat("yyyy年MM月dd日");
        String year = ft.format(dNow);*/
        BufferedImage image = startGraphics2D("广州思博商贸有限公司","财务专用章",null);

        String filePath = "";
        try {
            filePath = "C:\\Users\\14740\\IdeaProjects\\mybatisplus\\seal\\src\\main\\resources\\"+new Date().getTime()+".png";
            ImageIO.write(image, "png", new File(filePath)); //将其保存在D:\\下，得有这个目录
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

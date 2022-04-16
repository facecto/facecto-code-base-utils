package com.facecto.code.base.toolkit.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.facecto.code.base.toolkit.oss.entity.OSSDirectParam;
import com.facecto.code.base.toolkit.upload.entity.WatermarkImage;
import com.facecto.code.base.toolkit.upload.entity.WatermarkPosition;
import com.facecto.code.base.toolkit.upload.entity.WatermarkText;
import com.facecto.code.base.toolkit.upload.entity.WatermarkType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * UpLoadTools
 *
 * @author Jon So, https://facecto.com, https://github.com/facecto
 * @version v1.1.2 (2022/01/01)
 */
public class UploadHandler {
    /**
     * get watermark position
     *
     * @param position   position
     * @param marginX    margin x
     * @param marginY    margin y
     * @param imgWidth   image width
     * @param imgHeight  image height
     * @param iconWidth  watermark width
     * @param iconHeight watermark height
     * @return map
     */
    private static Map<String, Integer> getPosition(WatermarkPosition position, int marginX
            , int marginY, int imgWidth, int imgHeight, int iconWidth, int iconHeight) {
        int x = 0;
        int y = 0;
        int xx = imgWidth - iconWidth;
        int yy = imgHeight - iconHeight;
        switch (position) {
            case TOP_CENTER:
                x = Math.floorDiv(xx, 2);
                y = marginY;
                break;
            case TOP_RIGHT:
                x = xx - marginX;
                y = marginY;
                break;
            case MIDDLE_LEFT:
                x = marginX;
                y = Math.floorDiv(yy, 2);
                break;
            case MIDDLE_CENTER:
                x = Math.floorDiv(xx, 2);
                y = Math.floorDiv(yy, 2);
                break;
            case MIDDLE_RIGHT:
                x = xx - marginX;
                y = Math.floorDiv(yy, 2);
                break;
            case UNDER_LEFT:
                x = marginX;
                y = yy - marginY;
                break;
            case UNDER_CENTER:
                x = Math.floorDiv(xx, 2);
                y = yy - marginY;
                break;
            case UNDER_RIGHT:
                x = xx - marginX;
                y = yy - marginY;
                break;
            default:
                x = marginX;
                y = marginY;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("x", x);
        map.put("y", y);
        return map;
    }

    /**
     * Download file from web
     *
     * @param urlString URLs that begin with http or https
     * @return local path
     * @throws IOException
     */
    private static String downloadImage(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        byte[] bs = new byte[1024];
        int len;
        File f = new File("");
        String filename = f.getCanonicalPath() + "\\" + System.currentTimeMillis() + ".jpg";
        File file = new File(filename);
        FileOutputStream os = new FileOutputStream(file, true);
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        is.close();
        System.out.println(filename);
        return filename;
    }

    /**
     * Upload file to root directory.
     * Ali OSS direct upload
     *
     * @param file  file
     * @param param OSS param
     * @return The full url after the upload is complete
     * @throws Exception
     */
    public String uploadFile(MultipartFile file, OSSDirectParam param) throws Exception {
        String path = System.currentTimeMillis() + getSuffix(file);
        return uploadFile(file, path, param);
    }

    /**
     * Upload file to specify directory
     * Ali OSS direct upload
     *
     * @param file     file
     * @param savePath The name of the saved file, without the first '/'
     * @param param    OSS param
     * @return The full url after the upload is complete
     * @throws Exception
     */
    public String uploadFile(MultipartFile file, String savePath, OSSDirectParam param) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Request a file.");
        }
        if (StringUtils.isEmpty(savePath)) {
            savePath = System.currentTimeMillis() + getSuffix(file);
        }
        return upload(file, savePath, param);
    }

    /**
     * Upload file to specify directory
     * Ali OSS direct upload
     *
     * @param inputStream file inputStream
     * @param savePath    The name of the saved file, without the first '/'
     * @param param       OSS param
     * @return The full url after the upload is complete
     * @throws Exception
     */
    public String uploadFile(InputStream inputStream, String savePath, OSSDirectParam param) throws Exception {
        if (StringUtils.isEmpty(savePath)) {
            throw new Exception("Request save path.");
        }
        return upload(inputStream, savePath, param);
    }

    /**
     * Upload file to specify directory
     * Ali OSS direct upload
     *
     * @param data     file byte[] data
     * @param savePath The name of the saved file, without the first '/'
     * @param param    OSS param
     * @return The full url after the upload is complete
     * @throws Exception
     */
    public String uploadFile(byte[] data, String savePath, OSSDirectParam param) throws Exception {
        if (StringUtils.isEmpty(savePath)) {
            throw new Exception("Request save path.");
        }
        return upload(data, savePath, param);
    }

    /**
     * Upload image
     * Ali OSS direct upload
     *
     * @param file       file
     * @param savePath   The name of the saved image, without the first '/' . ex: 20220202/001.jpg
     * @param waterType  watermark type
     * @param waterText  watermark for text
     * @param waterImage watermark for image
     * @param param      oss param
     * @return The full url after the upload is complete
     * @throws Exception
     */
    public String upImageBase(MultipartFile file, String savePath, WatermarkType waterType, WatermarkText waterText,
                              WatermarkImage waterImage, OSSDirectParam param) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Request a file.");
        }
        String type = file.getContentType();
        String[] values = {".jpg", ".png"};
        if (Arrays.stream(values).anyMatch(getSuffix(file).toLowerCase()::equals) && type.contains("image")) {
            InputStream inputStream = file.getInputStream();
            return upImageBase(inputStream, savePath, waterType, waterText, waterImage, param);
        }
        return null;
    }

    /**
     * Upload image (without check image type)
     * Ali OSS direct upload
     *
     * @param inputStream image file inputStream.
     * @param savePath    The name of the saved image, without the first '/' . ex: 20220202/001.jpg
     * @param waterType   watermark type
     * @param waterText   watermark for text
     * @param waterImage  watermark for image
     * @param param       oss par
     * @return The full url after the upload is complete
     * @throws Exception
     */
    public String upImageBase(InputStream inputStream, String savePath, WatermarkType waterType, WatermarkText waterText,
                              WatermarkImage waterImage, OSSDirectParam param) throws Exception {
        if (inputStream == null) {
            throw new Exception("Request a file.");
        }
        Image image = ImageIO.read(inputStream);
        if (waterType == WatermarkType.TEXT) {
            if (waterText == null) {
                waterText = new WatermarkText();
            }
        }
        if (waterType == WatermarkType.IMAGE) {
            if (waterImage == null) {
                waterImage = new WatermarkImage();
            }
        }
        return upload(handleImage(image, waterType, waterText, waterImage), savePath, param);
    }

    /**
     * Upload image with image watermark
     * Ali OSS direct upload
     *
     * @param file     file
     * @param savePath The name of the saved image, without the first '/' . ex: 20220202/001.jpg
     * @param image    watermark image
     * @param param    oss param
     * @return The full url after the upload is complete
     * @throws Exception
     */
    public String upImageWithWatermarkImage(MultipartFile file, String savePath, WatermarkImage image, OSSDirectParam param) throws Exception {
        return upImageBase(file, savePath, WatermarkType.IMAGE, null, image, param);
    }

    /**
     * Upload image with text watermark
     * Ali OSS direct upload
     *
     * @param file     file
     * @param savePath The name of the saved image, without the first '/' . ex: 20220202/001.jpg
     * @param text     watermark text
     * @param param    oss param
     * @return The full url after the upload is complete
     * @throws Exception
     */
    public String upImageWithWatermarkText(MultipartFile file, String savePath, WatermarkText text, OSSDirectParam param) throws Exception {
        return upImageBase(file, savePath, WatermarkType.TEXT, text, null, param);
    }

    /**
     * Upload image without watermark
     * Ali OSS direct upload
     *
     * @param file     image file
     * @param savePath The name of the saved image, without the first '/' . ex: 20220202/001.jpg
     * @param param    oss param
     * @return The full url after the upload is complete
     * @throws Exception
     */
    public String upImage(MultipartFile file, String savePath, OSSDirectParam param) throws Exception {
        return upImageBase(file, savePath, WatermarkType.NONE, null, null, param);
    }

    /**
     * Handle image with watermark
     *
     * @param image      original image
     * @param waterType  watermark type
     * @param waterText  watermark for text
     * @param waterImage watermark for image
     * @return InputStream
     * @throws Exception
     */
    private InputStream handleImage(Image image, WatermarkType waterType, WatermarkText waterText,
                                    WatermarkImage waterImage) throws Exception {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = bufferedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image, 0, 0, width, height, null);

        if (waterType == WatermarkType.IMAGE) {
            String waterUrl = waterImage.getWaterUrl();
            if (waterImage.getWaterUrl().startsWith("http")) {
                waterUrl = downloadImage(waterUrl);
            }
            ImageIcon imgIcon = new ImageIcon(waterUrl);
            Image imgWater = imgIcon.getImage();
            int iconWidth = imgWater.getWidth(null);
            int iconHeight = imgWater.getHeight(null);
            if (width - iconWidth < 0 || height - iconHeight < 0) {
                throw new Exception("The original image is smaller than the watermarked image!");
            }
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, waterImage.getWaterAlpha()));
            Map<String, Integer> map = getPosition(waterImage.getPosition(), waterImage.getMarginX(), waterImage.getMarginY(),
                    width, height, iconWidth, iconHeight);
            g.drawImage(imgWater, map.get("x"), map.get("y"), null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        }
        if (waterType == WatermarkType.TEXT) {
            Font font = new Font(waterText.getFontName(), Font.BOLD, waterText.getFontSize());
            Color color = new Color(waterText.getRed(), waterText.getGreen(), waterText.getBlue(), waterText.getAlpha());
            g.setColor(color);
            g.setFont(font);
            Map<String, Integer> map = getPosition(waterText.getPosition(), waterText.getMarginX(), waterText.getMarginY(), width, height,
                    0, 0);
            g.drawString(waterText.getWaterText(), map.get("x"), map.get("y"));
        }
        g.dispose();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageIO.write(bufferedImage, "jpg", outputStream);
        bufferedImage.flush();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    /**
     * Get multipartFile suffix
     *
     * @param file
     * @return file suffix
     */
    private String getSuffix(MultipartFile file) {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        return suffix;
    }

    /**
     * Upload file
     *
     * @param data     byte[]
     * @param savePath the file save path
     * @param ossParam oss param
     * @return The full url after the upload is complete
     */
    private String upload(byte[] data, String savePath, OSSDirectParam ossParam) {
        return upload(new ByteArrayInputStream(data), savePath, ossParam);
    }

    /**
     * Upload file
     *
     * @param inputStream file inputStream
     * @param savePath    the file save path
     * @param ossParam    oss param
     * @return The full url after the upload is complete
     */
    private String upload(InputStream inputStream, String savePath, OSSDirectParam ossParam) {
        OSS client = new OSSClientBuilder().build(ossParam.getEndpoint(), ossParam.getAccessKeyId(), ossParam.getAccessKeySecret());
        client.putObject(ossParam.getBucketName(), savePath, inputStream);
        client.shutdown();
        return ossParam.getDomain() + "/" + savePath;
    }

    /**
     * Upload file
     *
     * @param file     multipartFile
     * @param savePath the file save path
     * @param ossParam oss param
     * @return The full url after the upload is complete
     * @throws IOException
     */
    private String upload(MultipartFile file, String savePath, OSSDirectParam ossParam) throws IOException {
        InputStream stream = file.getInputStream();
        return upload(stream, savePath, ossParam);
    }

}

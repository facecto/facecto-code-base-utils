package com.facecto.code.base.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * QRCodeUtils
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.2 (2022/01/01)
 */
@Slf4j
public class QRCodeUtils {
    private static final int CODE_WIDTH = 400;
    private static final int CODE_HEIGHT = 400;
    private static final int COLOR_FRONT = 0x000000;
    private static final int COLOR_BACKGROUND = 0xFFFFFF;

    private int codeWidth;
    private int codeHeight;
    private int colorFront;
    private int colorBackground;

    /**
     * Constructors
     * @param width Number, if less than or equal to 0 will use the default value of 400.
     * @param height Number, if less than or equal to 0 will use the default value of 400.
     * @param color_front QR code body color, hexadecimal color, or default black if empty.
     * @param color_background Background color, hex color, or default black if empty.
     */
    public QRCodeUtils(int width, int height, Integer color_front, Integer color_background){
        if(width<=0){
            this.codeWidth = CODE_WIDTH;
        } else {
            this.codeWidth = width;
        }
        if(height<=0){
            this.codeHeight = CODE_HEIGHT;
        } else {
            this.codeHeight= height;
        }
        if(color_front == null || color_front<=0){
            this.colorFront = COLOR_FRONT;
        } else {
            this.colorFront = color_front;
        }
        if(color_background == null || color_background<=0){
            this.colorBackground = COLOR_BACKGROUND;
        } else {
            this.colorBackground = color_background;
        }
    }

    /**
     * Constructors by default value
     */
    public QRCodeUtils(){
        this.codeWidth = CODE_WIDTH;
        this.codeHeight = CODE_HEIGHT;
        this.colorFront = COLOR_FRONT;
        this.colorBackground =COLOR_BACKGROUND;
    }

    /**
     * Create a QR code and save it to the specified path
     * @param codeText QR code text
     * @param filePath Specified path
     * @throws WriterException
     * @throws IOException
     */
    public void create2File(String codeText, String filePath) throws WriterException, IOException {
        codeText = checkCodeText(codeText);
        File file = new File(filePath);
        String savePath = "";
        String saveName = "";
        if(file.isDirectory()){
            saveName = System.currentTimeMillis() + ".png";
        } else {
            filePath = StringUtils.substringBeforeLast(filePath,"/");
            saveName = StringUtils.substringAfterLast(filePath, "/");
            if(!StringUtils.substringAfterLast(saveName,".").equals("png")){
                saveName = saveName + ".png";
            }
        }
        File codeImgFile = new File(savePath, saveName);
        ImageIO.write(getBufferedImage(codeText), "png", codeImgFile);
        log.info("QRCode create success, the local path is " + codeImgFile.getPath());
    }

    /**
     * Create QR code and write to stream
     * @param codeText QR code text
     * @param outputStream stream
     * @throws WriterException
     * @throws IOException
     */
    public void create2Stream(String codeText, OutputStream outputStream) throws WriterException, IOException {
        ImageIO.write(getBufferedImage(codeText), "png", outputStream);
        log.info("QRCode create success.");
    }

    /**
     * Check QR code text
     * @param codeText
     * @return String
     */
    private String checkCodeText(String codeText) {
        if (codeText == null || "".equals(codeText.trim())) {
            codeText = "Create by facecto.com QRCode utils.";
            log.info("the codeText is null.");
        }
        return codeText.trim();
    }

    /**
     * Get bufferedImage by text
     * @param codeText text
     * @return BufferedImage
     * @throws WriterException
     */
    private BufferedImage getBufferedImage(String codeText) throws WriterException {
        codeText = checkCodeText(codeText);
        Map<EncodeHintType, Object> hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.MARGIN, 1);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(codeText, BarcodeFormat.QR_CODE, this.codeWidth, this.codeHeight, hints);
        BufferedImage bufferedImage = new BufferedImage(this.codeWidth, this.codeHeight, BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < CODE_WIDTH; x++) {
            for (int y = 0; y < CODE_HEIGHT; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? this.colorFront : this.colorBackground);
            }
        }
        return bufferedImage;
    }

}

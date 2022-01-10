package com.facecto.code.base.toolkit.upload.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.2 (2022/01/01)
 */
@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class WatermarkText {
    /**
     * The watermark coordinates X
     */
    private Integer marginX;
    /**
     * The watermark coordinates Y
     */
    private Integer marginY;
    /**
     * The watermark position.
     */
    private WatermarkPosition position;
    /**
     * The watermark text.
     */
    private String waterText;
    /**
     * The watermark font name.
     */
    private String fontName;
    /**
     * The watermark font size.
     */
    private int fontSize;
    /**
     * Red value of the watermark color. Value range 0 - 255.
     */
    private int red;
    /**
     * Green value of the watermark color. Value range 0 - 255.
     */
    private int green;
    /**
     * Blue value of the watermark color. Value range 0 - 255.
     */
    private int blue;
    /**
     * The watermark alpha, Value range 0 - 255
     */
    private int alpha;

    private static final int DEFAULT_MARGIN_X = 20;
    private static final int DEFAULT_MARGIN_Y = 20;
    private static final WatermarkPosition DEFAULT_POSITION = WatermarkPosition.TOP_LEFT;
    private static final String DEFAULT_TEXT = "facecto.com";
    private static final String DEFAULT_FONT_NAME = "Arial";
    private static final int DEFAULT_FONT_SIZE = 14;
    private static final int DEFAULT_RED = 0;
    private static final int DEFAULT_GREEN = 0;
    private static final int DEFAULT_BLUE = 0;
    private static final int DEFAULT_ALPHA = 255;

    public WatermarkText() {
        this.marginX = DEFAULT_MARGIN_X;
        this.marginY = DEFAULT_MARGIN_Y;
        this.position = DEFAULT_POSITION;
        this.waterText = DEFAULT_TEXT;
        this.fontName = DEFAULT_FONT_NAME;
        this.fontSize = DEFAULT_FONT_SIZE;
        this.red = DEFAULT_RED;
        this.green = DEFAULT_GREEN;
        this.blue = DEFAULT_BLUE;
        this.alpha = DEFAULT_ALPHA;
    }

}

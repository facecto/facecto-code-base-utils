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
public class WatermarkImage {
    private static final int DEFAULT_MARGIN_X = 20;
    private static final int DEFAULT_MARGIN_Y = 20;
    private static final WatermarkPosition DEFAULT_POSITION = WatermarkPosition.TOP_LEFT;
    private static final String DEFAULT_URL = "";
    private static final float DEFAULT_ALPHA = 1F;
    /**
     * Watermark margin X
     */
    private Integer marginX;
    /**
     * Watermark margin Y
     */
    private Integer marginY;
    /**
     * Watermark position
     */
    private WatermarkPosition position;
    /**
     * Watermark url
     */
    private String waterUrl;
    /**
     * Watermark alpha
     */
    private float waterAlpha = 1f;

    public WatermarkImage() {
        this.marginX = DEFAULT_MARGIN_X;
        this.marginY = DEFAULT_MARGIN_Y;
        this.position = DEFAULT_POSITION;
        this.waterUrl = DEFAULT_URL;
        this.waterAlpha = DEFAULT_ALPHA;
    }
}
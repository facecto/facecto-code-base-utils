package com.facecto.code.base.toolkit.upload.entity;

import lombok.Getter;

/**
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.2 (2022/01/01)
 */
@Getter
public enum WatermarkType {
    /**
     * Text watermark
     */
    TEXT(1, "Text watermark"),
    /**
     * Image watermark
     */
    IMAGE(2, "Image watermark"),
    /**
     * Without watermark
     */
    NONE(0, "Without watermark");
    private Integer type;
    private String typeName;

    WatermarkType(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }
}

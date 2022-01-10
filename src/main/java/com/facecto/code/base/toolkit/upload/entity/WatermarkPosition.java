package com.facecto.code.base.toolkit.upload.entity;

import lombok.Getter;

/**
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.2 (2022/01/01)
 */
@Getter
public enum WatermarkPosition {
    /**
     * Top && left
     */
    TOP_LEFT(1, "Top left"),
    /**
     * Top && middle
     */
    TOP_CENTER(2, "Top middle"),
    /**
     * Top && right
     */
    TOP_RIGHT(3, "Top right"),
    /**
     * Middle && left
     */
    MIDDLE_LEFT(4, "Middle left"),
    /**
     * Middle && middle
     */
    MIDDLE_CENTER(5, "Center"),
    /**
     * Middle && right
     */
    MIDDLE_RIGHT(6, "Middle right"),

    /**
     * Bottom && left
     */
    UNDER_LEFT(7, "Bottom left"),
    /**
     * Bottom && middle
     */
    UNDER_CENTER(8, "Bottom middle"),
    /**
     * Bottom && right
     */
    UNDER_RIGHT(9, "Bottom right"),
    ;

    /**
     * Position && type
     */
    private int positionType;
    /**
     * Position && name
     */
    private String positionName;

    private WatermarkPosition(int type, String name) {
        this.positionType = type;
        this.positionName = name;
    }
}

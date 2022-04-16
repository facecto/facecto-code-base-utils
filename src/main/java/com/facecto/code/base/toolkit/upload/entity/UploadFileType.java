package com.facecto.code.base.toolkit.upload.entity;

import lombok.Getter;

/**
 * @author Jon So, https://facecto.com, https://github.com/facecto
 * @version v1.1.2 (2022/01/01)
 */
@Getter
public enum UploadFileType {
    /**
     * File type
     */
    FILE(1, "File"),
    /**
     * JPG,PNG
     */
    IMAGE(2, "JPG,PNG");
    private Integer typeCode;
    private String typeName;

    UploadFileType(Integer typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }
}
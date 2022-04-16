package com.facecto.code.base.toolkit.oss.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Jon So, https://facecto.com, https://github.com/facecto
 * @version v1.1.2 (2022/01/01)
 */
@Data
@Accessors(chain = true)
public class OSSDirectParam extends OSSBaseParam {
    private String bucketName;
    private String domain;
}

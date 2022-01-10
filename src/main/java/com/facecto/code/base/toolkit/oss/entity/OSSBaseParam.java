package com.facecto.code.base.toolkit.oss.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.2 (2022/01/01)
 */
@Data
@Accessors(chain = true)
public class OSSBaseParam implements Serializable {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
}

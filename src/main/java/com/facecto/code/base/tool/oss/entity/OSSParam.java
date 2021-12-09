package com.facecto.code.base.tool.oss.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.0.0 (2021/12/09)
 */
@Data
@Accessors(chain = true)
public class OSSParam {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String roleArn;
    private String roleSessionName;
    private String policy;
    private Long expiration;
}

package com.facecto.code.base.toolkit.oss.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.2 (2022/01/01)
 */
@Data
@Accessors(chain = true)
public class OSSSTSParam extends OSSBaseParam {
    private String roleArn;
    private String roleSessionName;
    private String policy;
    private Long expiration;
}

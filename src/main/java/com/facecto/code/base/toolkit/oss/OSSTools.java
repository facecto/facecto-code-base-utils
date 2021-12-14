package com.facecto.code.base.toolkit.oss;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.facecto.code.base.toolkit.oss.entity.OSSParam;
import com.facecto.code.base.toolkit.oss.entity.STSToken;

/**
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.0.0 (2021/12/09)
 */
public class OSSTools {
    public STSToken getToken(OSSParam ossParam){
        STSToken stsToken =new STSToken();
        try {
            DefaultProfile.addEndpoint( "", "Sts", ossParam.getEndpoint());
            IClientProfile profile = DefaultProfile.getProfile("", ossParam.getAccessKeyId(),ossParam.getAccessKeySecret());
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setRoleArn(ossParam.getRoleArn());
            request.setRoleSessionName(ossParam.getRoleSessionName());
            request.setPolicy(ossParam.getPolicy());
            request.setDurationSeconds(ossParam.getExpiration());
            final AssumeRoleResponse response = client.getAcsResponse(request);
            stsToken.setExpiration(response.getCredentials().getExpiration());
            stsToken.setAccessKeyId(response.getCredentials().getAccessKeyId());
            stsToken.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
            stsToken.setSecurityToken(response.getCredentials().getSecurityToken());
            stsToken.setRequestId(response.getRequestId());
        } catch (ClientException e) {
            System.out.println("Failed：");
        }
        return stsToken;
    }
}

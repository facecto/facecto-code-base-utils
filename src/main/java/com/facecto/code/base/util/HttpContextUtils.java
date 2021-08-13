package com.facecto.code.base.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpContextUtils
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.0 (2021/08/08)
 */
public class HttpContextUtils {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * get http requet domain
     * @return string
     */
    public static String getDomain() {
        HttpServletRequest request = getHttpServletRequest();
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    /**
     * get http request origin
     * @return string
     */
    public static String getOrigin() {
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }
}

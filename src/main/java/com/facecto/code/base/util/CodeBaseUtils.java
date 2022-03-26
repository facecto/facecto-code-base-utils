package com.facecto.code.base.util;

/**
 * ConvertUtils
 *
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.2 (2022/02/01)
 */
public class CodeBaseUtils {
    /**
     * get stack
     *
     * @return stacktrace
     */
    public static StackTraceElement getStack() {
        return new Throwable().getStackTrace()[1];
    }


}

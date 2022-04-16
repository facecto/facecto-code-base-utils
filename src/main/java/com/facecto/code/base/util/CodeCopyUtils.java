package com.facecto.code.base.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * CopyUtils
 *
 * @author Jon So, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/08/08)
 */
public class CodeCopyUtils {
    /**
     * getNullPropertyNames
     *
     * @param source object
     * @return String[]
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * copyProperties ignore null
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target,getNullPropertyNames(source));
    }
}

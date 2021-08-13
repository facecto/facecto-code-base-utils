package com.facecto.code.base.util;

import org.springframework.cglib.beans.BeanMap;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * MapConvertUtils
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.0 (2021/08/08)
 */
public class MapConvertUtils {

    /**
     * get map
     * @param key key
     * @param value value
     * @return hashmap
     */
    public static HashMap<String, Object> getMap(String key, Object value) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    /**
     * get boolean value from map
     * @param map map
     * @param key key
     * @return boolean
     */
    public static Boolean getBoolValue(Map<String, Object> map, String key) {
        if (map.get(key) != null) {
            return Boolean.parseBoolean(map.get(key).toString());
        }
        return null;
    }

    /**
     * get string from map
     * @param map map
     * @param key key
     * @return string
     */
    public static String getStringValue(Map<String, Object> map, String key) {
        if (map.get(key) != null) {
            return map.get(key).toString();
        }
        return null;
    }

    /**
     * get integer value from map
     * @param map map
     * @param key key
     * @return integer
     */
    public static Integer getIntegerValue(Map<String, Object> map, String key) {
        if (map.get(key) != null) {
            return Integer.parseInt(map.get(key).toString());
        }
        return null;
    }

    /**
     * get double value from map
     * @param map map
     * @param key key
     * @return double
     */
    public static Double getDoubleValue(Map<String, Object> map, String key) {
        if (map.get(key) != null) {
            return Double.parseDouble(map.get(key).toString());
        }
        return null;
    }

    /**
     * get float value from map
     * @param map map
     * @param key key
     * @return float
     */
    public static Float getFloatValue(Map<String, Object> map, String key) {
        if (map.get(key) != null) {
            return Float.parseFloat(map.get(key).toString());
        }
        return null;
    }

    /**
     * get bigdecimal value from map
     * @param map map
     * @param key key
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimalValue(Map<String, Object> map, String key) {
        if (map.get(key) != null) {
            return new BigDecimal(map.get(key).toString());
        }
        return null;
    }

    /**
     * object convert to map
     * @param bean bean
     * @param <T> the bean type
     * @return map
     */
    public static <T> Map<String, Object> conversionMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(String.valueOf(key), beanMap.get(key));
            }
        }
        return map;
    }
}
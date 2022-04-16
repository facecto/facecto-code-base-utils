package com.facecto.code.base.util;

import com.alibaba.fastjson.JSON;
import com.facecto.code.base.annotation.NameInMap;
import org.springframework.cglib.beans.BeanMap;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * MapConvertUtils
 *
 * @author Jon So, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/08/08)
 */
public class CodeMapUtils {

    /**
     * Converting entity classes to map
     *
     * @param entity entity
     * @return map
     */
    public static Map<String, Object> getMapFromEntity(Object entity) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = entity.getClass();
        convertMap(map, clazz, entity);
        return map;
    }

    /**
     * Converting entity classes to map (single layer)
     *
     * @param entity object
     * @return map
     */
    public static Map<String, String> getMapFromEntitySimple(Object entity) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = entity.getClass();
        convertMapSimple(map, clazz, entity);
        return map;
    }

    /**
     * Convert entity class to map (including parent class)
     *
     * @param map    map
     * @param clazz  class
     * @param target object
     * @throws Exception IllegalAccessException
     */
    private static void convertMap(Map<String, Object> map, Class clazz, Object target) throws IllegalAccessException {
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        for (Field field : fields) {
            NameInMap nameInMap = field.getAnnotation(NameInMap.class);
            field.setAccessible(true);
            String key;
            Object value = field.get(target);
            if (nameInMap != null) {
                key = nameInMap.value();
                if (value != null) {
                    if (nameInMap.type() == String.class) {
                        map.put(key, value);
                    } else if (nameInMap.type() == List.class) {
                        List<Object> values = (List<Object>) value;
                        List<Object> list = new ArrayList<>();
                        map.put(key, list);
                        for (Object o : values) {
                            if (isBasicType(o)) {
                                list.add(o);
                            } else {
                                Map<String, Object> innerOptions = new HashMap<>();
                                list.add(innerOptions);
                                convertMap(innerOptions, o.getClass(), o);
                            }
                        }
                    } else {
                        Map<String, Object> innerOptions = new HashMap<>();
                        map.put(key, innerOptions);
                        convertMap(innerOptions, nameInMap.type(), value);
                    }
                }
            } else {
                key = field.getName();
                if (value != null) {
                    map.put(key, value);
                }
            }
        }
    }

    /**
     * Convert entity class to map (including parent class)
     * Simple entity
     *
     * @param map    map
     * @param clazz  class
     * @param target object
     * @throws IllegalAccessException
     */
    private static void convertMapSimple(Map<String, String> map, Class clazz, Object target) throws IllegalAccessException {
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        for (Field field : fields) {
            NameInMap nameInMap = field.getAnnotation(NameInMap.class);
            field.setAccessible(true);
            String key;
            Object value = field.get(target);
            if (nameInMap != null) {
                key = nameInMap.value();
            } else {
                key = field.getName();
            }
            if (value != null) {
                map.put(key, value.toString());
            }
        }
    }

    /**
     * Convert entity class to map (including parent class)
     *
     * @param o Object
     * @return boolean
     */
    private static boolean isBasicType(Object o) {
        return o instanceof String || o instanceof Character || o instanceof Integer || o instanceof Long || o instanceof Byte
                || o instanceof Double || o instanceof Float || o instanceof Boolean;
    }

    /**
     * Get the requested Map from HttpServletRequest
     *
     * @param request HttpServletRequest
     * @return map
     */
    public static Map<String, String> getRequestParam2Map(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<?> temp = request.getParameterNames();
        while (temp.hasMoreElements()) {
            String en = (String) temp.nextElement();
            String value = request.getParameter(en);
            map.put(en, value);
        }
        return map;
    }

    /**
     * Get the requested Map from HttpServletRequest and convert it to JSON
     *
     * @param request HttpServletRequest
     * @return JSON
     */
    public static String getRequestParam2Json(HttpServletRequest request) {
        Map<String, String> map = getRequestParam2Map(request);
        return JSON.toJSONString(map);
    }


    /**
     * get map
     *
     * @param key   key
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @param bean bean
     * @param <T>  the bean type
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
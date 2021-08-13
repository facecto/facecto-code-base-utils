package com.facecto.code.base.util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ListUtils
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.0 (2021/08/08)
 */
public class ListUtils {
    /**
     * check list1 and list2 has equal
     * @param list1 List
     * @param list2 Lsit
     * @return boolean
     */
    public static boolean hasEqual(List<Integer> list1, List<Integer> list2){
        List<Integer> collect1 = list1.stream().sorted(Comparator.comparing(a -> a)).collect(Collectors.toList());
        List<Integer> collect2 = list2.stream().sorted(Comparator.comparing(a -> a)).collect(Collectors.toList());
        return collect1.equals(collect2);
    }
}

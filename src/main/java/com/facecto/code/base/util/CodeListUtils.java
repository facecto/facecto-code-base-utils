package com.facecto.code.base.util;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * ListUtils
 *
 * @author Jon So, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/08/08)
 */
public class CodeListUtils {
    /**
     * check list1 and list2 has equal
     *
     * @param list1 List
     * @param list2 Lsit
     * @return boolean
     */
    public static boolean hasEqual(List<Integer> list1, List<Integer> list2) {
        List<Integer> collect1 = list1.stream().sorted(Comparator.comparing(a -> a)).collect(Collectors.toList());
        List<Integer> collect2 = list2.stream().sorted(Comparator.comparing(a -> a)).collect(Collectors.toList());
        return collect1.equals(collect2);
    }

    /**
     * list sort
     *
     * @param list      list
     * @param isAsc     true=a false=desc
     * @param isChinese true=chinese
     * @return list
     */
    public static List<String> sortList(List<String> list, boolean isAsc, boolean isChinese) {
        if (isChinese) {
            if (isAsc) {
                return list.stream()
                        .sorted((s1, s2) -> Collator.getInstance(Locale.CHINESE).compare(s1, s2))
                        .collect(Collectors.toList());
            } else {
                return list.stream()
                        .sorted((s1, s2) -> Collator.getInstance(Locale.CHINESE).reversed().compare(s1, s2))
                        .collect(Collectors.toList());
            }
        } else {
            return list.stream().sorted(Comparator.comparing(String::toString).reversed()).collect(Collectors.toList());
        }
    }
}

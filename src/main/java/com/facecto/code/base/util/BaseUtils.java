package com.facecto.code.base.util;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * ConvertUtils
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.2 (2022/02/01)
 */
public class BaseUtils {
    public static StackTraceElement getStack(){
        return new Throwable().getStackTrace()[1];
    }

    public static List<String> sortList(List<String> list, boolean isAsc, boolean isChinese){
        if(isChinese){
            if(isAsc){
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

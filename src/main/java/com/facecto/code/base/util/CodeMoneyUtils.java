package com.facecto.code.base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * ConvertUtils
 *
 * @author Jon So, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/08/08)
 */
public class CodeMoneyUtils {
    /**
     * china: fen to yuan
     *
     * @param fen fen
     * @return BigDecimal
     */
    public static BigDecimal getFen2Yuan(Integer fen) {
        BigDecimal d = new BigDecimal(fen).divide(new BigDecimal(100));
        return d;
    }

    /**
     * china: get yuan string
     *
     * @param number BigDecimal
     * @return String
     */
    private static String getYuan(BigDecimal number) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(number);
    }

    /**
     * china: fen to yuan
     *
     * @param fen Integer
     * @return String
     */
    public static String getFen2YuanString(Integer fen) {
        return getYuan(getFen2Yuan(fen));
    }

}

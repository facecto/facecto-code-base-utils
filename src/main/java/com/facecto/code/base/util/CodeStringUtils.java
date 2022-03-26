package com.facecto.code.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * StringFormatUtils
 *
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.1.0 (2021/08/08)
 */
public class CodeStringUtils {

    private static final int TEL_LENGTH = 11;
    private static final int ID_LENGTH_18 = 18;
    private static final int ID_LENGTH_15 = 15;

    /**
     * china mobile number protection
     * example: 13912345678 to 139****5678
     *
     * @param tel length
     * @return the format string
     */
    public static String getProtectionTel(String tel) {
        if (tel.length() == TEL_LENGTH) {
            return tel.substring(0, 3) + "****" + tel.substring(7, 11);
        }
        return tel;
    }

    /**
     * china id protection
     * example: 350101202105201234 to 3501**2021****1234
     *
     * @param IdNo length 18 or 15
     * @return the format string
     */
    public static String getProtectionIdNo(String IdNo) {
        if (IdNo.length() == ID_LENGTH_18) {
            return IdNo.substring(0, 4) + "****" + IdNo.substring(8, 12) + "****" + IdNo.substring(16, 18);
        }
        if (IdNo.length() == ID_LENGTH_15) {
            return IdNo.substring(0, 4) + "**" + IdNo.substring(6, 10) + "****" + IdNo.substring(14, 15);
        }
        return IdNo;
    }

    /**
     * Get a random string
     *
     * @return String
     */
    public static String getNonceStr() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * getUUID no hyphen(-)
     *
     * @return string
     */
    public static String getUuidNoHyphen(int len) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, len);
    }

    /**
     * get random string
     *
     * @return String
     */
    public static String getRandomNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return sdf.format(new Date()) + getUuidNoHyphen(16);
    }
}

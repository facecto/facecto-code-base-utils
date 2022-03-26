package com.facecto.code.base.generator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author Jon So, https://cto.pub, https://github.com/facecto
 * @version v1.0.0 (2021/12/02)
 * Get 24-bit fixed-length sequential code.
 * Used to generate sequential codes with dates.
 * The first 8 bits are the year, month and date.
 * The 9th bit is the microservice designator and takes values in the range 0-15 (displayed as hex 0-9A-F).
 * The 10th bit is the machine designator and takes the value range 0-15 (displayed as 0-9A-F in hexadecimal).
 * The last 14 bits are the sequence codes.
 * Example: 20211202AB10098259438592
 * <p>
 * Joke: A technician of a company (in Xiamen),
 * he memorized the snowflake very well and was proud of it. When the digit adjustment, he did not recognize it,
 * hahaha...
 */
public class IdBuilder {
    private final static int CODE_LENGTH = 14;
    private final static long APP_BIT = 4L;
    private final static long MACHINE_BIT = 4L;
    private final static long SEQUENCE_BIT = 12L;

    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_APP_NUM = -1L ^ (-1L << APP_BIT);

    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long APP_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIME_LEFT = APP_LEFT + APP_BIT;

    private final static String DATE_STRING = LocalDate.now().toString();
    private final static DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final static long START_TIMESTAMP = LocalDateTime.parse(DATE_STRING + " 00:00:00", DF)
            .toInstant(ZoneOffset.of("+8")).toEpochMilli();

    private long appId;
    private long machineId;
    private long sequence = 0L;
    private long lastTimeStamp = -1L;
    private String appIdChar;
    private String machineChar;


    /**
     * The parameters are required
     *
     * @param appId     Microservice Code,takes values in the range 0-15
     * @param machineId Machine code,takes values in the range 0-15
     */
    public IdBuilder(long appId, long machineId) {
        if (appId > MAX_APP_NUM || appId < 0) {
            throw new IllegalArgumentException("AppId can't be greater than 15 or less than 0！");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("MachineId can't be greater than 15 or less than 0！");
        }
        this.appId = appId;
        this.machineId = machineId;
        appIdChar = Long.toString(appId, 16).toUpperCase();
        machineChar = Long.toString(machineId, 16).toUpperCase();
    }

    /**
     * Get millis
     *
     * @return millis
     */
    private long getNext() {
        long mill = getNew();
        while (mill <= lastTimeStamp) {
            mill = getNew();
        }
        return mill;
    }

    /**
     * Get millis
     *
     * @return millis
     */
    private long getNew() {
        return System.currentTimeMillis();
    }

    /**
     * Generate ID
     *
     * @return 24bit ID
     */
    public synchronized String genId() {
        long currTimeStamp = getNew();
        if (currTimeStamp < lastTimeStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currTimeStamp == lastTimeStamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                currTimeStamp = getNext();
            }
        } else {
            sequence = 0L;
        }

        lastTimeStamp = currTimeStamp;
        Long result = (currTimeStamp - START_TIMESTAMP) << TIME_LEFT
                | appId << APP_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
        return DATE_STRING.replace("-", "") + appIdChar + machineChar + cast(result.toString());
    }

    /**
     * Converts to a length-compliant string
     *
     * @param StringNumber
     * @return 14bit string
     */
    private String cast(String StringNumber) {
        int len1 = StringNumber.length();
        if (len1 < CODE_LENGTH) {
            return getZero(CODE_LENGTH - len1) + StringNumber;
        }
        return StringNumber;
    }

    /**
     * Left-aligned zero complement
     *
     * @param x Number of replenishments required
     * @return 14bit string
     */
    private String getZero(int x) {
        if (x <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            sb.append(0);
        }
        return sb.toString();
    }
}

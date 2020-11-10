package com.lege.android.base.string;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    /* //时间戳转换日期 */
    public static byte[] stampToTimeM(Long stamp) {
        byte[] s = new byte[5];
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdM = new SimpleDateFormat("yyMMddHHmm");
        String sd = sdM.format(new Date(stamp)); // 时间戳转换日期
        s[0] = (byte)Integer.parseInt(sd.substring(0,2),16);
        s[1] = (byte)Integer.parseInt(sd.substring(2,4),16);
        s[2] = (byte)Integer.parseInt(sd.substring(4,6),16);
        s[3] = (byte)Integer.parseInt(sd.substring(6,8),16);
        s[4] = (byte)Integer.parseInt(sd.substring(8,10),16);
        return s;
    }

    /* //时间戳转换日期 */
    public static String stampToTime(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdM = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return sdM.format(new Date(stamp));
    }

    public static String stampToTimeyyyy(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdM = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdM.format(new Date(stamp));
    }

    public static String stampToTimeyyyy2(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdM = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return sdM.format(new Date(stamp * 1000));
    }

    /* //时间戳转换日期 */
    public static String stampToTimeyyyyMMddHHmm(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdM.format(new Date(stamp));
    }

    /* //时间戳转换日期 */
    public static String stampToTimeymd(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdM = new SimpleDateFormat("yyyy-MM-dd");
        return sdM.format(new Date(stamp));
    }

    /* //时间戳转换日期 */
    public static String stampToTimeyyyyMM(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sd = new SimpleDateFormat("yyyyMM");
        return sd.format(new Date(stamp));
    }

    /* //时间戳转换日期 */
    public static String stampToTimeyyyyMMdd(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        return sd.format(new Date(stamp));
    }

    /* //时间戳转换日期 */
    public static String stampToTimeMMdd(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sd = new SimpleDateFormat("MM/dd");
        return sd.format(new Date(stamp));
    }

    public static String stampToTimemm(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sd = new SimpleDateFormat("mm");
        return sd.format(new Date(stamp));
    }
    public static String stampToTimeHH(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sd = new SimpleDateFormat("HH");
        return sd.format(new Date(stamp));
    }
    public static String stampToTimeHHmm(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
        return sd.format(new Date(stamp));
    }
    public static String stampToTimedd(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sd = new SimpleDateFormat("dd");
        return sd.format(new Date(stamp));
    }
    public static String stampToTimeMM(Long stamp) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sd = new SimpleDateFormat("MM");
        return sd.format(new Date(stamp));
    }
    public static int stampToTimeWeek(Long timestemp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestemp);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static long date2TimeStamp(String date, String format) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

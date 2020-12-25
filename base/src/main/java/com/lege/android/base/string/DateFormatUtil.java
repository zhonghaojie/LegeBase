package com.lege.android.base.string;

import com.lege.android.base.log.APPLog;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static java.util.Calendar.DAY_OF_MONTH;

/**
 * Description:
 * Created by loctek on 2020/6/17.
 */
public class DateFormatUtil {
    public static enum DateDistance {
        DAY, HOUR, MINUTE, SECOND
    }

    public static String getDateTime(@NotNull Date date, @NotNull String format) {
        String result = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            result = simpleDateFormat.format(date);
        } catch (IllegalArgumentException e) {

        } finally {
            return result;
        }

    }

    public static String getSystemCurrent_Time() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getSystemCurrent_Time2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getSystemCurrent_Time_NoMillSecond() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getSystemCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getCurrentTime2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getCurrentDate2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getCurrentDate4() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getCurrentDate3() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 把秒先转毫秒，然后转日期
     *
     * @param timeSecond
     * @return
     */
    public static String getCurrentDate(long timeSecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(timeSecond * 1000);
        return simpleDateFormat.format(date);
    }


    /**
     * 毫秒转化成日期
     *
     * @param millsecond
     * @return
     */
    public static String millSecondConvertDate(long millsecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(millsecond);
        return simpleDateFormat.format(date);
    }

    /**
     * 毫秒转化成日期
     *
     * @param millsecond
     * @return
     */
    public static String millSecondConvertDate(long millsecond, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date(millsecond);
        return simpleDateFormat.format(date);
    }

    /**
     * yyyy年MM月dd日 HH:mm:ss"转成MM-dd，去除年
     *
     * @return
     */
    public static String getCurrentDateNoYear(String date) {
        String[] date_time = date.split(" ");
        String dateStr = date_time[0];
        String timeStr = date_time[1];
        APPLog.log("dateStr===" + dateStr + "==timeStr==" + timeStr);
        String date_noyear = dateStr.substring(5, dateStr.length() - 1);
        date_noyear = date_noyear.replace("月", "-");
        return date_noyear;
    }

    /**
     * 将时间转化成毫秒
     * 时间格式: yyyy-MM-dd HH:mm
     *
     * @param time
     * @return
     */
    public static Long timeStrToMillSecond(String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Long second = format.parse(time).getTime();
            return second;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1l;
    }

    /**
     * yyyy年MM月dd日 HH:mm:ss"转成HH:mm，去除年
     *
     * @return
     */
    public static String getCurrentTimeNoSecond(String time) {
        String[] date_time = time.split(" ");
        String dateStr = date_time[0];
        String timeStr = date_time[1];
        APPLog.log("dateStr===" + dateStr + "==timeStr==" + timeStr);
        int index = timeStr.lastIndexOf(":");
        String timeStr_noSecond = timeStr.substring(0, index);
        return timeStr_noSecond;
    }

    /**
     * 格式是20181018或者是2018年10月18日，转为10月18日
     *
     * @param datestr
     * @return
     */
    public static String convertDate(String datestr) {
        if (datestr != null) {
            if (datestr.contains("年")) {
                return datestr.substring(5, datestr.length());
            } else {
                String years = datestr.substring(0, 4);
                String month = datestr.substring(4, 6);
                String day = datestr.substring(6, 8);
                return month + "月" + day + "日";
            }

        }
        return null;
    }

    /**
     * 2019-2-19 22:10 格式转换天数
     *
     * @param date
     * @return
     */
    public static int dateConvertToNumday(String date) {
        String[] data = date.split(" ");
        if (data.length > 0) {
            String datestr = data[0];
            String[] date_str = datestr.split("-");
            if (date_str.length > 0) {
                int years = Integer.parseInt(date_str[0]);
                int moth = Integer.parseInt(date_str[1]);
                int day = Integer.parseInt(date_str[2]);
                return years * 365 + moth * 30 + day;
            }
        }
        return -1;
    }

    public static int compareDays(String date) {
        int value = dateConvertToNumday(date) - currentDateConvertToNumday();
        if (value == 0) {
            return 0;
        } else if (value < 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 2019-2-19格式转换天数
     *
     * @return
     */
    public static int currentDateConvertToNumday() {
        String[] datestr = getCurrentDate().split("-");
        if (datestr.length > 0) {
            int years = Integer.parseInt(datestr[0]);
            int moth = Integer.parseInt(datestr[1]);
            int day = Integer.parseInt(datestr[2]);
            return years * 365 + moth * 30 + day;
        }
        return -1;
    }

    public static int getCurrentWeekIndex() {
        Calendar cal = Calendar.getInstance();
        int i = -1;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            date = dateFormat.parse(getCurrentDate());
            cal.setTime(date);
            i = cal.get(Calendar.DAY_OF_WEEK);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int getMinuteByTimestemp(long timestemp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestemp);
        return cal.get(Calendar.MINUTE);
    }

    public static int getHourByTimestemp(long timestemp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestemp);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getDayByTimestemp(long timestemp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestemp);
        return cal.get(DAY_OF_MONTH);
    }

    public static int getMonthByTimestemp(long timestemp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestemp);
        int month = cal.get(Calendar.MONTH);
        return month;
    }

    public static int getWeekByTimestemp(long timestemp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestemp);
        int i = -1;
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String getCurrentWeek() {
        Calendar cal = Calendar.getInstance();
        int i = -1;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            date = dateFormat.parse(getCurrentDate());
            cal.setTime(date);
            i = cal.get(Calendar.DAY_OF_WEEK);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (i != -1) {
            switch (i) {
                case 1:
                    return "周日";
                case 2:
                    return "周一";
                case 3:
                    return "周二";
                case 4:
                    return "周三";
                case 5:
                    return "周四";
                case 6:
                    return "周五";
                case 7:
                    return "周六";
            }

        }
        return null;
    }

    public static String getCurrentWeek3(String data) {
        Calendar cal = Calendar.getInstance();
        int i = -1;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            date = dateFormat.parse(data);
            cal.setTime(date);
            i = cal.get(Calendar.DAY_OF_WEEK);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (i != -1) {
            switch (i) {
                case 1:
                    return "周日";
                case 2:
                    return "周一";
                case 3:
                    return "周二";
                case 4:
                    return "周三";
                case 5:
                    return "周四";
                case 6:
                    return "周五";
                case 7:
                    return "周六";
            }

        }
        return null;
    }

    public static int WEEK_START = 1;
    public static int WEEK_END = 2;

    public static String getWeekStartOrEnd(int type) {
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        int i;
        cal.setTime(d);
        i = cal.get(Calendar.DAY_OF_WEEK);
        if (type == WEEK_START) {
            if (i == 1) {
                i = 8;
            }
            int distance = (i - 1) - 1;//前面的-1：把周一设置为1
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - distance);
            return cal.get(Calendar.MONTH) + 1 + "月" + cal.get(Calendar.DAY_OF_MONTH) + "日";
        } else {
            if (i == 1) {
                i = 8;
            }
            int distance = 7 - (i - 1); //-1：把周一设置为1
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) + distance);
            return cal.get(Calendar.MONTH) + 1 + "月" + cal.get(Calendar.DAY_OF_MONTH) + "日";
        }
    }

    public static String getCurrentWeek2() {
        Calendar cal = Calendar.getInstance();
        int i = -1;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            date = dateFormat.parse(getCurrentDate());
            cal.setTime(date);
            i = cal.get(Calendar.DAY_OF_WEEK);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (i != -1) {
            switch (i) {
                case 1:
                    return "星期天";
                case 2:
                    return "星期一";
                case 3:
                    return "星期二";
                case 4:
                    return "星期三";
                case 5:
                    return "星期四";
                case 6:
                    return "星期五";
                case 7:
                    return "星期六";
            }

        }
        return null;
    }

    /**
     * 返回2019/01
     *
     * @return
     */
    public static String getYearMonth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getDayOfMonth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date).split("-")[2];
    }

    public static String getTimeZone(String timezone) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        Date mDate = new Date(System.currentTimeMillis());
        String dateStrTmp = dateFormat.format(mDate);
        System.out.println("dateStrTmp：" + dateStrTmp);
        return dateStrTmp;
    }

    //获取精确到毫秒的当前时间
    public static String getCurrentTimeTomilliseconds() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date mDate = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(mDate);
        return time;
    }

    //计算两个时间差多少
    public static String getTimeDifference(String start, String end) {
        String time = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date beginTime = simpleDateFormat.parse(start);
            Date endTime = simpleDateFormat.parse(end);
            long diff = endTime.getTime() - beginTime.getTime();
            /*计算天数*/
            long days = diff / (1000 * 60 * 60 * 24);
            /*计算小时*/
            long hours = (diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            /*计算分钟*/
            long minutes = (diff % (1000 * 60 * 60)) / (1000 * 60);
            /*计算秒*/
            long seconds = (diff % (1000 * 60)) / 1000;
            time = hours + "小时";
            if (diff > 0) {
                time = "+" + time;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }

    //计算两个时间差多少
    public static long getTimeDifference(String start, String end, String pattern, DateDistance distanceType) {
        String time = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date beginTime = simpleDateFormat.parse(start);
            Date endTime = simpleDateFormat.parse(end);
            long diff = Math.abs(endTime.getTime() - beginTime.getTime());
            if (distanceType == DateDistance.DAY) {
                return diff / (1000 * 60 * 60 * 24);
            } else if (distanceType == DateDistance.HOUR) {
                return (diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            } else if (distanceType == DateDistance.MINUTE) {
                return (diff % (1000 * 60 * 60)) / (1000 * 60);
            } else if (distanceType == DateDistance.SECOND) {
                return (diff % (1000 * 60)) / 1000;
            } else {
                return -1L;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    /**
     * 获取指定时间之后多少时间的时间
     *
     * @param dateType    时间格式 yyyy-MM-dd HH:mm:ss
     * @param currentTime 当前时间
     * @param delay       秒
     * @return
     */
    public static String delayTime2(String dateType, String currentTime, long delay) {
        SimpleDateFormat format = new SimpleDateFormat(dateType);//24小时制
        try {
            long current = format.parse(currentTime).getTime();
            return format.format(new Date(current + delay));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 计算当前时间是本年度的第几周
     */
    public static int getWeek() {
        Calendar cal = Calendar.getInstance();//这一句必须要设置，否则美国认为第一天是周日，而我国认为是周一，对计算当期日期是第几周会有错误
        cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
        cal.setMinimalDaysInFirstWeek(7); // 设置每周最少为7天
        cal.setTime(new Date());
        int weeks = cal.get(Calendar.WEEK_OF_YEAR);
        return weeks;
    }

    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 获取前n天日期、后n天日期
     *
     * @param startDate   开始日期
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static String getOldDate(String startDate, int distanceDay) throws ParseException {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = dft.parse(startDate);
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    public static String getOldDate2(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy年MM月dd日");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.get(Calendar.YEAR) + "年" + (date.get(Calendar.MONTH) + 1) + "月" + date.get(Calendar.DAY_OF_MONTH) + "日";

    }

    /**
     * 计算两个日期之间的yyyy-MM-dd的list
     *
     * @param startTime 小日期
     * @param endTime   大日期
     * @return
     */
    public static List<String> getListDays(String startTime, String endTime) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public static List<String> getListDays(String startTime, String endTime, boolean isIncludeLastDate) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            if (isIncludeLastDate) {
                tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            }

            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
    //获取指定日期/当前日期的前后多少天

    /**
     * @param distance 正往后几天，负往前几天
     * @param baseDate 基准日期
     * @return
     */
    public static List<String> getListDay(int distance, String baseDate) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        if (baseDate == null || baseDate.equals("")) {
            String endDate = getOldDate(distance);
            if (distance >= 0) {
                return getListDays(dft.format(System.currentTimeMillis()), endDate, false);
            } else {
                return getListDays(endDate, dft.format(System.currentTimeMillis()), false);
            }
        } else {
            try {
                String endDate = getOldDate(baseDate, distance);
                if (distance >= 0) {
                    return getListDays(baseDate, endDate, false);
                } else {
                    return getListDays(endDate, baseDate, false);
                }

            } catch (ParseException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }

    }

    /**
     * 获得指定日期的前若干天
     *
     * @param calendar
     * @param offset
     * @return
     */
    public static Calendar standardDate(Calendar calendar, int offset) {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        int day = calendar.get(Calendar.DATE);

        Calendar _calendar = Calendar.getInstance();
        _calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        _calendar.set(Calendar.DATE, day + offset);
        return _calendar;
    }

    /**
     * 判断给定日期是否为当前日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static boolean isCurrentDate(int year, int month, int day) {
        Calendar mCalendar = Calendar.getInstance();
        return (year == mCalendar.get(Calendar.YEAR))
                && (month == mCalendar.get(Calendar.MONTH) + 1) && (day == mCalendar.get(Calendar.DATE));
    }

    /**
     * 将date转换成String
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");//日期格式
        String tiem = sformat.format(date) + " 00:00";
        return tiem;
    }

    /**
     * 将date转换成String
     *
     * @param date
     * @return
     */
    public static String dateToString2(Date date) {
        SimpleDateFormat sformat = new SimpleDateFormat("HH:mm");//日期格式
        String tiem = sformat.format(date);
        return tiem;
    }

    /**
     * 将date转换成String
     *
     * @param date
     * @return
     */
    public static String dateToString3(Date date) {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//日期格式
        String tiem = sformat.format(date);
        return tiem;
    }

    /**
     * 将date转换成String
     *
     * @param date
     * @return
     */
    public static String dateToStringToHour(Date date) {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH");//日期格式
        String tiem = sformat.format(date);
        return tiem;
    }

    /**
     * yyyy-MM-dd HH:MM
     *
     * @param
     * @return
     */
    public static Date stringToDate(String datetime) {
        Date date = null;
        try {
            SimpleDateFormat sformatHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");//日期格式
            date = sformatHM.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * date 均为yyyy-MM-dd HH:MM
     * 比较两日期的大小
     * date1小于date2返回-1，date1大于date2返回1，相等返回0
     */
    public static int dateCompareTo(Date date1, Date date2) {
        SimpleDateFormat sformatHM = new SimpleDateFormat("yyyy-MM-dd");
        int compareTo = sformatHM.format(date1).compareTo(sformatHM.format(date2));
        return compareTo;
    }

    /**
     * 根据date 放回 年/月/日 星期几
     */
    public static String dateToWeekDay(Date date) {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy/MM/dd");//日期格式
        String tiem = sformat.format(date);
        Calendar cal = Calendar.getInstance();
        int i;
        cal.setTime(date);
        i = cal.get(Calendar.DAY_OF_WEEK);
        String weektime = "";
        if (i != -1) {
            switch (i) {
                case 1:
                    weektime = "星期天";
                    break;
                case 2:
                    weektime = "星期一";
                    break;
                case 3:
                    weektime = "星期二";
                    break;
                case 4:
                    weektime = "星期三";
                    break;
                case 5:
                    weektime = "星期四";
                    break;
                case 6:
                    weektime = "星期五";
                    break;
                case 7:
                    weektime = "星期六";
                    break;
                default:
                    break;
            }

        }
        return tiem + " " + weektime;
    }

    public static int daysBetween(Date smdate, Date bdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = Math.abs(time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int daysBetween(String smdate, String bdate) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(s.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(s.parse(bdate));
            long time2 = cal.getTimeInMillis();
            long between_days = Math.abs(time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * 将10 or 13 位时间戳转为时间字符串
     * convert the number 1407449951 1407499055617 to date/time format timestamp
     */
    public static String timestamp2Date(String str_num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (str_num.length() == 13) {
            String date = sdf.format(new Date(Long.parseLong(str_num)));
            return date;
        } else {
            String date = sdf.format(new Date(Integer.parseInt(str_num) * 1000L));
            return date;
        }
    }

    //根据时间判断是不是今天
    public static Boolean handleDate(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date old = sdf.parse(time);
        Date now = sdf.parse(sdf.format(new Date()));
        long oldTime = old.getTime();
        long nowTime = now.getTime();
        long day = (nowTime - oldTime) / (24 * 60 * 60 * 1000);
        if (day < 1) {  //今天
            return true;
//        } else  {     //昨天
//            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//            return "昨天 " + format.format(date);
        } else {    //可依次类推
            return false;
        }
    }
}

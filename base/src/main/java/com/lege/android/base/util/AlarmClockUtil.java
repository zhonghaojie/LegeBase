package com.lege.android.base.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lege.android.base.constants.ReceiverActionConstant;
import com.lege.android.base.log.APPLog;

import java.util.Calendar;

/**
 * Created by zhoushaoqing on 18-12-18.
 */

public class AlarmClockUtil {

    //日程闹钟的头部
    public static String HEAD_SCHEDULE = "99";
    //提醒闹钟的头部
    public static String HEAD_REMIND = "8";
    //闹钟头部
    public static String HEAD_ALARM = "77";
    //通勤闹钟头部
    public static String HEAD_COMMUTE = "66";

    /**
     * 设置通勤闹钟
     * head 77加到id最前面
     *
     * @param context
     * @param hourOfDay
     * @param minute
     */

    public static void setCommuteSingleAlarmAlart(Context context,String type, int alarmid, int hourOfDay, int minute,  String ringingid) {
        String head = HEAD_COMMUTE;
        int finalAlarmidid = Integer.parseInt(head + alarmid);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        APPLog.log("=hourOfDay=" + hourOfDay + "=minute=" + minute);
        Intent intent = new Intent(ReceiverActionConstant.ACTION_COMMUTE_ALARM);
        intent.putExtra("alarmid", alarmid);
        intent.putExtra("time", "" + hourOfDay + ":" + (minute >= 10 ? minute : "0" + minute));
        intent.putExtra("ringingid", ringingid);//铃声id号
        intent.putExtra("type",type );
        intent.putExtra("requestcode", finalAlarmidid);
        PendingIntent sender = PendingIntent.getBroadcast(
                context, finalAlarmidid, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am;
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        APPLog.log("闹钟   " + c.get(Calendar.DAY_OF_WEEK) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + "");
        am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
    }

    /**
     * 取消通勤闹钟
     * @param context
     * @param alarmid
     */
    public static void deletCommuteSingleAlarmAlart(Class<? extends BroadcastReceiver> br, Context context, int alarmid) {
        //删除闹钟
        deleteClock(br,context, Integer.parseInt(HEAD_COMMUTE + alarmid));
    }





    /**
     * 设置闹钟
     * head 77加到id最前面
     *
     * @param context
     * @param hourOfDay
     * @param minute
     */

    public static void setSingleAlarmAlart(Class<? extends BroadcastReceiver> br, Context context, String type,int alarmid, int hourOfDay, int minute, String repeat, String ringingid) {
        String head = HEAD_ALARM;
        int finalAlarmidid = Integer.parseInt(head + alarmid);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        APPLog.log("=hourOfDay=" + hourOfDay + "=minute=" + minute);
        Intent intent = new Intent(context, br);
        intent.setAction(ReceiverActionConstant.ACTION_ALARM_ALARM);
        intent.putExtra("alarmid", alarmid);
        intent.putExtra("time", "" + hourOfDay + ":" + (minute >= 10 ? minute : "0" + minute));
        intent.putExtra("ringingid", ringingid);//铃声id号
        intent.putExtra("type",type);
        intent.putExtra("requestcode", finalAlarmidid);
        PendingIntent sender = PendingIntent.getBroadcast(
                context, finalAlarmidid, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am;
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
    }

    public static void deletSingleAlarmAlart(Class<? extends BroadcastReceiver> br,Context context, int alarmid, int hourOfDay, int minute) {
        //删除闹钟
        deleteClock(br,context, Integer.parseInt(HEAD_ALARM + alarmid));
    }

    /**
     * head 99加到id最前面
     *
     * @param context
     * @param year
     * @param month
     * @param day
     * @param scheduleid
     * @param hourOfDay
     * @param minute
     */
    public static void setSingleScheduleAlart(Class<? extends BroadcastReceiver> br,Context context,String type, String title, int scheduleid, int year, int month, int day, int hourOfDay, int minute) {
        String head = HEAD_SCHEDULE;
        int finalscheduleid = Integer.parseInt(head + scheduleid);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        APPLog.log("year==" + year + " month=" + month + " day=" + day + " hourOfDay=" + hourOfDay + "=minute=" + minute);
        APPLog.log("Calendar  year==" + c.get(Calendar.YEAR) + " month=" + c.get(Calendar.MONTH) + " day=" + c.get(Calendar.DAY_OF_MONTH) + " hourOfDay=" + c.get(Calendar.HOUR_OF_DAY) + "=minute=" + c.get(Calendar.MINUTE));
        Intent intent = new Intent(context, br);
        intent.setAction(ReceiverActionConstant.ACTION_SCHEDULE_ALARM);
        intent.putExtra("scheduleid", scheduleid);
        intent.putExtra("type", type);
        intent.putExtra("title", title);
        intent.putExtra("requestcode", finalscheduleid);
        APPLog.log("setSingleScheduleAlart====" + finalscheduleid);
        PendingIntent sender = PendingIntent.getBroadcast(
                context, finalscheduleid, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am;
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        APPLog.log("闹钟" + c.get(Calendar.DAY_OF_WEEK) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + "");
        am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
    }

    public static void setRepeatScheduleAlart(Class<? extends BroadcastReceiver> br,Context context,String type, String title, int scheduleid, int year, int month, int day, int hourOfDay, int minute, long duration) {
        String head = HEAD_SCHEDULE;
        int finalscheduleid = Integer.parseInt(head + scheduleid);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        APPLog.log("year==" + year + " month=" + month + " day=" + day + " hourOfDay=" + hourOfDay + "=minute=" + minute);
        APPLog.log("Calendar  year==" + c.get(Calendar.YEAR) + " month=" + c.get(Calendar.MONTH) + " day=" + c.get(Calendar.DAY_OF_MONTH) + " hourOfDay=" + c.get(Calendar.HOUR) + "=minute=" + c.get(Calendar.MINUTE));
        Intent intent = new Intent(context,br);
        intent.setAction(ReceiverActionConstant.ACTION_SCHEDULE_ALARM);
        intent.putExtra("scheduleid", scheduleid);
        intent.putExtra("type", type);
        intent.putExtra("title", title);
        intent.putExtra("requestcode", finalscheduleid);
        APPLog.log("setSingleScheduleAlart====" + finalscheduleid);
        PendingIntent sender = PendingIntent.getBroadcast(
                context, finalscheduleid, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am;
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), duration, sender);
    }

    /**
     * 设置重复闹钟
     *
     * @param context
     * @param hourOfDay
     * @param minute
     */
    public void setRepeatAlarmAlart(Class<? extends BroadcastReceiver> br, Context context, int alarmid, int year, int month, int day, int hourOfDay, int minute, long duration) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Intent intent = new Intent(context, br);
        intent.putExtra("alarmid", alarmid);
        intent.putExtra("time", "" + hourOfDay + ":" + (minute >= 10 ? minute : "0" + minute));
        PendingIntent sender = PendingIntent.getBroadcast(
                context, alarmid, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am;
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //每天重复闹钟
        am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), duration, sender);
        //AlarmManager.INTERVAL_FIFTEEN_MINUTES  间隔15分钟
        //AlarmManager.INTERVAL_HALF_HOUR        间隔半个小时
        //AlarmManager.INTERVAL_HOUR             间隔一个小时
        //AlarmManager.INTERVAL_HALF_DAY         间隔半天
        //AlarmManager.INTERVAL_DAY              间隔一天
    }


    /**
     * head 8加到id最前面
     * 设置重复提醒
     */
    public static void setRepeatReminder(Class<? extends BroadcastReceiver> br,Context context,String type, int reminderid, int remindertype, int starthourOfDay, int startminute, int endhourOfDay, int endminute, int interval, String title) {
        String head = HEAD_REMIND;
        int step = 1000;
        int startTotalMinute = starthourOfDay * 60 + startminute;
        int endTotalMinute = endhourOfDay * 60 + endminute;
        int alarmCount = (endTotalMinute - startTotalMinute) / interval;
        for (int i = 0; i < alarmCount; i++) {
            int hourOfDay = (startTotalMinute + i * interval) / 60;
            int minute = (startTotalMinute + i * interval) % 60;
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            int tempcode = reminderid * step + i;
            String requestcodestr = head + tempcode;
            Intent intent = new Intent(context, br);
            intent.setAction(ReceiverActionConstant.ACTION_REMINDER_ALARM);
            intent.putExtra("reminderid", reminderid);
            intent.putExtra("remindertype", remindertype);
            intent.putExtra("title", title);
            intent.putExtra("type", type);
            intent.putExtra("requestcode", Integer.parseInt(requestcodestr));
            APPLog.log("闹钟" + "requestcode   "+Integer.parseInt(requestcodestr));
            PendingIntent sender = PendingIntent.getBroadcast(
                    context, Integer.parseInt(requestcodestr), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am;
            am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            //每天重复闹钟
            APPLog.log("闹钟" + c.get(Calendar.DAY_OF_WEEK) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + "");
            am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
        }
    }

    /**
     * setp是为了跟闹钟区分
     *
     * @param context
     * @param reminderid
     * @param starthourOfDay
     * @param startminute
     * @param endhourOfDay
     * @param endminute
     * @param interval
     */
    public static void deleteRepeatReminder(Class<? extends BroadcastReceiver> br,Context context, int reminderid, int starthourOfDay, int startminute, int endhourOfDay, int endminute, int interval) {
        int step = 1000;
        String head = HEAD_REMIND;
        int startTotalMinute = starthourOfDay * 60 + startminute;
        int endTotalMinute = endhourOfDay * 60 + endminute;
        int alarmCount = (endTotalMinute - startTotalMinute) / interval;
        for (int i = 0; i < alarmCount; i++) {
            int tempcode = reminderid * step + i;
            String requestcode = head + tempcode;
            int alarmRequestcode = Integer.parseInt(requestcode);
            deleteReminderClock(br,context, alarmRequestcode);
        }
    }

    /**
     * 删除闹钟
     *
     * @param context
     */
    public static void deleteClock(Class<? extends BroadcastReceiver> br, Context context, int alarmRequestcode) {
        APPLog.log("deleteClock===alarmRequestcode====" + alarmRequestcode);
        Intent intent = new Intent(context, br);
        intent.setAction(ReceiverActionConstant.ACTION_ALARM_ALARM);
        PendingIntent sender = PendingIntent.getBroadcast(
                context, alarmRequestcode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am;
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(sender);
    }

    /**
     * 删除日程闹钟
     *
     * @param context
     */
    public static void deleteScheduleClock(Class<? extends BroadcastReceiver> br,Context context, int alarmRequestcode) {
        APPLog.log("删除日程","deleteScheduleClock===alarmRequestcode====" + alarmRequestcode);
        Intent intent = new Intent(context, br);
        intent.setAction(ReceiverActionConstant.ACTION_SCHEDULE_ALARM);
        PendingIntent sender = PendingIntent.getBroadcast(
                context, alarmRequestcode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am;
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(sender);
    }

    /**
     * 删除提醒闹钟
     *
     * @param context
     */
    public static void deleteReminderClock(Class<? extends BroadcastReceiver> br,Context context, int alarmRequestcode) {
//        Log.i("zhou", "deleteReminderClock===alarmRequestcode====" + alarmRequestcode);
        Intent intent = new Intent(context, br);
        intent.setAction(ReceiverActionConstant.ACTION_REMINDER_ALARM);
        PendingIntent sender = PendingIntent.getBroadcast(
                context, alarmRequestcode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am;
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(sender);
    }
}

package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * Description:
 * Created by zhonghaojie on 2019-11-04.
 */
@Entity
public class TaskBean {
    @Id
    private Long id;

    /**
     * 任务id
     */
    @Index(unique = true)
    private int taskId;
    /**
     * 任务类型
     */
    private String type;
    private String subject;
    /**
     * datetime的日期
     */
    private String date;
    private String datetime;
    /**
     * 任务状态  已完成finished、延后delay、已删除del
     */
    private String state;
    /**
     * 是否是重复任务(闹钟、提醒等)
     */
    private boolean isRepeate;
    /**
     * 闹钟、提醒的重复类型  每天的就是0,1,2,3,4,5,6,7对应周日到周六，7是闹钟的单次
     */
    private String repeateType;
    /**
     * 是否已读
     */
    private boolean isRead;
    /**
     * 设置系统闹钟的id
     */
    private String alarmRequestCode;
    /**
     * 提醒时间 和advance一样
     */
    private String alertTime;
    /**
     * 延后之后的提醒时间  list里点击延后操作会赋值
     */
    private String delayAlertTime;
    /**
     * 日程：提前多久提醒
     */
    private int advance;
    /**
     * 日程 是否已提醒
     */
    private boolean isReminded;
    /**
     * 提醒的间隔
     */
    private int remindInterval;
    /**
     * 提醒的类型，久坐还是喝水提醒这种
     */
    private int remindType;
    /**
     * 提醒开始时间
     */
    private String remindStartTime;
    /**
     * 提醒结束时间
     */
    private String remindEndTime;
    /**
     * 闹钟的铃声类型
     */
    private int alarmRingType;
    /**
     * 闹钟是否启用
     */
    private int alarmIsStart ;
    /**
     * 日程、待办开始时间
     */
    private String startTime;
    /**
     * 日程、待办结束时间
     */
    private String endTime;

    public boolean isRepeate() {
        return isRepeate;
    }

    public void setRepeate(boolean repeate) {
        isRepeate = repeate;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isReminded() {
        return isReminded;
    }

    public void setReminded(boolean reminded) {
        isReminded = reminded;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean getIsRead() {
        return this.isRead;
    }
    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
    public String getRepeateType() {
        return this.repeateType;
    }
    public void setRepeateType(String repeateType) {
        this.repeateType = repeateType;
    }
    public boolean getIsRepeate() {
        return this.isRepeate;
    }
    public void setIsRepeate(boolean isRepeate) {
        this.isRepeate = isRepeate;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getDatetime() {
        return this.datetime;
    }
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    public String getSubject() {
        return this.subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAlertTime() {
        return this.alertTime;
    }
    public void setAlertTime(String alertTime) {
        this.alertTime = alertTime;
    }
    public String getAlarmRequestCode() {
        return this.alarmRequestCode;
    }
    public void setAlarmRequestCode(String alarmRequestCode) {
        this.alarmRequestCode = alarmRequestCode;
    }
    public int getTaskId() {
        return this.taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public String getDelayAlertTime() {
        return this.delayAlertTime;
    }
    public void setDelayAlertTime(String delayAlertTime) {
        this.delayAlertTime = delayAlertTime;
    }
    public int getAdvance() {
        return this.advance;
    }
    public void setAdvance(int advance) {
        this.advance = advance;
    }
    public int getAlarmRingType() {
        return this.alarmRingType;
    }
    public void setAlarmRingType(int alarmRingType) {
        this.alarmRingType = alarmRingType;
    }
    public int getRemindType() {
        return this.remindType;
    }
    public void setRemindType(int remindType) {
        this.remindType = remindType;
    }
    public int getRemindInterval() {
        return this.remindInterval;
    }
    public void setRemindInterval(int remindInterval) {
        this.remindInterval = remindInterval;
    }
    public boolean getIsReminded() {
        return this.isReminded;
    }
    public void setIsReminded(boolean isReminded) {
        this.isReminded = isReminded;
    }
    public String getRemindEndTime() {
        return this.remindEndTime;
    }
    public void setRemindEndTime(String remindEndTime) {
        this.remindEndTime = remindEndTime;
    }
    public String getRemindStartTime() {
        return this.remindStartTime;
    }
    public void setRemindStartTime(String remindStartTime) {
        this.remindStartTime = remindStartTime;
    }
    public int getAlarmIsStart() {
        return this.alarmIsStart;
    }
    public void setAlarmIsStart(int alarmIsStart) {
        this.alarmIsStart = alarmIsStart;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    @Generated(hash = 1913524438)
    public TaskBean(Long id, int taskId, String type, String subject, String date,
            String datetime, String state, boolean isRepeate, String repeateType,
            boolean isRead, String alarmRequestCode, String alertTime,
            String delayAlertTime, int advance, boolean isReminded,
            int remindInterval, int remindType, String remindStartTime,
            String remindEndTime, int alarmRingType, int alarmIsStart,
            String startTime, String endTime) {
        this.id = id;
        this.taskId = taskId;
        this.type = type;
        this.subject = subject;
        this.date = date;
        this.datetime = datetime;
        this.state = state;
        this.isRepeate = isRepeate;
        this.repeateType = repeateType;
        this.isRead = isRead;
        this.alarmRequestCode = alarmRequestCode;
        this.alertTime = alertTime;
        this.delayAlertTime = delayAlertTime;
        this.advance = advance;
        this.isReminded = isReminded;
        this.remindInterval = remindInterval;
        this.remindType = remindType;
        this.remindStartTime = remindStartTime;
        this.remindEndTime = remindEndTime;
        this.alarmRingType = alarmRingType;
        this.alarmIsStart = alarmIsStart;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Generated(hash = 1443476586)
    public TaskBean() {
    }
    
}

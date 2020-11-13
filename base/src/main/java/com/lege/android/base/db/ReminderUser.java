package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class ReminderUser {
    @Id
    private Long id;
    //日常提醒
    private int remindertype;
    private String remindersubject;
    private String reminderstarttime;
    private String reminderendtime;
    private String reminderrepeat;
    private String reminderinterval;
    private int isStart;
    @Index(unique = true)
    private int remindid;
    public int getRemindid() {
        return this.remindid;
    }
    public void setRemindid(int remindid) {
        this.remindid = remindid;
    }
    public int getIsStart() {
        return this.isStart;
    }
    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }
    public String getReminderinterval() {
        return this.reminderinterval;
    }
    public void setReminderinterval(String reminderinterval) {
        this.reminderinterval = reminderinterval;
    }
    public String getReminderrepeat() {
        return this.reminderrepeat;
    }
    public void setReminderrepeat(String reminderrepeat) {
        this.reminderrepeat = reminderrepeat;
    }
    public String getReminderendtime() {
        return this.reminderendtime;
    }
    public void setReminderendtime(String reminderendtime) {
        this.reminderendtime = reminderendtime;
    }
    public String getReminderstarttime() {
        return this.reminderstarttime;
    }
    public void setReminderstarttime(String reminderstarttime) {
        this.reminderstarttime = reminderstarttime;
    }
    public String getRemindersubject() {
        return this.remindersubject;
    }
    public void setRemindersubject(String remindersubject) {
        this.remindersubject = remindersubject;
    }
    public int getRemindertype() {
        return this.remindertype;
    }
    public void setRemindertype(int remindertype) {
        this.remindertype = remindertype;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1272514911)
    public ReminderUser(Long id, int remindertype, String remindersubject,
            String reminderstarttime, String reminderendtime,
            String reminderrepeat, String reminderinterval, int isStart,
            int remindid) {
        this.id = id;
        this.remindertype = remindertype;
        this.remindersubject = remindersubject;
        this.reminderstarttime = reminderstarttime;
        this.reminderendtime = reminderendtime;
        this.reminderrepeat = reminderrepeat;
        this.reminderinterval = reminderinterval;
        this.isStart = isStart;
        this.remindid = remindid;
    }
    @Generated(hash = 141496892)
    public ReminderUser() {
    }
}

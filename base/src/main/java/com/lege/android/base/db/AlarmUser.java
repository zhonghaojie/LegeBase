package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class AlarmUser {
    @Id
    private Long id;
    private int alarmid ;
    private String time ;
    private String repeat ;
    private String ringingid;
    private int isStart;
    private int skip_holiday;
    private String startdate;
    private String tag;
    private int type;
    public String getStartdate() {
        return this.startdate;
    }
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
    public int getIsStart() {
        return this.isStart;
    }
    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }
    public String getRingingid() {
        return this.ringingid;
    }
    public void setRingingid(String ringingid) {
        this.ringingid = ringingid;
    }
    public String getRepeat() {
        return this.repeat;
    }
    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getAlarmid() {
        return this.alarmid;
    }
    public void setAlarmid(int alarmid) {
        this.alarmid = alarmid;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTag() {
        return this.tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public int getSkip_holiday() {
        return this.skip_holiday;
    }
    public void setSkip_holiday(int skip_holiday) {
        this.skip_holiday = skip_holiday;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    @Generated(hash = 1169194162)
    public AlarmUser(Long id, int alarmid, String time, String repeat,
            String ringingid, int isStart, int skip_holiday, String startdate,
            String tag, int type) {
        this.id = id;
        this.alarmid = alarmid;
        this.time = time;
        this.repeat = repeat;
        this.ringingid = ringingid;
        this.isStart = isStart;
        this.skip_holiday = skip_holiday;
        this.startdate = startdate;
        this.tag = tag;
        this.type = type;
    }
    @Generated(hash = 1160523540)
    public AlarmUser() {
    }
}

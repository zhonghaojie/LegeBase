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
    private String startdate;
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
    @Generated(hash = 670475733)
    public AlarmUser(Long id, int alarmid, String time, String repeat,
            String ringingid, int isStart, String startdate) {
        this.id = id;
        this.alarmid = alarmid;
        this.time = time;
        this.repeat = repeat;
        this.ringingid = ringingid;
        this.isStart = isStart;
        this.startdate = startdate;
    }
    @Generated(hash = 1160523540)
    public AlarmUser() {
    }
}

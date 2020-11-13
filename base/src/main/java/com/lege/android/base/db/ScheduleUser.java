package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class ScheduleUser {
    @Id
    private Long id;
    @Index(unique = true)
    private int scheduleid;
    private String startdate;
    private String starttime;
    private String enddate;
    private String endtime;
    private String title;
    private int is_allday;
    private String alert;
    private String readed;
    private String isReminded;
    private String time;
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getIsReminded() {
        return this.isReminded;
    }
    public void setIsReminded(String isReminded) {
        this.isReminded = isReminded;
    }
    public String getReaded() {
        return this.readed;
    }
    public void setReaded(String readed) {
        this.readed = readed;
    }
    public String getAlert() {
        return this.alert;
    }
    public void setAlert(String alert) {
        this.alert = alert;
    }
    public int getIs_allday() {
        return this.is_allday;
    }
    public void setIs_allday(int is_allday) {
        this.is_allday = is_allday;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getEndtime() {
        return this.endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getEnddate() {
        return this.enddate;
    }
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    public String getStarttime() {
        return this.starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getStartdate() {
        return this.startdate;
    }
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
    public int getScheduleid() {
        return this.scheduleid;
    }
    public void setScheduleid(int scheduleid) {
        this.scheduleid = scheduleid;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1123082903)
    public ScheduleUser(Long id, int scheduleid, String startdate,
            String starttime, String enddate, String endtime, String title,
            int is_allday, String alert, String readed, String isReminded,
            String time) {
        this.id = id;
        this.scheduleid = scheduleid;
        this.startdate = startdate;
        this.starttime = starttime;
        this.enddate = enddate;
        this.endtime = endtime;
        this.title = title;
        this.is_allday = is_allday;
        this.alert = alert;
        this.readed = readed;
        this.isReminded = isReminded;
        this.time = time;
    }
    @Generated(hash = 566555504)
    public ScheduleUser() {
    }
}

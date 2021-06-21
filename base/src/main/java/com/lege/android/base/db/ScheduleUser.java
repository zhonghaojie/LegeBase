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
    private String remark;
    private Long add_time;
    private int user_id;
    private String device_sn;
    private int tag;
    private int is_delete;
    private String human_date;
    private String type_id;
    private String trip_info;
    private String trip_id;

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getTrip_info() {
        return trip_info;
    }

    public void setTrip_info(String trip_info) {
        this.trip_info = trip_info;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDevice_sn() {
        return device_sn;
    }

    public void setDevice_sn(String device_sn) {
        this.device_sn = device_sn;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public String getHuman_date() {
        return human_date;
    }

    public void setHuman_date(String human_date) {
        this.human_date = human_date;
    }

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
    @Generated(hash = 1725706348)
    public ScheduleUser(Long id, int scheduleid, String startdate,
            String starttime, String enddate, String endtime, String title,
            int is_allday, String alert, String readed, String isReminded,
            String time, String remark, Long add_time, int user_id,
            String device_sn, int tag, int is_delete, String human_date,
            String type_id, String trip_info, String trip_id) {
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
        this.remark = remark;
        this.add_time = add_time;
        this.user_id = user_id;
        this.device_sn = device_sn;
        this.tag = tag;
        this.is_delete = is_delete;
        this.human_date = human_date;
        this.type_id = type_id;
        this.trip_info = trip_info;
        this.trip_id = trip_id;
    }

    @Generated(hash = 566555504)
    public ScheduleUser() {
    }
}

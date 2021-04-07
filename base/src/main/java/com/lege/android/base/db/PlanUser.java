package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by zhoushaoqing on 18-12-1.
 */
@Entity
public class PlanUser {
    @Id
    private Long id;
    @Index(unique = true)
    private int taskid;
    private String subject;
    private String duedate;
    private int status;
    private String human_date;
    private String device_sn;
    private int tag;
    private int is_delete;
    private int user_id;
    private Long add_time;

    public String getHuman_date() {
        return human_date;
    }

    public void setHuman_date(String human_date) {
        this.human_date = human_date;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    public String getDuedate() {
        return this.duedate;
    }
    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
    public String getSubject() {
        return this.subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public int getTaskid() {
        return this.taskid;
    }
    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    @Generated(hash = 1367105404)
    public PlanUser(Long id, int taskid, String subject, String duedate, int status) {
        this.id = id;
        this.taskid = taskid;
        this.subject = subject;
        this.duedate = duedate;
        this.status = status;
    }
    @Generated(hash = 2114241025)
    public PlanUser() {
    }
}

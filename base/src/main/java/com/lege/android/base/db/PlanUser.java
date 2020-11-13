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

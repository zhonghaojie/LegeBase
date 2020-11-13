package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by zhoushaoqing on 18-12-1.
 */
@Entity
public class GlobalClockUser {
    @Id
    private Long id;
    @Index(unique = true)
    private int GlobalClockid;
    private String timezone;
    private String gmt;
    public String getGmt() {
        return this.gmt;
    }
    public void setGmt(String gmt) {
        this.gmt = gmt;
    }
    public String getTimezone() {
        return this.timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public int getGlobalClockid() {
        return this.GlobalClockid;
    }
    public void setGlobalClockid(int GlobalClockid) {
        this.GlobalClockid = GlobalClockid;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 578903638)
    public GlobalClockUser(Long id, int GlobalClockid, String timezone, String gmt) {
        this.id = id;
        this.GlobalClockid = GlobalClockid;
        this.timezone = timezone;
        this.gmt = gmt;
    }
    @Generated(hash = 246440757)
    public GlobalClockUser() {
    }
}

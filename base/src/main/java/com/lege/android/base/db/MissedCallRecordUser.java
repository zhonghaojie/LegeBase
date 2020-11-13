package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class MissedCallRecordUser {
    @Id
    private Long id;
    private String tel;
    private String employee;
    private String avatar;
    private String device_sn;
    private String position;
    private String department;
    private int count;
    private String readed;//是否已读 yes已读，no未读
    private String insertiontime;// 插入时间
    public String getReaded() {
        return this.readed;
    }
    public void setReaded(String readed) {
        this.readed = readed;
    }
    public String getDepartment() {
        return this.department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getPosition() {
        return this.position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getDevice_sn() {
        return this.device_sn;
    }
    public void setDevice_sn(String device_sn) {
        this.device_sn = device_sn;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getEmployee() {
        return this.employee;
    }
    public void setEmployee(String employee) {
        this.employee = employee;
    }
    public String getTel() {
        return this.tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getInsertiontime() {
        return this.insertiontime;
    }
    public void setInsertiontime(String insertiontime) {
        this.insertiontime = insertiontime;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Generated(hash = 799134366)
    public MissedCallRecordUser(Long id, String tel, String employee,
            String avatar, String device_sn, String position, String department,
            int count, String readed, String insertiontime) {
        this.id = id;
        this.tel = tel;
        this.employee = employee;
        this.avatar = avatar;
        this.device_sn = device_sn;
        this.position = position;
        this.department = department;
        this.count = count;
        this.readed = readed;
        this.insertiontime = insertiontime;
    }
    @Generated(hash = 54403638)
    public MissedCallRecordUser() {
    }
}


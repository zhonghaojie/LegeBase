package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by zhoushaoqing on 18-10-23.
 */
@Entity
public class User{
    @Id
    private Long id;
    private String uid;
    private String name;
    private String date;
    private String state;
    private String position;
    private String dept;
    private String collection;
    private String huanxin;
    private String readed;
    public String getReaded() {
        return this.readed;
    }
    public void setReaded(String readed) {
        this.readed = readed;
    }
    public String getHuanxin() {
        return this.huanxin;
    }
    public void setHuanxin(String huanxin) {
        this.huanxin = huanxin;
    }
    public String getCollection() {
        return this.collection;
    }
    public void setCollection(String collection) {
        this.collection = collection;
    }
    public String getDept() {
        return this.dept;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    public String getPosition() {
        return this.position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 592352801)
    public User(Long id, String uid, String name, String date, String state,
            String position, String dept, String collection, String huanxin,
            String readed) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.date = date;
        this.state = state;
        this.position = position;
        this.dept = dept;
        this.collection = collection;
        this.huanxin = huanxin;
        this.readed = readed;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}

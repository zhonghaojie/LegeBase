package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by zhoushaoqing on 18-10-23.
 */
@Entity
public class CollectionUser {
    @Id
    private Long id;
    private String uid;
    private String name;
    private String date;
    private String position;
    private String dept;
    private String collection;
    private String huanxin;
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
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getHuanxin() {
        return this.huanxin;
    }
    public void setHuanxin(String huanxin) {
        this.huanxin = huanxin;
    }
    @Generated(hash = 372194875)
    public CollectionUser(Long id, String uid, String name, String date,
            String position, String dept, String collection, String huanxin) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.date = date;
        this.position = position;
        this.dept = dept;
        this.collection = collection;
        this.huanxin = huanxin;
    }
    @Generated(hash = 14573360)
    public CollectionUser() {
    }
}

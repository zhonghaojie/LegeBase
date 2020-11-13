package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class RecentlyPlayedUser {
    @Id
    private Long id;
    private int uid;
    private String group_name;
    private String image;
    private String from_source;
    private int group_id;
    private int sort;
    public int getSort() {
        return this.sort;
    }
    public void setSort(int sort) {
        this.sort = sort;
    }
    public int getGroup_id() {
        return this.group_id;
    }
    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
    public String getFrom_source() {
        return this.from_source;
    }
    public void setFrom_source(String from_source) {
        this.from_source = from_source;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getGroup_name() {
        return this.group_name;
    }
    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
    public int getUid() {
        return this.uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 439780670)
    public RecentlyPlayedUser(Long id, int uid, String group_name, String image,
            String from_source, int group_id, int sort) {
        this.id = id;
        this.uid = uid;
        this.group_name = group_name;
        this.image = image;
        this.from_source = from_source;
        this.group_id = group_id;
        this.sort = sort;
    }
    @Generated(hash = 1377612779)
    public RecentlyPlayedUser() {
    }

}

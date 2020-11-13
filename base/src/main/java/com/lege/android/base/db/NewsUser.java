package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by zhoushaoqing on 18-12-1.
 */
@Entity
public class NewsUser {
    @Id
    private Long id;
    @Index(unique = true)
    private int newsid;
    private String title;
    private String date;
    private String url;
    private String time;
    private String readed;
    private String insertiontime;// 插入时间
    public String getInsertiontime() {
        return insertiontime;
    }

    public void setInsertiontime(String insertiontime) {
        this.insertiontime = insertiontime;
    }

    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getNewsid() {
        return this.newsid;
    }
    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getReaded() {
        return this.readed;
    }
    public void setReaded(String readed) {
        this.readed = readed;
    }
    @Generated(hash = 320868342)
    public NewsUser(Long id, int newsid, String title, String date, String url,
            String time, String readed, String insertiontime) {
        this.id = id;
        this.newsid = newsid;
        this.title = title;
        this.date = date;
        this.url = url;
        this.time = time;
        this.readed = readed;
        this.insertiontime = insertiontime;
    }

    @Generated(hash = 1648215837)
    public NewsUser() {
    }
}

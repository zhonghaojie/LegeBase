package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by zhoushaoqing on 18-12-3.
 */
@Entity
public class MessageUser {
    @Id
    private Long id;

    private String isReminded;

    private String date;
    private String time;
    private String title;
    private String type;
    private String currentdate;
    private String currenttime;
    private String readed;
    private String newsimagesurl;
    private String newsfrom;

    private String way;
    private String startloc;
    private String endloc;

    private int letterid;
    private int newsid;
    private int noticeid;
    private int trafficid;
    private String content;
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getTrafficid() {
        return this.trafficid;
    }
    public void setTrafficid(int trafficid) {
        this.trafficid = trafficid;
    }
    public int getNoticeid() {
        return this.noticeid;
    }
    public void setNoticeid(int noticeid) {
        this.noticeid = noticeid;
    }
    public int getNewsid() {
        return this.newsid;
    }
    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }
    public int getLetterid() {
        return this.letterid;
    }
    public void setLetterid(int letterid) {
        this.letterid = letterid;
    }
    public String getEndloc() {
        return this.endloc;
    }
    public void setEndloc(String endloc) {
        this.endloc = endloc;
    }
    public String getStartloc() {
        return this.startloc;
    }
    public void setStartloc(String startloc) {
        this.startloc = startloc;
    }
    public String getWay() {
        return this.way;
    }
    public void setWay(String way) {
        this.way = way;
    }
    public String getNewsfrom() {
        return this.newsfrom;
    }
    public void setNewsfrom(String newsfrom) {
        this.newsfrom = newsfrom;
    }
    public String getNewsimagesurl() {
        return this.newsimagesurl;
    }
    public void setNewsimagesurl(String newsimagesurl) {
        this.newsimagesurl = newsimagesurl;
    }
    public String getReaded() {
        return this.readed;
    }
    public void setReaded(String readed) {
        this.readed = readed;
    }
    public String getCurrenttime() {
        return this.currenttime;
    }
    public void setCurrenttime(String currenttime) {
        this.currenttime = currenttime;
    }
    public String getCurrentdate() {
        return this.currentdate;
    }
    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getIsReminded() {
        return this.isReminded;
    }
    public void setIsReminded(String isReminded) {
        this.isReminded = isReminded;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 236379700)
    public MessageUser(Long id, String isReminded, String date, String time,
            String title, String type, String currentdate, String currenttime,
            String readed, String newsimagesurl, String newsfrom, String way,
            String startloc, String endloc, int letterid, int newsid, int noticeid,
            int trafficid, String content) {
        this.id = id;
        this.isReminded = isReminded;
        this.date = date;
        this.time = time;
        this.title = title;
        this.type = type;
        this.currentdate = currentdate;
        this.currenttime = currenttime;
        this.readed = readed;
        this.newsimagesurl = newsimagesurl;
        this.newsfrom = newsfrom;
        this.way = way;
        this.startloc = startloc;
        this.endloc = endloc;
        this.letterid = letterid;
        this.newsid = newsid;
        this.noticeid = noticeid;
        this.trafficid = trafficid;
        this.content = content;
    }
    @Generated(hash = 1201440511)
    public MessageUser() {
    }
}

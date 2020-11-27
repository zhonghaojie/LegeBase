package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class NewRemindUser {
    @Id
    private Long id;
    private int parent_id ;
    @Index(unique = true)
    private String title ;
    private String remind ;
    private String desc;
    private String icon ;
    private int type ;
    private String app;
    private int interval ;
    private String starttime ;
    private String endtime;
    private String when_play ;
    private String repeat ;
    private int auto_start;
    private String play_resoure ;
    private int play_time ;
    private String remind_img;
    private int sort;
    private int user_id;
    private String device_sn;
    private int subscribe;
    private String time;
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getSubscribe() {
        return this.subscribe;
    }
    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }
    public String getDevice_sn() {
        return this.device_sn;
    }
    public void setDevice_sn(String device_sn) {
        this.device_sn = device_sn;
    }
    public int getUser_id() {
        return this.user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getSort() {
        return this.sort;
    }
    public void setSort(int sort) {
        this.sort = sort;
    }
    public String getRemind_img() {
        return this.remind_img;
    }
    public void setRemind_img(String remind_img) {
        this.remind_img = remind_img;
    }
    public int getPlay_time() {
        return this.play_time;
    }
    public void setPlay_time(int play_time) {
        this.play_time = play_time;
    }
    public String getPlay_resoure() {
        return this.play_resoure;
    }
    public void setPlay_resoure(String play_resoure) {
        this.play_resoure = play_resoure;
    }
    public int getAuto_start() {
        return this.auto_start;
    }
    public void setAuto_start(int auto_start) {
        this.auto_start = auto_start;
    }
    public String getRepeat() {
        return this.repeat;
    }
    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }
    public String getWhen_play() {
        return this.when_play;
    }
    public void setWhen_play(String when_play) {
        this.when_play = when_play;
    }
    public String getEndtime() {
        return this.endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getStarttime() {
        return this.starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public int getInterval() {
        return this.interval;
    }
    public void setInterval(int interval) {
        this.interval = interval;
    }
    public String getApp() {
        return this.app;
    }
    public void setApp(String app) {
        this.app = app;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getRemind() {
        return this.remind;
    }
    public void setRemind(String remind) {
        this.remind = remind;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getParent_id() {
        return this.parent_id;
    }
    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1159930730)
    public NewRemindUser(Long id, int parent_id, String title, String remind,
            String desc, String icon, int type, String app, int interval,
            String starttime, String endtime, String when_play, String repeat,
            int auto_start, String play_resoure, int play_time, String remind_img,
            int sort, int user_id, String device_sn, int subscribe, String time) {
        this.id = id;
        this.parent_id = parent_id;
        this.title = title;
        this.remind = remind;
        this.desc = desc;
        this.icon = icon;
        this.type = type;
        this.app = app;
        this.interval = interval;
        this.starttime = starttime;
        this.endtime = endtime;
        this.when_play = when_play;
        this.repeat = repeat;
        this.auto_start = auto_start;
        this.play_resoure = play_resoure;
        this.play_time = play_time;
        this.remind_img = remind_img;
        this.sort = sort;
        this.user_id = user_id;
        this.device_sn = device_sn;
        this.subscribe = subscribe;
        this.time = time;
    }
    @Generated(hash = 311479523)
    public NewRemindUser() {
    }
}

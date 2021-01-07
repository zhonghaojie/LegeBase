package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:小贴士历史记录
 * Created by loctek on 2020/12/30.
 */
@Entity
public class TipsHistoryBean {
    @Id
    private Long id;
    private String title;
    private String content;
    private String lunar;
    private String img;
    private int tipsID;
    private String updateTime;
    private String addTime;

    @Generated(hash = 136099514)
    public TipsHistoryBean(Long id, String title, String content, String lunar,
            String img, int tipsID, String updateTime, String addTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.lunar = lunar;
        this.img = img;
        this.tipsID = tipsID;
        this.updateTime = updateTime;
        this.addTime = addTime;
    }

    @Generated(hash = 1440011860)
    public TipsHistoryBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTipsID() {
        return tipsID;
    }

    public void setTipsID(int tipsID) {
        this.tipsID = tipsID;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


}

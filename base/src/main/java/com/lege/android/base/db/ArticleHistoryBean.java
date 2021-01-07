package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:长文章历史记录
 * Created by loctek on 2021/1/4.
 */
@Entity
public class ArticleHistoryBean {
    @Id
    private Long id;
    private int articleID;
    private String title;
    private String url;
    private String addTime;
    private long publishTime;
    private String imageUrl;

    @Generated(hash = 2079628004)
    public ArticleHistoryBean(Long id, int articleID, String title, String url,
            String addTime, long publishTime, String imageUrl) {
        this.id = id;
        this.articleID = articleID;
        this.title = title;
        this.url = url;
        this.addTime = addTime;
        this.publishTime = publishTime;
        this.imageUrl = imageUrl;
    }

    @Generated(hash = 1550925571)
    public ArticleHistoryBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}

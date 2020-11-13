package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class WallpaperUser {
    @Id
    private Long id;
    private int wallpaperid;
    private String url;
    private String localpath;
    public String getLocalpath() {
        return this.localpath;
    }
    public void setLocalpath(String localpath) {
        this.localpath = localpath;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getWallpaperid() {
        return this.wallpaperid;
    }
    public void setWallpaperid(int wallpaperid) {
        this.wallpaperid = wallpaperid;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1127285973)
    public WallpaperUser(Long id, int wallpaperid, String url, String localpath) {
        this.id = id;
        this.wallpaperid = wallpaperid;
        this.url = url;
        this.localpath = localpath;
    }
    @Generated(hash = 834867523)
    public WallpaperUser() {
    }
}

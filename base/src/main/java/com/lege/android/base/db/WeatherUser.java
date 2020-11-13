package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by zhoushaoqing on 18-12-1.
 */
@Entity
public class WeatherUser {
    @Id
    private Long id;
    private int weather;
    private int weathercityid;
    private String cityname;
    private String zonename;
    public String getZonename() {
        return this.zonename;
    }
    public void setZonename(String zonename) {
        this.zonename = zonename;
    }
    public String getCityname() {
        return this.cityname;
    }
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
    public int getWeathercityid() {
        return this.weathercityid;
    }
    public void setWeathercityid(int weathercityid) {
        this.weathercityid = weathercityid;
    }
    public int getWeather() {
        return this.weather;
    }
    public void setWeather(int weather) {
        this.weather = weather;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1312708119)
    public WeatherUser(Long id, int weather, int weathercityid, String cityname,
            String zonename) {
        this.id = id;
        this.weather = weather;
        this.weathercityid = weathercityid;
        this.cityname = cityname;
        this.zonename = zonename;
    }
    @Generated(hash = 396444250)
    public WeatherUser() {
    }
}

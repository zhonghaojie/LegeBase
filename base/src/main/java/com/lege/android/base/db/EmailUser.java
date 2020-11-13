package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class EmailUser {
    @Id
    private Long id;
    @Index(unique = true)
    private int emailid;
    private String username;
    private String password;
    private String popserver;
    private int ssl;
    private String serverport;
    private String totalCount;
    private Integer isStart;
    @Generated(hash = 1725725663)
    public EmailUser(Long id, int emailid, String username, String password,
            String popserver, int ssl, String serverport, String totalCount,
            Integer isStart) {
        this.id = id;
        this.emailid = emailid;
        this.username = username;
        this.password = password;
        this.popserver = popserver;
        this.ssl = ssl;
        this.serverport = serverport;
        this.totalCount = totalCount;
        this.isStart = isStart;
    }
    @Generated(hash = 1804807455)
    public EmailUser() {
    }
    public String getServerport() {
        return this.serverport;
    }
    public void setServerport(String serverport) {
        this.serverport = serverport;
    }
    public int getSsl() {
        return this.ssl;
    }
    public void setSsl(int ssl) {
        this.ssl = ssl;
    }
    public String getPopserver() {
        return this.popserver;
    }
    public void setPopserver(String popserver) {
        this.popserver = popserver;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getEmailid() {
        return this.emailid;
    }
    public void setEmailid(int emailid) {
        this.emailid = emailid;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTotalCount() {
        return this.totalCount;
    }
    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
    public Integer getIsStart() {
        return this.isStart;
    }
    public void setIsStart(Integer isStart) {
        this.isStart = isStart;
    }
}

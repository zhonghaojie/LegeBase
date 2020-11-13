package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class EmailMessageUser {

    @Id
    private Long id;
    private String subject;
    private String fromemail;
    private String senddate;
    @Index(unique = true)
    private String emailmessageid;
    private String readed;
    private String username;
    private String time;
    private String uid;
    private String content;
    private String deleted;//刪除标志符 n；未删除，y：已删除
    private String insertiontime;// 插入时间
    public String getInsertiontime() {
        return insertiontime;
    }

    public void setInsertiontime(String insertiontime) {
        this.insertiontime = insertiontime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getReaded() {
        return this.readed;
    }
    public void setReaded(String readed) {
        this.readed = readed;
    }
    public String getEmailmessageid() {
        return this.emailmessageid;
    }
    public void setEmailmessageid(String emailmessageid) {
        this.emailmessageid = emailmessageid;
    }
    public String getSenddate() {
        return this.senddate;
    }
    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }
    public String getFromemail() {
        return this.fromemail;
    }
    public void setFromemail(String fromemail) {
        this.fromemail = fromemail;
    }
    public String getSubject() {
        return this.subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1643673018)
    public EmailMessageUser(Long id, String subject, String fromemail,
            String senddate, String emailmessageid, String readed, String username,
            String time, String uid, String content, String deleted,
            String insertiontime) {
        this.id = id;
        this.subject = subject;
        this.fromemail = fromemail;
        this.senddate = senddate;
        this.emailmessageid = emailmessageid;
        this.readed = readed;
        this.username = username;
        this.time = time;
        this.uid = uid;
        this.content = content;
        this.deleted = deleted;
        this.insertiontime = insertiontime;
    }

    @Generated(hash = 1295394182)
    public EmailMessageUser() {
    }
}

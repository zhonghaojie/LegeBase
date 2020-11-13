package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * @author xgx on 2020-05-07
 */
@Entity
public class NoticeUser {

    /**
     * abstract : 111
     * company_id : 0
     * content : <p>服务器维护通知</p>
     <p>服务器维护通知</p>
     <p>&nbsp;</p>
     <p>服务器维护通知</p>
     <p>服务器维护通知</p>
     <p>&nbsp;</p>
     <p>服务器维护通知服务器维护通知</p>
     * create_time : 2020-04-30 15:14:11
     * editor : 小念
     * editor_id : 1
     * id : 8
     * image :
     * is_published : 1
     * is_timing : 1
     * is_top : 0
     * message_file : null
     * message_type : 1
     * publish_time : 2020-04-30 15:15:29
     * send_company : -1
     * send_range : 非企业用户
     * send_user : null
     * title : 通知
     * topic : 服务器维护通知
     * url : http://40.73.69.201:8005/static/html/message/view-message-8.html
     */
    @Id
    private Long id;
    private String abstractX;
    private int company_id;
    private String content;
    private String create_time;
    private String editor;
    private int editor_id;
    @Index(unique = true)
    private int noticeid;
    private String image;
    private int is_published;
    private int is_timing;
    private int is_top;
    private String message_file;
    private int message_type; //1为乐歌服务通知，0为公司公告
    private String publish_time;
    private String send_company;
    private String send_range;
    private String send_user;
    private String title;
    private String topic;
    private String url;
    private String deleted;//刪除标志符 n；未删除，y：已删除
    private String insertiontime;// 插入时间
    private String readed;//是否已读，yes已读，no未读
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTopic() {
        return this.topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSend_user() {
        return this.send_user;
    }
    public void setSend_user(String send_user) {
        this.send_user = send_user;
    }
    public String getSend_range() {
        return this.send_range;
    }
    public void setSend_range(String send_range) {
        this.send_range = send_range;
    }
    public String getSend_company() {
        return this.send_company;
    }
    public void setSend_company(String send_company) {
        this.send_company = send_company;
    }
    public String getPublish_time() {
        return this.publish_time;
    }
    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }
    public int getMessage_type() {
        return this.message_type;
    }
    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }
    public String getMessage_file() {
        return this.message_file;
    }
    public void setMessage_file(String message_file) {
        this.message_file = message_file;
    }
    public int getIs_top() {
        return this.is_top;
    }
    public void setIs_top(int is_top) {
        this.is_top = is_top;
    }
    public int getIs_timing() {
        return this.is_timing;
    }
    public void setIs_timing(int is_timing) {
        this.is_timing = is_timing;
    }
    public int getIs_published() {
        return this.is_published;
    }
    public void setIs_published(int is_published) {
        this.is_published = is_published;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getNoticeid() {
        return this.noticeid;
    }
    public void setNoticeid(int noticeid) {
        this.noticeid = noticeid;
    }
    public int getEditor_id() {
        return this.editor_id;
    }
    public void setEditor_id(int editor_id) {
        this.editor_id = editor_id;
    }
    public String getEditor() {
        return this.editor;
    }
    public void setEditor(String editor) {
        this.editor = editor;
    }
    public String getCreate_time() {
        return this.create_time;
    }
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getCompany_id() {
        return this.company_id;
    }
    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
    public String getAbstractX() {
        return this.abstractX;
    }
    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
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
    public String getInsertiontime() {
        return this.insertiontime;
    }
    public void setInsertiontime(String insertiontime) {
        this.insertiontime = insertiontime;
    }
    public String getDeleted() {
        return this.deleted;
    }
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
    @Generated(hash = 1689791106)
    public NoticeUser(Long id, String abstractX, int company_id, String content,
            String create_time, String editor, int editor_id, int noticeid,
            String image, int is_published, int is_timing, int is_top,
            String message_file, int message_type, String publish_time,
            String send_company, String send_range, String send_user, String title,
            String topic, String url, String deleted, String insertiontime,
            String readed) {
        this.id = id;
        this.abstractX = abstractX;
        this.company_id = company_id;
        this.content = content;
        this.create_time = create_time;
        this.editor = editor;
        this.editor_id = editor_id;
        this.noticeid = noticeid;
        this.image = image;
        this.is_published = is_published;
        this.is_timing = is_timing;
        this.is_top = is_top;
        this.message_file = message_file;
        this.message_type = message_type;
        this.publish_time = publish_time;
        this.send_company = send_company;
        this.send_range = send_range;
        this.send_user = send_user;
        this.title = title;
        this.topic = topic;
        this.url = url;
        this.deleted = deleted;
        this.insertiontime = insertiontime;
        this.readed = readed;
    }
    @Generated(hash = 1669666811)
    public NoticeUser() {
    }
}

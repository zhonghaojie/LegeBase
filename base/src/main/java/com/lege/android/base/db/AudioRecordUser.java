package com.lege.android.base.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Description:
 * Created by loctek on 2020/9/11.
 */
@Entity
public class AudioRecordUser {
    private long timestemp;
    private String path;
    @Id
    private long recordID;
    private long duration;
    private boolean isUpload=false;
    private long fileSize;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }

    public long getDuration() {
        return this.duration;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public long getRecordID() {
        return this.recordID;
    }
    public void setRecordID(long recordID) {
        this.recordID = recordID;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public long getTimestemp() {
        return this.timestemp;
    }
    public void setTimestemp(long timestemp) {
        this.timestemp = timestemp;
    }

    public boolean getIsUpload() {
        return this.isUpload;
    }

    public void setIsUpload(boolean isUpload) {
        this.isUpload = isUpload;
    }
    @Generated(hash = 2001585299)
    public AudioRecordUser(long timestemp, String path, long recordID, long duration,
            boolean isUpload, long fileSize, String name) {
        this.timestemp = timestemp;
        this.path = path;
        this.recordID = recordID;
        this.duration = duration;
        this.isUpload = isUpload;
        this.fileSize = fileSize;
        this.name = name;
    }

    @Generated(hash = 1550508959)
    public AudioRecordUser() {
    }


}

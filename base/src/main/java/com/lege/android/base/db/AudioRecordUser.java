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
    @Generated(hash = 1345925923)
    public AudioRecordUser(long timestemp, String path, long recordID, long duration,
            boolean isUpload, long fileSize) {
        this.timestemp = timestemp;
        this.path = path;
        this.recordID = recordID;
        this.duration = duration;
        this.isUpload = isUpload;
        this.fileSize = fileSize;
    }

    @Generated(hash = 1550508959)
    public AudioRecordUser() {
    }


}

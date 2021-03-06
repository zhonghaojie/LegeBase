package com.lege.android.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lege.android.base.db.EmailMessageUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "EMAIL_MESSAGE_USER".
*/
public class EmailMessageUserDao extends AbstractDao<EmailMessageUser, Long> {

    public static final String TABLENAME = "EMAIL_MESSAGE_USER";

    /**
     * Properties of entity EmailMessageUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Subject = new Property(1, String.class, "subject", false, "SUBJECT");
        public final static Property Fromemail = new Property(2, String.class, "fromemail", false, "FROMEMAIL");
        public final static Property Senddate = new Property(3, String.class, "senddate", false, "SENDDATE");
        public final static Property Emailmessageid = new Property(4, String.class, "emailmessageid", false, "EMAILMESSAGEID");
        public final static Property Readed = new Property(5, String.class, "readed", false, "READED");
        public final static Property Username = new Property(6, String.class, "username", false, "USERNAME");
        public final static Property Time = new Property(7, String.class, "time", false, "TIME");
        public final static Property Uid = new Property(8, String.class, "uid", false, "UID");
        public final static Property Content = new Property(9, String.class, "content", false, "CONTENT");
        public final static Property Deleted = new Property(10, String.class, "deleted", false, "DELETED");
        public final static Property Insertiontime = new Property(11, String.class, "insertiontime", false, "INSERTIONTIME");
    };


    public EmailMessageUserDao(DaoConfig config) {
        super(config);
    }
    
    public EmailMessageUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EMAIL_MESSAGE_USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"SUBJECT\" TEXT," + // 1: subject
                "\"FROMEMAIL\" TEXT," + // 2: fromemail
                "\"SENDDATE\" TEXT," + // 3: senddate
                "\"EMAILMESSAGEID\" TEXT," + // 4: emailmessageid
                "\"READED\" TEXT," + // 5: readed
                "\"USERNAME\" TEXT," + // 6: username
                "\"TIME\" TEXT," + // 7: time
                "\"UID\" TEXT," + // 8: uid
                "\"CONTENT\" TEXT," + // 9: content
                "\"DELETED\" TEXT," + // 10: deleted
                "\"INSERTIONTIME\" TEXT);"); // 11: insertiontime
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_EMAIL_MESSAGE_USER_EMAILMESSAGEID ON EMAIL_MESSAGE_USER" +
                " (\"EMAILMESSAGEID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EMAIL_MESSAGE_USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, EmailMessageUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String subject = entity.getSubject();
        if (subject != null) {
            stmt.bindString(2, subject);
        }
 
        String fromemail = entity.getFromemail();
        if (fromemail != null) {
            stmt.bindString(3, fromemail);
        }
 
        String senddate = entity.getSenddate();
        if (senddate != null) {
            stmt.bindString(4, senddate);
        }
 
        String emailmessageid = entity.getEmailmessageid();
        if (emailmessageid != null) {
            stmt.bindString(5, emailmessageid);
        }
 
        String readed = entity.getReaded();
        if (readed != null) {
            stmt.bindString(6, readed);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(7, username);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(8, time);
        }
 
        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(9, uid);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(10, content);
        }
 
        String deleted = entity.getDeleted();
        if (deleted != null) {
            stmt.bindString(11, deleted);
        }
 
        String insertiontime = entity.getInsertiontime();
        if (insertiontime != null) {
            stmt.bindString(12, insertiontime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, EmailMessageUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String subject = entity.getSubject();
        if (subject != null) {
            stmt.bindString(2, subject);
        }
 
        String fromemail = entity.getFromemail();
        if (fromemail != null) {
            stmt.bindString(3, fromemail);
        }
 
        String senddate = entity.getSenddate();
        if (senddate != null) {
            stmt.bindString(4, senddate);
        }
 
        String emailmessageid = entity.getEmailmessageid();
        if (emailmessageid != null) {
            stmt.bindString(5, emailmessageid);
        }
 
        String readed = entity.getReaded();
        if (readed != null) {
            stmt.bindString(6, readed);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(7, username);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(8, time);
        }
 
        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(9, uid);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(10, content);
        }
 
        String deleted = entity.getDeleted();
        if (deleted != null) {
            stmt.bindString(11, deleted);
        }
 
        String insertiontime = entity.getInsertiontime();
        if (insertiontime != null) {
            stmt.bindString(12, insertiontime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public EmailMessageUser readEntity(Cursor cursor, int offset) {
        EmailMessageUser entity = new EmailMessageUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // subject
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // fromemail
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // senddate
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // emailmessageid
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // readed
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // username
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // time
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // uid
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // content
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // deleted
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11) // insertiontime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, EmailMessageUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSubject(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFromemail(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSenddate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEmailmessageid(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setReaded(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUsername(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setUid(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setContent(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDeleted(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setInsertiontime(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(EmailMessageUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(EmailMessageUser entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

package com.lege.android.base.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "EMAIL_USER".
*/
public class EmailUserDao extends AbstractDao<EmailUser, Long> {

    public static final String TABLENAME = "EMAIL_USER";

    /**
     * Properties of entity EmailUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Emailid = new Property(1, int.class, "emailid", false, "EMAILID");
        public final static Property Username = new Property(2, String.class, "username", false, "USERNAME");
        public final static Property Password = new Property(3, String.class, "password", false, "PASSWORD");
        public final static Property Popserver = new Property(4, String.class, "popserver", false, "POPSERVER");
        public final static Property Ssl = new Property(5, int.class, "ssl", false, "SSL");
        public final static Property Serverport = new Property(6, String.class, "serverport", false, "SERVERPORT");
        public final static Property TotalCount = new Property(7, String.class, "totalCount", false, "TOTAL_COUNT");
        public final static Property IsStart = new Property(8, Integer.class, "isStart", false, "IS_START");
    };


    public EmailUserDao(DaoConfig config) {
        super(config);
    }
    
    public EmailUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EMAIL_USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"EMAILID\" INTEGER NOT NULL ," + // 1: emailid
                "\"USERNAME\" TEXT," + // 2: username
                "\"PASSWORD\" TEXT," + // 3: password
                "\"POPSERVER\" TEXT," + // 4: popserver
                "\"SSL\" INTEGER NOT NULL ," + // 5: ssl
                "\"SERVERPORT\" TEXT," + // 6: serverport
                "\"TOTAL_COUNT\" TEXT," + // 7: totalCount
                "\"IS_START\" INTEGER);"); // 8: isStart
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_EMAIL_USER_EMAILID ON EMAIL_USER" +
                " (\"EMAILID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EMAIL_USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, EmailUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getEmailid());
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(3, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
 
        String popserver = entity.getPopserver();
        if (popserver != null) {
            stmt.bindString(5, popserver);
        }
        stmt.bindLong(6, entity.getSsl());
 
        String serverport = entity.getServerport();
        if (serverport != null) {
            stmt.bindString(7, serverport);
        }
 
        String totalCount = entity.getTotalCount();
        if (totalCount != null) {
            stmt.bindString(8, totalCount);
        }
 
        Integer isStart = entity.getIsStart();
        if (isStart != null) {
            stmt.bindLong(9, isStart);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, EmailUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getEmailid());
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(3, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
 
        String popserver = entity.getPopserver();
        if (popserver != null) {
            stmt.bindString(5, popserver);
        }
        stmt.bindLong(6, entity.getSsl());
 
        String serverport = entity.getServerport();
        if (serverport != null) {
            stmt.bindString(7, serverport);
        }
 
        String totalCount = entity.getTotalCount();
        if (totalCount != null) {
            stmt.bindString(8, totalCount);
        }
 
        Integer isStart = entity.getIsStart();
        if (isStart != null) {
            stmt.bindLong(9, isStart);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public EmailUser readEntity(Cursor cursor, int offset) {
        EmailUser entity = new EmailUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // emailid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // username
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // password
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // popserver
            cursor.getInt(offset + 5), // ssl
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // serverport
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // totalCount
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8) // isStart
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, EmailUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEmailid(cursor.getInt(offset + 1));
        entity.setUsername(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPassword(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPopserver(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSsl(cursor.getInt(offset + 5));
        entity.setServerport(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTotalCount(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setIsStart(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(EmailUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(EmailUser entity) {
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

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
 * DAO for table "SCHEDULE_USER".
*/
public class ScheduleUserDao extends AbstractDao<ScheduleUser, Long> {

    public static final String TABLENAME = "SCHEDULE_USER";

    /**
     * Properties of entity ScheduleUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Scheduleid = new Property(1, int.class, "scheduleid", false, "SCHEDULEID");
        public final static Property Startdate = new Property(2, String.class, "startdate", false, "STARTDATE");
        public final static Property Starttime = new Property(3, String.class, "starttime", false, "STARTTIME");
        public final static Property Enddate = new Property(4, String.class, "enddate", false, "ENDDATE");
        public final static Property Endtime = new Property(5, String.class, "endtime", false, "ENDTIME");
        public final static Property Title = new Property(6, String.class, "title", false, "TITLE");
        public final static Property Is_allday = new Property(7, int.class, "is_allday", false, "IS_ALLDAY");
        public final static Property Alert = new Property(8, String.class, "alert", false, "ALERT");
        public final static Property Readed = new Property(9, String.class, "readed", false, "READED");
        public final static Property IsReminded = new Property(10, String.class, "isReminded", false, "IS_REMINDED");
        public final static Property Time = new Property(11, String.class, "time", false, "TIME");
    };


    public ScheduleUserDao(DaoConfig config) {
        super(config);
    }
    
    public ScheduleUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SCHEDULE_USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"SCHEDULEID\" INTEGER NOT NULL ," + // 1: scheduleid
                "\"STARTDATE\" TEXT," + // 2: startdate
                "\"STARTTIME\" TEXT," + // 3: starttime
                "\"ENDDATE\" TEXT," + // 4: enddate
                "\"ENDTIME\" TEXT," + // 5: endtime
                "\"TITLE\" TEXT," + // 6: title
                "\"IS_ALLDAY\" INTEGER NOT NULL ," + // 7: is_allday
                "\"ALERT\" TEXT," + // 8: alert
                "\"READED\" TEXT," + // 9: readed
                "\"IS_REMINDED\" TEXT," + // 10: isReminded
                "\"TIME\" TEXT);"); // 11: time
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_SCHEDULE_USER_SCHEDULEID ON SCHEDULE_USER" +
                " (\"SCHEDULEID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SCHEDULE_USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ScheduleUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getScheduleid());
 
        String startdate = entity.getStartdate();
        if (startdate != null) {
            stmt.bindString(3, startdate);
        }
 
        String starttime = entity.getStarttime();
        if (starttime != null) {
            stmt.bindString(4, starttime);
        }
 
        String enddate = entity.getEnddate();
        if (enddate != null) {
            stmt.bindString(5, enddate);
        }
 
        String endtime = entity.getEndtime();
        if (endtime != null) {
            stmt.bindString(6, endtime);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(7, title);
        }
        stmt.bindLong(8, entity.getIs_allday());
 
        String alert = entity.getAlert();
        if (alert != null) {
            stmt.bindString(9, alert);
        }
 
        String readed = entity.getReaded();
        if (readed != null) {
            stmt.bindString(10, readed);
        }
 
        String isReminded = entity.getIsReminded();
        if (isReminded != null) {
            stmt.bindString(11, isReminded);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(12, time);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ScheduleUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getScheduleid());
 
        String startdate = entity.getStartdate();
        if (startdate != null) {
            stmt.bindString(3, startdate);
        }
 
        String starttime = entity.getStarttime();
        if (starttime != null) {
            stmt.bindString(4, starttime);
        }
 
        String enddate = entity.getEnddate();
        if (enddate != null) {
            stmt.bindString(5, enddate);
        }
 
        String endtime = entity.getEndtime();
        if (endtime != null) {
            stmt.bindString(6, endtime);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(7, title);
        }
        stmt.bindLong(8, entity.getIs_allday());
 
        String alert = entity.getAlert();
        if (alert != null) {
            stmt.bindString(9, alert);
        }
 
        String readed = entity.getReaded();
        if (readed != null) {
            stmt.bindString(10, readed);
        }
 
        String isReminded = entity.getIsReminded();
        if (isReminded != null) {
            stmt.bindString(11, isReminded);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(12, time);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ScheduleUser readEntity(Cursor cursor, int offset) {
        ScheduleUser entity = new ScheduleUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // scheduleid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // startdate
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // starttime
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // enddate
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // endtime
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // title
            cursor.getInt(offset + 7), // is_allday
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // alert
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // readed
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // isReminded
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ScheduleUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setScheduleid(cursor.getInt(offset + 1));
        entity.setStartdate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStarttime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEnddate(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEndtime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTitle(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setIs_allday(cursor.getInt(offset + 7));
        entity.setAlert(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setReaded(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setIsReminded(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setTime(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ScheduleUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ScheduleUser entity) {
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

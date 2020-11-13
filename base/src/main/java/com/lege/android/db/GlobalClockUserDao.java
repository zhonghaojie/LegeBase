package com.lege.android.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lege.android.base.db.GlobalClockUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GLOBAL_CLOCK_USER".
*/
public class GlobalClockUserDao extends AbstractDao<GlobalClockUser, Long> {

    public static final String TABLENAME = "GLOBAL_CLOCK_USER";

    /**
     * Properties of entity GlobalClockUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property GlobalClockid = new Property(1, int.class, "GlobalClockid", false, "GLOBAL_CLOCKID");
        public final static Property Timezone = new Property(2, String.class, "timezone", false, "TIMEZONE");
        public final static Property Gmt = new Property(3, String.class, "gmt", false, "GMT");
    };


    public GlobalClockUserDao(DaoConfig config) {
        super(config);
    }
    
    public GlobalClockUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GLOBAL_CLOCK_USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"GLOBAL_CLOCKID\" INTEGER NOT NULL ," + // 1: GlobalClockid
                "\"TIMEZONE\" TEXT," + // 2: timezone
                "\"GMT\" TEXT);"); // 3: gmt
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_GLOBAL_CLOCK_USER_GLOBAL_CLOCKID ON GLOBAL_CLOCK_USER" +
                " (\"GLOBAL_CLOCKID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GLOBAL_CLOCK_USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GlobalClockUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getGlobalClockid());
 
        String timezone = entity.getTimezone();
        if (timezone != null) {
            stmt.bindString(3, timezone);
        }
 
        String gmt = entity.getGmt();
        if (gmt != null) {
            stmt.bindString(4, gmt);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GlobalClockUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getGlobalClockid());
 
        String timezone = entity.getTimezone();
        if (timezone != null) {
            stmt.bindString(3, timezone);
        }
 
        String gmt = entity.getGmt();
        if (gmt != null) {
            stmt.bindString(4, gmt);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public GlobalClockUser readEntity(Cursor cursor, int offset) {
        GlobalClockUser entity = new GlobalClockUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // GlobalClockid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // timezone
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // gmt
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GlobalClockUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGlobalClockid(cursor.getInt(offset + 1));
        entity.setTimezone(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setGmt(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GlobalClockUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GlobalClockUser entity) {
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
package com.lege.android.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lege.android.base.db.AudioRecordUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "AUDIO_RECORD_USER".
*/
public class AudioRecordUserDao extends AbstractDao<AudioRecordUser, Long> {

    public static final String TABLENAME = "AUDIO_RECORD_USER";

    /**
     * Properties of entity AudioRecordUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Timestemp = new Property(0, long.class, "timestemp", false, "TIMESTEMP");
        public final static Property Path = new Property(1, String.class, "path", false, "PATH");
        public final static Property RecordID = new Property(2, long.class, "recordID", true, "_id");
        public final static Property Duration = new Property(3, long.class, "duration", false, "DURATION");
        public final static Property IsUpload = new Property(4, boolean.class, "isUpload", false, "IS_UPLOAD");
        public final static Property FileSize = new Property(5, long.class, "fileSize", false, "FILE_SIZE");
        public final static Property Name = new Property(6, String.class, "name", false, "NAME");
    };


    public AudioRecordUserDao(DaoConfig config) {
        super(config);
    }
    
    public AudioRecordUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"AUDIO_RECORD_USER\" (" + //
                "\"TIMESTEMP\" INTEGER NOT NULL ," + // 0: timestemp
                "\"PATH\" TEXT," + // 1: path
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 2: recordID
                "\"DURATION\" INTEGER NOT NULL ," + // 3: duration
                "\"IS_UPLOAD\" INTEGER NOT NULL ," + // 4: isUpload
                "\"FILE_SIZE\" INTEGER NOT NULL ," + // 5: fileSize
                "\"NAME\" TEXT);"); // 6: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"AUDIO_RECORD_USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AudioRecordUser entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getTimestemp());
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(2, path);
        }
        stmt.bindLong(3, entity.getRecordID());
        stmt.bindLong(4, entity.getDuration());
        stmt.bindLong(5, entity.getIsUpload() ? 1L: 0L);
        stmt.bindLong(6, entity.getFileSize());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(7, name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AudioRecordUser entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getTimestemp());
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(2, path);
        }
        stmt.bindLong(3, entity.getRecordID());
        stmt.bindLong(4, entity.getDuration());
        stmt.bindLong(5, entity.getIsUpload() ? 1L: 0L);
        stmt.bindLong(6, entity.getFileSize());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(7, name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 2);
    }    

    @Override
    public AudioRecordUser readEntity(Cursor cursor, int offset) {
        AudioRecordUser entity = new AudioRecordUser( //
            cursor.getLong(offset + 0), // timestemp
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // path
            cursor.getLong(offset + 2), // recordID
            cursor.getLong(offset + 3), // duration
            cursor.getShort(offset + 4) != 0, // isUpload
            cursor.getLong(offset + 5), // fileSize
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AudioRecordUser entity, int offset) {
        entity.setTimestemp(cursor.getLong(offset + 0));
        entity.setPath(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRecordID(cursor.getLong(offset + 2));
        entity.setDuration(cursor.getLong(offset + 3));
        entity.setIsUpload(cursor.getShort(offset + 4) != 0);
        entity.setFileSize(cursor.getLong(offset + 5));
        entity.setName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AudioRecordUser entity, long rowId) {
        entity.setRecordID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AudioRecordUser entity) {
        if(entity != null) {
            return entity.getRecordID();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

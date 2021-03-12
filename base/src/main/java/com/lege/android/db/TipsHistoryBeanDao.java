package com.lege.android.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lege.android.base.db.TipsHistoryBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TIPS_HISTORY_BEAN".
*/
public class TipsHistoryBeanDao extends AbstractDao<TipsHistoryBean, Long> {

    public static final String TABLENAME = "TIPS_HISTORY_BEAN";

    /**
     * Properties of entity TipsHistoryBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
        public final static Property Lunar = new Property(3, String.class, "lunar", false, "LUNAR");
        public final static Property Img = new Property(4, String.class, "img", false, "IMG");
        public final static Property TipsID = new Property(5, int.class, "tipsID", false, "TIPS_ID");
        public final static Property UpdateTime = new Property(6, String.class, "updateTime", false, "UPDATE_TIME");
        public final static Property AddTime = new Property(7, String.class, "addTime", false, "ADD_TIME");
    };


    public TipsHistoryBeanDao(DaoConfig config) {
        super(config);
    }
    
    public TipsHistoryBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TIPS_HISTORY_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"CONTENT\" TEXT," + // 2: content
                "\"LUNAR\" TEXT," + // 3: lunar
                "\"IMG\" TEXT," + // 4: img
                "\"TIPS_ID\" INTEGER NOT NULL ," + // 5: tipsID
                "\"UPDATE_TIME\" TEXT," + // 6: updateTime
                "\"ADD_TIME\" TEXT);"); // 7: addTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TIPS_HISTORY_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TipsHistoryBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
 
        String lunar = entity.getLunar();
        if (lunar != null) {
            stmt.bindString(4, lunar);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(5, img);
        }
        stmt.bindLong(6, entity.getTipsID());
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(7, updateTime);
        }
 
        String addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindString(8, addTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TipsHistoryBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
 
        String lunar = entity.getLunar();
        if (lunar != null) {
            stmt.bindString(4, lunar);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(5, img);
        }
        stmt.bindLong(6, entity.getTipsID());
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(7, updateTime);
        }
 
        String addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindString(8, addTime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TipsHistoryBean readEntity(Cursor cursor, int offset) {
        TipsHistoryBean entity = new TipsHistoryBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // content
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // lunar
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // img
            cursor.getInt(offset + 5), // tipsID
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // updateTime
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // addTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TipsHistoryBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setContent(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLunar(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setImg(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTipsID(cursor.getInt(offset + 5));
        entity.setUpdateTime(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAddTime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TipsHistoryBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TipsHistoryBean entity) {
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

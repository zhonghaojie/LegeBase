package com.lege.android.base.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.lege.android.base.log.APPLog
import com.lege.android.db.*
import org.greenrobot.greendao.database.Database


/**
 * Created by zhoushaoqing on 19-3-19.
 */

class DaoMasterHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?) : DaoMaster.DevOpenHelper(context, name, factory) {

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        //把需要管理的数据库表DAO作为最后一个参数传入到方法中
        APPLog.log("oldVersion===$oldVersion===newVersion==$newVersion")
        when {
            newVersion <= 2 -> {
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                }, WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java)
            }
            newVersion <= 3 -> {
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                }, WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java)
            }
            newVersion <= 4 -> {
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                }, WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java)
            }
            newVersion <= 6 -> {
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java
                )
            }
            newVersion <= 9 -> {
                //EmailMessageUser增加content字段
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java
                )
            }
            newVersion <= 10 -> {
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java
                )
            }
            newVersion <= 11 -> {
                //新增邮件和新闻的插入时间
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java
                )
            }
            newVersion <= 12 -> {
                //新增TaskBean表
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, true)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, true)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java
                )
            }
            newVersion <= 13 -> {
                //PlanUser增加status字段
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java,
                        TaskBeanDao::class.java
                )
            }
            newVersion <= 14 -> {
                //TaskBean增加date字段
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java,
                        TaskBeanDao::class.java
                )

            }
            newVersion <= 15 -> {
                //EmailUserDao增加isStart字段 是否开启接收邮件
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java,
                        TaskBeanDao::class.java
                )

            }
            newVersion <= 16->{
                // TaskBean增加startTime、endTime
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java,
                        TaskBeanDao::class.java
                )
            }
            newVersion <= 17->{
                // 新增服务通知、公司公告NoticeUserDao
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java,
                        TaskBeanDao::class.java,
                        NoticeUserDao::class.java
                )
            }
            newVersion <= 18->{
                // 未接来电记录MissedCallRecordUserDao
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java,
                        TaskBeanDao::class.java,
                        NoticeUserDao::class.java,
                        MissedCallRecordUserDao::class.java
                )
            }
            newVersion <= 20->{
                //增加录音数据库
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java,
                        TaskBeanDao::class.java,
                        NoticeUserDao::class.java,
                        MissedCallRecordUserDao::class.java,
                        AudioRecordUserDao::class.java
                )
            }
            newVersion <= 21->{
                //AudioRecordUser增加isUpload fileSize 字段
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java,
                        TaskBeanDao::class.java,
                        NoticeUserDao::class.java,
                        MissedCallRecordUserDao::class.java,
                        AudioRecordUserDao::class.java
                )
            }

            newVersion <= 22->{
                //增加资源库最近播放
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                        WeatherUserDao::class.java,
                        EmailMessageUserDao::class.java,
                        WallpaperUserDao::class.java,
                        EmailUserDao::class.java,
                        GlobalClockUserDao::class.java,
                        MessageUserDao::class.java,
                        NewsUserDao::class.java,
                        PlanUserDao::class.java,
                        ReminderUserDao::class.java,
                        ScheduleUserDao::class.java,
                        WallpaperUserDao::class.java,
                        WeatherUserDao::class.java,
                        AlarmUserDao::class.java,
                        TaskBeanDao::class.java,
                        NoticeUserDao::class.java,
                        MissedCallRecordUserDao::class.java,
                        AudioRecordUserDao::class.java,
                        RecentlyPlayedUserDao::class.java
                )
            }
            newVersion <= 23->{
                //录音表增加name字段
                MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
                    override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                        DaoMaster.createAllTables(db, ifNotExists)
                    }

                    override fun onDropAllTables(db: Database, ifExists: Boolean) {
                        DaoMaster.dropAllTables(db, ifExists)
                    }

                },
                    WeatherUserDao::class.java,
                    EmailMessageUserDao::class.java,
                    WallpaperUserDao::class.java,
                    EmailUserDao::class.java,
                    GlobalClockUserDao::class.java,
                    MessageUserDao::class.java,
                    NewsUserDao::class.java,
                    PlanUserDao::class.java,
                    ReminderUserDao::class.java,
                    ScheduleUserDao::class.java,
                    WallpaperUserDao::class.java,
                    WeatherUserDao::class.java,
                    AlarmUserDao::class.java,
                    TaskBeanDao::class.java,
                    NoticeUserDao::class.java,
                    MissedCallRecordUserDao::class.java,
                    AudioRecordUserDao::class.java,
                    RecentlyPlayedUserDao::class.java
                )
            }
        }
    }


}

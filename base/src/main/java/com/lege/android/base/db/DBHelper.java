package com.lege.android.base.db;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.lege.android.base.PreferencesManager;
import com.lege.android.base.log.APPLog;
import com.lege.android.base.string.DateFormatUtil;
import com.lege.android.db.AlarmUserDao;
import com.lege.android.db.AudioRecordUserDao;
import com.lege.android.db.CollectionUserDao;
import com.lege.android.db.DaoMaster;
import com.lege.android.db.DaoSession;
import com.lege.android.db.EmailMessageUserDao;
import com.lege.android.db.EmailUserDao;
import com.lege.android.db.GlobalClockUserDao;
import com.lege.android.db.MessageUserDao;
import com.lege.android.db.MissedCallRecordUserDao;
import com.lege.android.db.NewRemindUserDao;
import com.lege.android.db.NewsUserDao;
import com.lege.android.db.NoticeUserDao;
import com.lege.android.db.PlanUserDao;
import com.lege.android.db.RecentlyPlayedUserDao;
import com.lege.android.db.ReminderUserDao;
import com.lege.android.db.ScheduleUserDao;
import com.lege.android.db.TaskBeanDao;
import com.lege.android.db.UserDao;
import com.lege.android.db.WallpaperUserDao;
import com.lege.android.db.WeatherUserDao;

import org.greenrobot.greendao.annotation.Id;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.lege.android.base.constants.TaskConstants.TASK_ALARM;
import static com.lege.android.base.constants.TaskConstants.TASK_PLAN;
import static com.lege.android.base.constants.TaskConstants.TASK_REMIND;
import static com.lege.android.base.constants.TaskConstants.TASK_SCHEDULE;
import static com.lege.android.base.constants.TaskConstants.TASK_STATE_DEL;
import static com.lege.android.base.constants.TaskConstants.TASK_STATE_DELAY;
import static com.lege.android.base.constants.TaskConstants.TASK_STATE_NORMAL;


/**
 * Created by zhoushaoqing on 18-10-23.
 */

public class DBHelper {
    private DaoMasterHelper masterHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    /**
     * 设置greenDao
     */
    public void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        /*mHelper = new DaoMaster.DevOpenHelper(this, "lege-db", null);
        db = mHelper.getWritableDatabase();*/
        masterHelper = new DaoMasterHelper(context, "lege-db", null);
        db = masterHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

        if (!PreferencesManager.getInstance().getBooleanResults2("is_transfer_db12", false)) {
            new Thread() {
                @Override
                public void run() {
                    new UpgradeTo12().transfer();
                    PreferencesManager.getInstance().saveBooleanResults("is_transfer_db12", true);
                }
            }.start();
        }
        if (!PreferencesManager.getInstance().getBooleanResults2("isdb_13_to_14", false)) {
            List<TaskBean> list = DBHelper.getInstance().getTaskBeanDao().loadAll();
            for (TaskBean taskBean : list) {
                if (taskBean.getDatetime() != null) {
                    taskBean.setDate(taskBean.getDatetime().split(" ")[0]);
                }

                DBHelper.getInstance().updateTaskBean(taskBean);
            }
            PreferencesManager.getInstance().saveBooleanResults("isdb_13_to_14", true);
        }

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


    public SQLiteDatabase getDb() {
        return db;
    }


    public void deleSQL() {
        SQLiteDatabase db = masterHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoMaster.dropAllTables(daoMaster.getDatabase(), true);
        DaoMaster.createAllTables(daoMaster.getDatabase(), true);
    }




    private static DBHelper instance;
    private Application context;
    public void initContext(Application context){
        this.context = context;
    }
    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    private UpgradeTo12 upgradeTo12 = new UpgradeTo12();

    public void addAudioRecord(AudioRecordUser record){
        getAudioRecordDao().insertOrReplace(record);
        long max = System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000L;
        List<AudioRecordUser> old = getAudioRecordDao().queryBuilder().where(AudioRecordUserDao.Properties.Timestemp.lt(max)).list();
        if(!old.isEmpty()){
            removeAudioRecord(old);
        }
    }
    public List<AudioRecordUser> getAudioRecordInMonth(){
        long max = System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000L;
        return getAudioRecordDao().queryBuilder().where(AudioRecordUserDao.Properties.Timestemp.ge(max)).orderDesc(AudioRecordUserDao.Properties.Timestemp).list();
    }

    //获取过期的录音
    public List<AudioRecordUser> getOverdueRecord(){
        long max = System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000L;
        return getAudioRecordDao().queryBuilder().where(AudioRecordUserDao.Properties.Timestemp.lt(max)).list();
    }
    public void removeAudioRecord(List<AudioRecordUser> removedList){
        for (AudioRecordUser audioRecordUser : removedList) {
            getAudioRecordDao().delete(audioRecordUser);
            File file = new File(audioRecordUser.getPath());
            if(file.exists()){
                file.delete();
            }
        }
    }
    /**
     * 增加通话记录数据
     */
    public void addUserData(User user) {
        getUserDao().insertOrReplace(user);
    }

    /**
     * 增加数据
     */
    public void addCollectionUserData(CollectionUser user) {
        getCollectionUserDao().insertOrReplace(user);
    }

    /**
     * 根据主键删除User
     *
     * @param id User的主键Id
     */
    public void deleteUserById(long id) {
        getUserDao().deleteByKey(id);
    }

    /**
     * 根据主键删除User
     *
     * @param id User的主键Id
     */
    public void deleteCollectionUserById(long id) {
        getCollectionUserDao().deleteByKey(id);
    }


    /**
     * 更改数据
     */
    public void updateUserDate(User user) {
        getUserDao().update(user);
    }

    /**
     * 根据id查找user所有数据
     */
    public User findUserDataById(long id) {
        User user = getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(id)).unique();
        return user;
    }

    /**
     * 更改数据
     */
    public void updateCollectionUserDate(CollectionUser user) {
        getCollectionUserDao().update(user);
    }

    /**
     * 查找数据
     */
    public List<User> findUserAllData() {
        List<User> users = getUserDao().loadAll();
        Collections.sort(users, new CompareId(false));
        return users;
    }

    /**
     * 根据未读信息的读取状态查找user所有数据
     */
    public List<User> findUserAllDataByReadstate(String readstate) {
        List<User> users = findUserAllDataByUnanswerState("未接");
        List<User> unviewuser = new ArrayList<>();
        if (users != null && users.size() > 0) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getReaded().equals(readstate)) {
                    unviewuser.add(users.get(i));
                }
            }
        }
        return unviewuser;
    }

    /**
     * 根据未接状态查找user所有数据
     */
    public List<User> findUserAllDataByUnanswerState(String state) {
        List<User> users = getUserDao().queryBuilder().where(UserDao.Properties.State.eq(state)).list();
        return users;
    }

    /**
     * 查找收藏的数据
     */
    public List<CollectionUser> findCollectionIsOkUserAllData() {
        List<CollectionUser> users = getCollectionUserDao().queryBuilder().where(CollectionUserDao.Properties.Collection.eq("yes")).list();
        return users;
    }


    /**
     * 根据UID查找数据
     */
    public CollectionUser findCollectionWithUidUserData(String uid) {
        CollectionUser collectionUser = getCollectionUserDao().queryBuilder().where(CollectionUserDao.Properties.Uid.eq(uid)).unique();
        return collectionUser;
    }

    /**
     * 根据Huanxin name查找数据
     */
    public CollectionUser findCollectionWithHuanXinUserData(String username) {
        CollectionUser collectionUser = getCollectionUserDao().queryBuilder().where(CollectionUserDao.Properties.Huanxin.eq(username)).unique();
        return collectionUser;
    }


    /**
     * 查找已经收藏的数据
     */
    public List<CollectionUser> findCollectionUserAllData() {
        List<CollectionUser> users = getCollectionUserDao().loadAll();
        return users;
    }

    private static class CompareId implements Comparator<User> {
        boolean is_Ascend;

        public CompareId(boolean b) {
            is_Ascend = b;
        }

        @Override
        public int compare(User user1, User user2) {
            Long a, b;
            if (is_Ascend) {
                a = user1.getId();
                b = user2.getId();
            } else {
                a = user2.getId();
                b = user1.getId();
            }

            if (a > b)
                return 1;
            else if (a == b)
                return 0;
            else
                return -1;
        }
    }

    public AudioRecordUserDao getAudioRecordDao(){
        return mDaoSession.getAudioRecordUserDao();
    }
    /**
     * 获取 getUserDao
     */
    public UserDao getUserDao() {

        return mDaoSession.getUserDao();
    }

    /**
     * 获取 CollectionUserDao
     */
    public CollectionUserDao getCollectionUserDao() {
        return mDaoSession.getCollectionUserDao();
    }


    /**
     * 获取 PlanUserDao
     */
    public PlanUserDao getPlanUserDao() {
        return mDaoSession.getPlanUserDao();
    }

    /**
     * 增加Plan数据
     */
    public void addPlanData(PlanUser user) {
        PlanUser old = findPlanUserByTaskid(user.getTaskid());
        if (old != null) {
            user.setId(old.getId());
            updatePlanUserDate(user);
        } else {
            getPlanUserDao().insert(user);
        }
        addTaskBean(upgradeTo12.plan2taskBean(user));
    }


    /**
     * 更改plan数据
     */
    public void updatePlanUserDate(PlanUser user) {
        getPlanUserDao().update(user);
        TaskBean task = findTaskByTaskId(user.getTaskid(), TASK_PLAN);
        if (task == null) {
            addTaskBean(upgradeTo12.plan2taskBean(user));
        } else {
            long id = task.getId();
            String time = task.getStartTime();
            task = upgradeTo12.plan2taskBean(user);
            task.setId(id);
            task.setStartTime(time);
            updateTaskBean(task);
        }
    }

    /**
     * 根据plan 的planid删除PlanUser
     */
    public void deletePlanUserByPlanid(int planid) {

        PlanUser planUser = getPlanUserDao().queryBuilder()
                .where(PlanUserDao.Properties.Taskid.eq(planid)).unique();
        getPlanUserDao().delete(planUser);
        TaskBean task = findTaskByTaskId(planid, TASK_PLAN);
        if (task != null) {
            removeTaskBean(task);
        }
    }

    public void deletePlanUser(PlanUser planUser) {
        getPlanUserDao().delete(planUser);
        TaskBean task = findTaskByTaskId(planUser.getTaskid(), TASK_PLAN);
        if (task != null) {
            removeTaskBean(task);
        }
    }

    /**
     * 查找Plan所有数据
     */
    public List<PlanUser> findPlanUserAllData() {
        List<PlanUser> users = getPlanUserDao().loadAll();
        return users;
    }

    public PlanUser findPlanUserByTaskid(int taskid) {
        PlanUser planUser = getPlanUserDao().queryBuilder()
                .where(PlanUserDao.Properties.Taskid.eq(taskid)).unique();
        return planUser;
    }

    /**
     * 查找reminder所以数据
     */
    public List<ReminderUser> findReminderAllData() {
        List<ReminderUser> reminderUsers = new ArrayList<>();
        reminderUsers = getReminderUserDao().loadAll();
        return reminderUsers;
    }

    /**
     * 查找newreminder所以数据
     */
    public List<NewRemindUser> findNewRemindAllData() {
        List<NewRemindUser> newreminderUsers = new ArrayList<>();
        newreminderUsers = getNewRemindUserDao().loadAll();
        return newreminderUsers;
    }

    /**
     * 获取 MessageUserDao
     */
    public MessageUserDao getMessageUserDao() {
        return mDaoSession.getMessageUserDao();
    }

    /**
     * 增加message数据
     */
    public void addMessageData(MessageUser user) {
        getMessageUserDao().insertOrReplace(user);
    }

    /**
     * 更改message数据
     */
    public void updateMessageUserDate(MessageUser user) {
        getMessageUserDao().update(user);
    }

    /**
     * 更改reminderUser数据
     */
    public void updateReminderUserDate(ReminderUser user) {
        getReminderUserDao().update(user);
    }

    /**
     * 更改newreminderUser数据
     */
    public void updateNewRemindUserDate(NewRemindUser user) {
        getNewRemindUserDao().update(user);
    }

    /**
     * 删除message数据
     */
    public void deleteMessageUserData(MessageUser user) {
        getMessageUserDao().delete(user);
    }


    /**
     * 根据message 的messageid删除MessageUser
     */
    public void deleteMessageUserByLetterid(int letterid) {

        MessageUser messageUser = getMessageUserDao().queryBuilder()
                .where(MessageUserDao.Properties.Letterid.eq(letterid)).unique();
        if (messageUser != null) {
            getMessageUserDao().delete(messageUser);
        } else {
            APPLog.log("deleteMessageUserByLetterid ====is null");
        }
    }

    /**
     * 根据读取状态查找message所有数据
     */
    public List<MessageUser> findMessageUserAllDataByReadstate(String readstate) {
        List<MessageUser> users = getMessageUserDao().queryBuilder().where(MessageUserDao.Properties.Readed.eq(readstate)).list();
        return users;
    }


    /**
     * 查找Message所有数据
     */
    public List<MessageUser> findMessageUserAllData() {
        List<MessageUser> users = getMessageUserDao().loadAll();
        return users;
    }

    /**
     * 根据letterid查找数据
     */
    public MessageUser findMessageUserWithLetteridUserData(int letterid) {
        MessageUser messageUser = getMessageUserDao().queryBuilder().where(MessageUserDao.Properties.Letterid.eq(letterid)).unique();
        return messageUser;
    }

    /**
     * 根据newsid查找数据
     */
    public MessageUser findMessageUserWithNewsidUserData(int newsid) {
        MessageUser messageUser = getMessageUserDao().queryBuilder().where(MessageUserDao.Properties.Newsid.eq(newsid)).unique();
        return messageUser;
    }


    /**
     * 根据trafficid查找数据
     */
    public MessageUser findMessageUserWithTrafficidUserData(int trafficid) {
        MessageUser messageUser = getMessageUserDao().queryBuilder().where(MessageUserDao.Properties.Trafficid.eq(trafficid)).unique();
        return messageUser;
    }


    /**
     * 根据reminderid查找数据
     *
     * @param reminderid
     */
    public ReminderUser findReminderUserWithReminderidUserData(int reminderid) {
        ReminderUser reminderUser = getReminderUserDao().queryBuilder().where(ReminderUserDao.Properties.Remindid.eq(reminderid)).unique();
        return reminderUser;
    }

    /**
     * 根据newreminderid查找数据
     *
     * @param reminderid
     */
    public NewRemindUser findNewRemindUserWithReminderidUserData(int reminderid) {
        NewRemindUser reminderUser = getNewRemindUserDao().queryBuilder().where(NewRemindUserDao.Properties.Id.eq(reminderid)).unique();
        return reminderUser;
    }

    /**
     * 获取 GlobalClockUserDao
     */
    public GlobalClockUserDao getGlobalClockUserDao() {
        return mDaoSession.getGlobalClockUserDao();
    }

    /**
     * 获取 WeatherUserDao
     */
    public WeatherUserDao getWeatherUserDao() {
        return mDaoSession.getWeatherUserDao();
    }

    /**
     * 获取 WallpaperUser
     */
    public WallpaperUserDao getWallpaperUserDao() {
        return mDaoSession.getWallpaperUserDao();
    }

    /**
     * 获取 EmailUserDao
     */
    public EmailUserDao getEmailUserDao() {
        return mDaoSession.getEmailUserDao();
    }

    /**
     * 获取 EmailMessageUserDao
     */
    public EmailMessageUserDao getEmailMessageUserDao() {
        return mDaoSession.getEmailMessageUserDao();
    }

    public TaskBeanDao getTaskBeanDao() {
        return mDaoSession.getTaskBeanDao();
    }

    /**
     * 获取服务通知、公司公告NoticeUserDao
     */
    public NoticeUserDao getNoticeUserDao() {
        return mDaoSession.getNoticeUserDao();
    }

    /**
     * 获取未接电话MissedCallRecordUserDao
     */
    public MissedCallRecordUserDao getMissedCallRecordUserDao() {
        return mDaoSession.getMissedCallRecordUserDao();
    }

    /**
     * 删除邮箱，同时删除改邮箱的所有邮件
     *
     * @param emailUser
     * @return
     */
    public void deleteEmail(EmailUser emailUser) {
        getEmailUserDao().delete(emailUser);
        deleteEmailByUserName(emailUser.getUsername());
    }

    /**
     * 删除已读邮件
     */
    public void deleteAllEmail() {
        List<EmailMessageUser> readedEmail = getEmailMessageUserDao().loadAll();
        if (readedEmail != null && !readedEmail.isEmpty()) {
            for (EmailMessageUser messageUser : readedEmail) {
                deleteEmailById(messageUser.getEmailmessageid());
            }

        }
    }

    /**
     * 根据id查询邮件
     *
     * @param emailMessageId
     * @return
     */
    public EmailMessageUser findEmailMessageByID(String emailMessageId) {
        return getEmailMessageUserDao().queryBuilder().where(EmailMessageUserDao.Properties.Emailmessageid.eq(emailMessageId)).unique();
    }

    /**
     * 根据邮箱删除邮件
     *
     * @param userName
     */
    public void deleteEmailByUserName(String userName) {
        List<EmailMessageUser> list = findEmailListbyAccount(userName);
        getEmailMessageUserDao().deleteInTx(list);
    }

    public List<EmailMessageUser> findEmailListbyAccount(String userName) {
        return getEmailMessageUserDao().queryBuilder()
                .where(EmailMessageUserDao.Properties.Username.eq(userName)).list();
    }

    /**
     * 获取 EmailMessageUserDao
     */
    public AlarmUserDao getAlarmUserDao() {
        return mDaoSession.getAlarmUserDao();
    }

    /**
     * 获取 ScheduleUserDao
     */
    public ScheduleUserDao getScheduleUserDao() {
        return mDaoSession.getScheduleUserDao();
    }

    /**
     * 获取 ReminderUserDao
     */
    public ReminderUserDao getReminderUserDao() {
        return mDaoSession.getReminderUserDao();
    }

    /**
     * 获取 NewRemindUserDao
     */
    public NewRemindUserDao getNewRemindUserDao() {
        return mDaoSession.getNewRemindUserDao();
    }

    /**
     * 增加GlobalClockUser数据
     */
    public void addGlobalClockData(GlobalClockUser user) {
        getGlobalClockUserDao().insertOrReplace(user);
    }

    /**
     * 更改GlobalClockUser数据
     */
    public void updateGlobalClockUserDate(GlobalClockUser user) {
        getGlobalClockUserDao().update(user);
    }


    /**
     * 根据globalclock的globalclockid删除GlobalClockUser
     */
    public void deleteGlobalClockUserByReMessageid(int globalclockid) {

        GlobalClockUser globalClockUser = getGlobalClockUserDao().queryBuilder()
                .where(GlobalClockUserDao.Properties.GlobalClockid.eq(globalclockid)).unique();
        getGlobalClockUserDao().delete(globalClockUser);
    }

    public void deleteAllGlobalClock() {
        getGlobalClockUserDao().deleteAll();
    }

    /**
     * 查找GlobalClock所有数据
     */
    public List<GlobalClockUser> findGlobalClockUserAllData() {
        List<GlobalClockUser> users = getGlobalClockUserDao().loadAll();
        return users;
    }

    /**
     * 根据GlobalClockid查找GlobalClock数据
     */
    public GlobalClockUser findGlobalClockUserByGlobalclockid(int globalclockid) {
        GlobalClockUser user = getGlobalClockUserDao().queryBuilder()
                .where(GlobalClockUserDao.Properties.GlobalClockid.eq(globalclockid)).unique();
        return user;
    }

    /**
     * 增加reminder数据
     */
    public void addReminderData(ReminderUser user) {
        getReminderUserDao().insertOrReplace(user);
    }

    /**
     * 删除ReminderUser数据
     */
    public void deleteReminderUserData(ReminderUser user) {
        getReminderUserDao().delete(user);
    }


    /**
     * 增加newreminder数据
     */
    public void addNewReminderData(NewRemindUser user) {
        getNewRemindUserDao().insertOrReplace(user);
    }

    /**
     * 删除newReminderUser数据
     */
    public void deleteNewReminderUserData(NewRemindUser user) {
        getNewRemindUserDao().delete(user);
    }


    /**
     * 增加weather数据
     */
    public void addWeatherData(WeatherUser user) {
        getWeatherUserDao().insertOrReplace(user);
    }

    /**
     * 删除WeatherUser数据
     */
    public void deleteWeatherUserData(WeatherUser user) {
        getWeatherUserDao().delete(user);
    }

    /**
     * 查找WeatherUser所有数据
     */
    public List<WeatherUser> findWeatherUserAllData() {
        List<WeatherUser> users = getWeatherUserDao().loadAll();
        return users;
    }


    /**
     * 增加Wallpaper数据
     */
    public void addWallpaerUserData(WallpaperUser user) {
        getWallpaperUserDao().insertOrReplace(user);
    }

    /**
     * 增加Wallpaper数据
     */
    public void deleteWallpaerUserData(WallpaperUser user) {
        getWallpaperUserDao().delete(user);
    }

    /**
     * 根据Wallpaperid查找 WallpaperUser
     */
    public WallpaperUser findWallpaperUserByWallpaperid(int Wallpaperid) {
        WallpaperUser wallpaperUser = getWallpaperUserDao().queryBuilder()
                .where(WallpaperUserDao.Properties.Wallpaperid.eq(Wallpaperid)).unique();
        return wallpaperUser;
    }

    /**
     * 根据Wallpaperid查找 WallpaperUser
     */
    public List<WallpaperUser> findWallpaperUser() {
        List<WallpaperUser> wallpaperUser = getWallpaperUserDao().queryBuilder().list();
        return wallpaperUser;
    }

    /**
     * 增加EmailUser数据
     */
    public void addEmailUserData(EmailUser user) {
        getEmailUserDao().insertOrReplace(user);
    }

    /**
     * 根据Emailid查找 EmailUser
     */
    public EmailUser findEmailUserByEmailid(int emailid) {
        EmailUser emailUser = getEmailUserDao().queryBuilder()
                .where(EmailUserDao.Properties.Emailid.eq(emailid)).unique();
        return emailUser;
    }

    /**
     * 更改Email数据
     */
    public void updateEmailUserData(EmailUser user) {
        getEmailUserDao().update(user);
    }

    /**
     * 增加EmailMessageUser数据
     */
    public void addEmailMessageUserData(EmailMessageUser user) {
        APPLog.d("数据库","addEmailMessageUserData");
        getEmailMessageUserDao().insertOrReplace(user);
    }

    /**
     * 更改Email数据
     */
    public void updateEmailMessageUserData(EmailMessageUser user) {
        APPLog.d("数据库","updateEmailMessageUserData");
        getEmailMessageUserDao().update(user);
    }

    /**
     * 根据EmailMessageid查找EmailMessageUser数据
     */
    public EmailMessageUser findEmailMessageUserDataByEmailMessageid(String emailmessageid) {
        EmailMessageUser emailMessageUser = getEmailMessageUserDao().queryBuilder()
                .where(EmailMessageUserDao.Properties.Emailmessageid.eq(emailmessageid)).unique();
        return emailMessageUser;
    }

    /**
     * 根据主题查找EmailMessageUser数据
     */
    public List<EmailMessageUser> findEmailMessageUserDataBySubject(String subject) {
        List<EmailMessageUser> emailMessageUser = getEmailMessageUserDao().queryBuilder()
                .where(EmailMessageUserDao.Properties.Subject.eq(subject)).list();
        return emailMessageUser;
    }

    /**
     * 根据username查找EmailMessageUser列表数据
     */
    public List<EmailMessageUser> findEmailMessageUserDataByUsername(String username) {
        List<EmailMessageUser> emailMessageUserList = getEmailMessageUserDao().queryBuilder()
                .where(EmailMessageUserDao.Properties.Username.eq(username)).list();
        return emailMessageUserList;
    }

    /**
     * 根据username查找EmailMessageUser列表数据
     */
    public List<EmailMessageUser> findEmailMessageUserDataByReaded(String readed) {
        List<EmailMessageUser> emailMessageUserList = getEmailMessageUserDao().queryBuilder()
                .where(EmailMessageUserDao.Properties.Readed.eq(readed)).orderDesc(EmailMessageUserDao.Properties.Insertiontime).list();
        return emailMessageUserList;
    }

    /**
     * 根据阅读状态和删除状态查找EmailMessageUser列表数据
     */
    public List<EmailMessageUser> findEmailMessageUserDataByReadedAndDeleted(String readed, String deleted) {
        List<EmailMessageUser> emailMessageUserList = getEmailMessageUserDao().queryBuilder()
                .where(EmailMessageUserDao.Properties.Readed.eq(readed), EmailMessageUserDao.Properties.Deleted.eq(deleted)).orderDesc(EmailMessageUserDao.Properties.Senddate).list();
        return emailMessageUserList;
    }

    public long getMyJobCount() {
        long scheduleCount = getScheduleUserDao().queryBuilder().where(ScheduleUserDao.Properties.Alert.gt(0)).count();
        long planCount = getPlanUserDao().queryBuilder().count();
        return planCount + scheduleCount;
    }

    /**
     * 获取未读消息数量
     *
     * @return
     */
    public long getUnReadMessage() {
        long unReadEmail = getEmailMessageUserDao().queryBuilder().where(EmailMessageUserDao.Properties.Readed.eq("no")).count();
//        long unReadNews = getNewsUserDao().queryBuilder().where(NewsUserDao.Properties.Readed.eq("no")).count();
        return unReadEmail;
    }

    public long getUnReadEmail() {
        return getEmailMessageUserDao().queryBuilder().where(EmailMessageUserDao.Properties.Readed.eq("no")).count();
    }

    public long getUnReadNews() {
        long unReadNews = getNewsUserDao().queryBuilder().where(NewsUserDao.Properties.Readed.eq("no")).count();
        return unReadNews;
    }

    /**
     * 增加AlarmUser数据
     */
    public void addAlarmUserData(AlarmUser user) {
        getAlarmUserDao().insertOrReplace(user);
    }

    /**
     * 更改AlarmUser数据
     */
    public void updatAlarmUserData(AlarmUser user) {
        getAlarmUserDao().update(user);
    }

    /**
     * 根据alarmid查找AlarmUser数据
     */
    public AlarmUser findAlarmUserDataByAlarmid(int alarmid) {
        AlarmUser alarmUser = getAlarmUserDao().queryBuilder()
                .where(AlarmUserDao.Properties.Alarmid.eq(alarmid)).unique();
        return alarmUser;
    }

    /**
     * 查找所用的AlarmUser数据
     */
    public List<AlarmUser> findAllAlarmUserData() {
        List<AlarmUser> alarmUserList = new ArrayList<>();
        alarmUserList = getAlarmUserDao().loadAll();
        return alarmUserList;
    }

    /**
     * 增加ScheduleUser数据
     */
    public void addScheduleUserData(ScheduleUser user) {
        ScheduleUser old = findScheduleUserDataByScheduleid(user.getScheduleid());
        if (old != null) {
            user.setId(old.getId());
            updateScheduleUserData(user);
        } else {
            getScheduleUserDao().insert(user);
        }
        TaskBean taskbean = upgradeTo12.schedule2TaskBean(user);
        addTaskBean(taskbean);
    }

    /**
     * 更改ScheduleUser数据
     */
    public void updateScheduleUserData(ScheduleUser user) {
        getScheduleUserDao().update(user);
        TaskBean taskbean = findTaskByTaskId(user.getScheduleid(), TASK_SCHEDULE);
        if (taskbean == null) {
            taskbean = upgradeTo12.schedule2TaskBean(user);
            addTaskBean(taskbean);
        } else {
            long id = taskbean.getId();
            taskbean = upgradeTo12.schedule2TaskBean(user);
            taskbean.setId(id);
            updateTaskBean(taskbean);
        }
    }

    /**
     * 删除日程
     *
     * @param scheduleId
     */
    public void deleteSchedule(int scheduleId) {
        ScheduleUser scheduleUser = getScheduleUserDao().queryBuilder()
                .where(ScheduleUserDao.Properties.Scheduleid.eq(scheduleId)).unique();
        if (scheduleUser != null) {
            getScheduleUserDao().delete(scheduleUser);
        }
        TaskBean taskbean = findTaskByTaskId(scheduleId, TASK_SCHEDULE);
        if (taskbean != null) {
            removeTaskBean(taskbean);
        }
    }

    public void deleteSchedule(ScheduleUser scheduleUser) {
        getScheduleUserDao().delete(scheduleUser);
        TaskBean taskbean = findTaskByTaskId(scheduleUser.getScheduleid(), TASK_SCHEDULE);
        if (taskbean != null) {
            removeTaskBean(taskbean);
        }
    }

    /**
     * 根据isreminded状态查找ScheduleUser列表数据
     */
    public List<ScheduleUser> findScheduleUserDataByisReminded(String isreminded) {
        List<ScheduleUser> scheduleUserList = getScheduleUserDao().queryBuilder()
                .where(ScheduleUserDao.Properties.IsReminded.eq(isreminded)).list();
        return scheduleUserList;
    }

    /**
     * 查找所有的ScheduleUser数据
     */
    public List<ScheduleUser> findAllScheduleUserData() {
        List<ScheduleUser> scheduleUserList = new ArrayList<>();
        scheduleUserList = getScheduleUserDao().loadAll();
        return scheduleUserList;
    }

    /**
     * 获取未读日程
     */
    public List<ScheduleUser> findScheduleUserDataByRead(String readed) {
        List<ScheduleUser> scheduleUserList = getScheduleUserDao().queryBuilder()
                .where(ScheduleUserDao.Properties.Readed.eq(readed)).list();
        return scheduleUserList;
    }

    /**
     * 根据isreminded状态查找ScheduleUser列表数据
     */
    public List<ScheduleUser> findScheduleUserDataByisRemindedAndReadedstatus(String isreminded, String readed) {
        List<ScheduleUser> scheduleUserList = getScheduleUserDao().queryBuilder()
                .where(ScheduleUserDao.Properties.IsReminded.eq(isreminded)).list();
        List<ScheduleUser> finalList = new ArrayList<>();
        if (scheduleUserList != null && scheduleUserList.size() > 0) {
            for (int i = 0; i < scheduleUserList.size(); i++) {
                if (scheduleUserList.get(i).getReaded().equals(readed)) {
                    finalList.add(scheduleUserList.get(i));
                }
            }
        }
        return finalList;
    }

    /**
     * 根据scheduleid查找ScheduleUser数据
     */
    public ScheduleUser findScheduleUserDataByScheduleid(int scheduleid) {
        ScheduleUser scheduleUser = getScheduleUserDao().queryBuilder()
                .where(ScheduleUserDao.Properties.Scheduleid.eq(scheduleid)).unique();
        return scheduleUser;
    }

    /**
     * 获取 NewsUserDao
     */
    public NewsUserDao getNewsUserDao() {
        return mDaoSession.getNewsUserDao();
    }

    /**
     * 增加NewsUser数据
     */
    public void addNewsUserData(NewsUser user) {
        getNewsUserDao().insertOrReplace(user);
    }

    /**
     * 更改NewsUser数据
     */
    public void updateNewsUserData(NewsUser user) {
        getNewsUserDao().update(user);
    }

    /**
     * 根据readed状态查找NewsUser数据
     */
    public List<NewsUser> findNewsUserByReadedStatus(String readstatus) {
        List<NewsUser> newsUserList = getNewsUserDao().queryBuilder()
                .where(NewsUserDao.Properties.Readed.eq(readstatus)).orderDesc(NewsUserDao.Properties.Insertiontime).list();
        return newsUserList;
    }

    /**
     * 根据newsid查找NewsUser数据
     */
    public NewsUser findNewsUserDataBynewsid(int newsid) {
        NewsUser newsUser = getNewsUserDao().queryBuilder()
                .where(NewsUserDao.Properties.Newsid.eq(newsid)).unique();
        return newsUser;
    }

    public void deleteNewsById(int newsId) {
        NewsUser newsUser = getNewsUserDao().queryBuilder()
                .where(NewsUserDao.Properties.Newsid.eq(newsId)).unique();
        if (newsUser != null) {
            getNewsUserDao().delete(newsUser);
        }
    }

    public void deleteEmailById(String emailId) {
        EmailMessageUser emailUser = getEmailMessageUserDao().queryBuilder()
                .where(EmailMessageUserDao.Properties.Emailmessageid.eq(emailId)).unique();
        if (emailUser != null) {
            emailUser.setDeleted("y");
            getEmailMessageUserDao().update(emailUser);
        }
    }

    public EmailUser findEmailbyAccount(String emailFrom) {
        EmailUser email = getEmailUserDao().queryBuilder()
                .where(EmailUserDao.Properties.Username.eq(emailFrom)).unique();
        return email;
    }

    public void addTaskBean(TaskBean taskBean) {
        TaskBean old = findTaskByTaskId(taskBean.getTaskId());
        if (old != null) {
            taskBean.setId(old.getId());
            getTaskBeanDao().update(taskBean);
        } else {
            getTaskBeanDao().insertOrReplace(taskBean);
        }

    }

    /**
     * 查询任务
     *
     * @return
     */
    public TaskBean findTaskByTaskId(int taskId, String type) {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.TaskId.eq(taskId), TaskBeanDao.Properties.Type.eq(type)).unique();
    }

    /***
     * 查询未完成任务
     * **/

    public TaskBean findTaskByNormal(int taskId,String type){
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.TaskId.eq(taskId),TaskBeanDao.Properties.State.eq(TASK_STATE_NORMAL), TaskBeanDao.Properties.Type.in(TASK_SCHEDULE,TASK_PLAN)).unique();

    }

    /**
     * 查询任务
     *
     * @param taskId
     * @return
     */
    public TaskBean findTaskByTaskId(int taskId) {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.TaskId.eq(taskId)).unique();
    }

    /**
     * 查询未删除的任务
     *
     * @return
     */
    public List<TaskBean> findUnDelTask() {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.notEq(TASK_STATE_DEL))
                .orderAsc(TaskBeanDao.Properties.Datetime).list();
    }

    /**
     * 查询已删除任务
     *
     * @return
     */
    public List<TaskBean> findDelTask() {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.eq(TASK_STATE_DEL)).list();
    }

    /**
     * 获取当天以及之后的回收站任务
     *
     * @return
     */
    public List<TaskBean> findDelTaskTodayAndAfterToday() {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.eq(TASK_STATE_DEL),
                        TaskBeanDao.Properties.Datetime.ge(DateFormatUtil.getCurrentDate() + " 00:00")
                )
                .list();
    }

    /**
     * 获取当天以及之后的未删除任务
     *
     * @return
     */
    public List<TaskBean> findTaskTodayAndAfterToday() {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.notEq(TASK_STATE_DEL),
                        TaskBeanDao.Properties.Datetime.ge(DateFormatUtil.getCurrentDate() + " 00:00")
                )
                .list();
    }

    /***
     * 获取时间已过还未执行的任务
     * ***/

    public List<TaskBean> findTaskTodayBeforeUnexecuted() {

        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.in(TASK_STATE_NORMAL,TASK_STATE_DELAY),
                        TaskBeanDao.Properties.IsRead.eq(0),
                        TaskBeanDao.Properties.Datetime.lt(DateFormatUtil.getSystemCurrent_Time2())
                ).orderAsc(TaskBeanDao.Properties.Datetime)
                .list();
    }

    /***
     * 获取当天还未到点未执行的任务
     * ***/

    public List<TaskBean> findTaskTodayUnexecuted() {

        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.eq(TASK_STATE_NORMAL),
                        TaskBeanDao.Properties.IsRead.eq(0),
                        TaskBeanDao.Properties.Datetime.between(DateFormatUtil.getSystemCurrent_Time2(), DateFormatUtil.getCurrentDate() + "24:00")
                ).orderAsc(TaskBeanDao.Properties.Datetime)
                .list();
    }

//            return getTaskBeanDao().queryBuilder()
//                .where(TaskBeanDao.Properties.State.notEq(TASK_STATE_DEL))
//            .orderAsc(TaskBeanDao.Properties.Datetime).list();

    /**
     * 查询指定某些日期的已删除的任务
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<TaskBean> findDelTaskBetweenDate(String startDate, String endDate) {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.eq(TASK_STATE_DEL),
                        TaskBeanDao.Properties.Date.between(startDate, endDate)
                )
                .list();
    }

    /**
     * 查询指定某些日期的已删除的任务
     *
     * @param date
     * @return
     */
    public List<TaskBean> findDelTaskByDate(Collection<String> date) {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.eq(TASK_STATE_DEL),
                        TaskBeanDao.Properties.Date.in(date)
                )
                .list();
    }

    /**
     * 查询指定日期之前的已删除的任务
     *
     * @param date
     * @return
     */
    public List<TaskBean> findDelTaskBeforeDate(String date) {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.eq(TASK_STATE_DEL),
                        TaskBeanDao.Properties.Date.lt(date)
                )
                .list();
    }

    /**
     * 查询指定某些日期的未删除的任务
     *
     * @param date
     * @return
     */
    public List<TaskBean> findTaskByDate(Collection<String> date) {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.notEq(TASK_STATE_DEL),
                        TaskBeanDao.Properties.Date.in(date)
                )
                .list();
    }

    /**
     * 查询指定日期之前的已删除的任务
     *
     * @param date
     * @return
     */
    public List<TaskBean> findTaskBeforeDate(String date) {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.notEq(TASK_STATE_DEL),
                        TaskBeanDao.Properties.Date.lt(date)
                )
                .list();
    }

    /**
     * 查询指定日期之间的未删除的任务
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<TaskBean> findTaskBetweenDate(String startDate, String endDate) {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.notEq(TASK_STATE_DEL),
                        TaskBeanDao.Properties.Date.between(startDate, endDate)
                )
                .list();
    }

    /**
     * 更新任务
     *
     * @param task
     */
    public void updateTaskBean(TaskBean task) {
        getTaskBeanDao().update(task);
    }

    /**
     * 删除任务
     *
     * @param taskBean
     */
    public void removeTaskBean(TaskBean taskBean) {
        getTaskBeanDao().delete(taskBean);
    }

    /**
     * 查询闹钟任务
     *
     * @return
     */
    public List<TaskBean> findAlarmTask() {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.Type.eq(TASK_ALARM)).list();
    }

    /**
     * 查询日程任务
     *
     * @return
     */
    public List<TaskBean> findScheduleTask() {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.Type.eq(TASK_SCHEDULE),
                        TaskBeanDao.Properties.State.notEq(TASK_STATE_DEL)).list();
    }

    /**
     * 查询待办任务
     *
     * @return
     */
    public List<TaskBean> findPlanTask() {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.Type.eq(TASK_PLAN), TaskBeanDao.Properties.State.notEq(TASK_STATE_DEL)).list();
    }

    /**
     * 查询提醒任务
     *
     * @return
     */
    public List<TaskBean> findRemindTask() {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.Type.eq(TASK_REMIND)).list();
    }

    /**
     * 根据Scheduleid查询日程任务
     *
     * @return
     */
    public ScheduleUser findScheduleUserDataByscheduleId(int scheduleId) {
        ScheduleUser scheduleUser = getScheduleUserDao().queryBuilder()
                .where(ScheduleUserDao.Properties.Scheduleid.eq(scheduleId)).unique();
        return scheduleUser;
    }

    /**
     * 增加NoticeUser数据
     */
    public void addNoticeUserData(NoticeUser user) {
        getNoticeUserDao().insertOrReplace(user);
    }

    /**
     * 更新NoticeUser数据
     */
    public void updateNoticeUserData(NoticeUser user) {
        getNoticeUserDao().update(user);
    }

    /**
     * 查询公司公告或服务通知list
     *
     * @return
     */
    public List<NoticeUser> findNoticeUser(int type) {
        return getNoticeUserDao().queryBuilder()
                .where(NoticeUserDao.Properties.Message_type.eq(type),
                        NoticeUserDao.Properties.Deleted.eq("n")).orderDesc(NoticeUserDao.Properties.Id).list();
    }

    /**
     * 根据time查询任务
     *
     * @return
     */
    public List<TaskBean> findTaskByTime(String time) {
        return getTaskBeanDao().queryBuilder()
                .where(TaskBeanDao.Properties.State.notEq(TASK_STATE_DEL), TaskBeanDao.Properties.StartTime.le(time.split(" ")[0] + " 23:59"), TaskBeanDao.Properties.EndTime.ge(time)).orderAsc(TaskBeanDao.Properties.StartTime).list();
    }

    /**
     * 查询公司公告或服务通知list 小于noticeid的十条数据  loadMore 上拉加载更多 loadMoreOrRefresh=true
     * 查询公司公告或服务通知list 大于noticeid的十条数据   Refresh   下拉加载更多 loadMoreOrRefresh=false
     *
     * @return
     */
    public List<NoticeUser> findNoticeUserbyNoticeidBeforeOrAfter(int type, Long id, Boolean loadMoreOrRefresh) {
        List<NoticeUser> list = new ArrayList<NoticeUser>();
        if (loadMoreOrRefresh) {
            list = getNoticeUserDao().queryBuilder()
                    .where(NoticeUserDao.Properties.Message_type.eq(type),
                            NoticeUserDao.Properties.Deleted.eq("n"), NoticeUserDao.Properties.Id.lt(id)).orderDesc(NoticeUserDao.Properties.Id).list();
        } else {
            list = getNoticeUserDao().queryBuilder()
                    .where(NoticeUserDao.Properties.Message_type.eq(type),
                            NoticeUserDao.Properties.Deleted.eq("n"), NoticeUserDao.Properties.Id.gt(id)).orderDesc(NoticeUserDao.Properties.Id).list();
        }
        List<NoticeUser> listResult = new ArrayList<NoticeUser>();
        if (list != null && list.size() > 0) {
            if (list.size() > 10) {
                listResult = list.subList(0, 10);
            } else {
                listResult.addAll(list);
            }
        }
        return listResult;
    }

    /**
     * 查询公司公告或服务通知的未读数量
     *
     * @return
     */
    public Long findNoticeUserCountWithType(int type) {
        return getNoticeUserDao().queryBuilder()
                .where(NoticeUserDao.Properties.Message_type.eq(type),
                        NoticeUserDao.Properties.Deleted.eq("n"), NoticeUserDao.Properties.Readed.eq("no")).count();
    }

    /**
     * 查询公司公告和服务通知的一共未读数量
     *
     * @return
     */
    public Long unReadNotice() {
        return getNoticeUserDao().queryBuilder()
                .where(NoticeUserDao.Properties.Deleted.eq("n"), NoticeUserDao.Properties.Readed.eq("no")).count();
    }

    /**
     * 分页加载
     *
     * @param pageSize 当前第几页(程序中动态修改pageSize的值即可)
     * @param pageNum  每页显示多少个
     * @return
     */
    public List<NoticeUser> findNoticeUserWithPage(int type, int pageSize, int pageNum) {
        return getNoticeUserDao().queryBuilder()
                .where(NoticeUserDao.Properties.Message_type.eq(type),
                        NoticeUserDao.Properties.Deleted.eq("n")).orderAsc(NoticeUserDao.Properties.Publish_time)
                .offset(pageSize * pageNum).limit(pageNum).list();
    }

    /**
     * 根据noticeid查找NewsUser数据
     */
    public NoticeUser findNoticeUserDataByNoticeid(int noticeid) {
        NoticeUser noticeUser = getNoticeUserDao().queryBuilder()
                .where(NoticeUserDao.Properties.Noticeid.eq(noticeid), NoticeUserDao.Properties.Deleted.eq("n")).unique();
        return noticeUser;
    }

    /**
     * 查找所有未读NoticeUser列表数据
     */
    public List<NoticeUser> findNoticeUserDataByReaded() {
        return getNoticeUserDao().queryBuilder()
                .where(NoticeUserDao.Properties.Readed.eq("no"), NoticeUserDao.Properties.Deleted.eq("n")).orderDesc(NoticeUserDao.Properties.Insertiontime).list();
    }

    /**
     * 根据noticeid设为已读
     */
    public void updateNoticeUserDataReaded(int noticeid) {
        List<NoticeUser> noticeUsers = getNoticeUserDao().queryBuilder()
                .where(NoticeUserDao.Properties.Noticeid.eq(noticeid), NoticeUserDao.Properties.Deleted.eq("n")).list();
        for (int i = 0; i < noticeUsers.size(); i++) {
            noticeUsers.get(i).setReaded("yes");
            getNoticeUserDao().update(noticeUsers.get(i));
        }
    }

    public void deleteNoticeById(int noticeid) {
        NoticeUser noticeUser = getNoticeUserDao().queryBuilder()
                .where(NoticeUserDao.Properties.Noticeid.eq(noticeid), NoticeUserDao.Properties.Deleted.eq("n")).unique();
        if (noticeUser != null) {
            noticeUser.setDeleted("y");
            getNoticeUserDao().update(noticeUser);
        }
    }

    /**
     * 增加未接电话记录
     */
    public void addCallRecordData(MissedCallRecordUser user) {
        MissedCallRecordUser missedCallRecordUser = getMissedCallRecordUserDao().queryBuilder()
                .where(MissedCallRecordUserDao.Properties.Employee.eq(user.getEmployee()),
                        MissedCallRecordUserDao.Properties.Readed.eq("no")).unique();
        if (missedCallRecordUser != null) {
            missedCallRecordUser.setCount(missedCallRecordUser.getCount()+1);
            missedCallRecordUser.setInsertiontime(user.getInsertiontime());
            getMissedCallRecordUserDao().update(missedCallRecordUser);
        } else {
            getMissedCallRecordUserDao().insertOrReplace(user);
        }
    }

    /**
     * 根据id设为
     */
    public void updateCallRecordDataById(String employee) {
        List<MissedCallRecordUser> list = getMissedCallRecordUserDao().queryBuilder()
                .where(MissedCallRecordUserDao.Properties.Employee.eq(employee),
                        MissedCallRecordUserDao.Properties.Readed.eq("no")).list();
        for (int i = 0; i < list.size(); i++) {
            //list.get(i).setReaded("yes");
            //getMissedCallRecordUserDao().update(list.get(i));
            getMissedCallRecordUserDao().delete(list.get(i));
        }
    }

    /**
     * MissedCallRecordUser
     */
    public List<MissedCallRecordUser> findCallRecordDataByReaded() {
        return getMissedCallRecordUserDao().queryBuilder()
                .where(MissedCallRecordUserDao.Properties.Readed.eq("no")).orderDesc(MissedCallRecordUserDao.Properties.Insertiontime).list();
    }

    /**
     * 将所有MissedCallRecordUser设为已读
     */
    public void updateCallRecordDataReaded() {
        List<MissedCallRecordUser> list = getMissedCallRecordUserDao().queryBuilder()
                .where(MissedCallRecordUserDao.Properties.Readed.eq("no")).list();
        for (int i = 0; i < list.size(); i++) {
            getMissedCallRecordUserDao().delete(list.get(i));
        }
    }

    /**
     * 获取 getRecentlyPlayedUserDao
     */
    public RecentlyPlayedUserDao getRecentlyPlayedUserDao() {
        return mDaoSession.getRecentlyPlayedUserDao();
    }

    /**
     * 增加资源库
     */
    public List<RecentlyPlayedUser> getRecentlyPlayedUserList() {
        return   getRecentlyPlayedUserDao().queryBuilder().orderDesc(RecentlyPlayedUserDao.Properties.Id).list();
    }
    /**
     * 增加资源库最近播放
     */
    public void addRecentlyPlayedUser(RecentlyPlayedUser user) {
        RecentlyPlayedUser recentlyPlayedUser= getRecentlyPlayedUserDao().queryBuilder().
                where(RecentlyPlayedUserDao.Properties.Uid.eq(user.getUid())).unique();
        if (recentlyPlayedUser!=null){
            getRecentlyPlayedUserDao().delete(recentlyPlayedUser);
        }
        long count=getRecentlyPlayedUserDao().queryBuilder().count();
        if (count>49){
            getRecentlyPlayedUserDao().delete(getRecentlyPlayedUserDao().queryBuilder().limit(1).unique());
        }
        getRecentlyPlayedUserDao().insert(user);
    }
}

package com.lege.android.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lege.android.base.log.APPLog;
import com.lege.android.base.constants.BindConstant;
import com.lege.android.base.constants.SettingConstant;

/**
 * Created by zhoushaoqing on 18-10-17.
 */

public class PreferencesManager{
    private static PreferencesManager INSTANCE = null;
    private Context mContext = null;

    public static void init(Context context){
        INSTANCE = new PreferencesManager(context);
    }
    private PreferencesManager(){
    }

    public PreferencesManager(Context mContext) {
        this.mContext = mContext;
    }

    public static PreferencesManager getInstance(){
        return INSTANCE;
    }
    public static PreferencesManager getInstance(Context context){
        return new PreferencesManager(context);
    }

    public boolean contains(String key){
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return mPreferences.contains(key);
    }
    public void removeKey(String key){
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        if(mPreferences.contains(key)){
            SharedPreferences.Editor mEditor = mPreferences.edit();
            mEditor.remove(key);
            mEditor.commit();
        }
    }
    public void clearAllData(){
//        boolean isFirst = PreferencesManager.getInstance(MyAPP.getAppContext()).getBooleanResults("isFirst");
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEditor = mPreferences.edit();
        mEditor.clear();
        mEditor.commit();
//        PreferencesManager.getInstance(MyAPP.getAppContext()).saveBooleanResults("isFirst",isFirst);
    }
    public void saveStringResults(String key,String value) {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEditor = mPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String getStringResults(String key){
        SharedPreferences mPreferences =PreferenceManager.getDefaultSharedPreferences(mContext);
        String vaule =mPreferences.getString(key, null);
        return vaule;
    }
    public String getStringResults(String key,String defaultValue){
        SharedPreferences mPreferences =PreferenceManager.getDefaultSharedPreferences(mContext);
        String vaule =mPreferences.getString(key, defaultValue);
        return vaule;
    }
    public String getStringResultsNotNull(String key){
        SharedPreferences mPreferences =PreferenceManager.getDefaultSharedPreferences(mContext);
        String vaule =mPreferences.getString(key, "");
        return vaule;
    }
    public void saveIntegerResults(String key,Integer value) {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEditor = mPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public Integer getIntegerResults(String key){
        SharedPreferences mPreferences =PreferenceManager.getDefaultSharedPreferences(mContext);
        Integer vaule =mPreferences.getInt(key, 0);
        return vaule;
    }
    public Integer getIntegerResults(String key,int defaultValue){
        SharedPreferences mPreferences =PreferenceManager.getDefaultSharedPreferences(mContext);
        Integer vaule =mPreferences.getInt(key, defaultValue);
        return vaule;
    }
    public void saveBooleanResults(String key,boolean value) {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEditor = mPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public boolean getBooleanResults(String key){
        SharedPreferences mPreferences =PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean vaule =mPreferences.getBoolean(key, true);
        return vaule;
    }
    public boolean getBooleanResults2(String key,boolean defaultValue){
        SharedPreferences mPreferences =PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean vaule =mPreferences.getBoolean(key, defaultValue);
        return vaule;
    }
    public boolean getBooleanBinderResults(String key){
        SharedPreferences mPreferences =PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean vaule =mPreferences.getBoolean(key, false);
        return vaule;
    }
    public boolean getIsZhuanzhuMode(String key){
        SharedPreferences mPreferences =PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean vaule =mPreferences.getBoolean(key, false);
        return vaule;
    }

    public boolean getIsBindScreen(){
        SharedPreferences mPreferences =PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean vaule =mPreferences.getBoolean(BindConstant.BIND_SCREEN, false);
        return vaule;
    }

    public boolean getLockSoundEnable(){
        SharedPreferences mPreferences =PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean vaule =mPreferences.getBoolean(SettingConstant.LOCK_SOUND, true);
        return vaule;
    }
    public boolean isRegistHuanXinAccount(){
        return getBooleanBinderResults("huanxin_regist");
    }
    public String getAccessToken(){
        return getStringResults("access_token");
    }

    public String getRefreshToken(){
        return getStringResults("refresh_token");
    }

    public String getExpires_in(){
        return getStringResults("expires_in");
    }

    public String getCreateTime(){
        return getStringResults("createtime");
    }

    public String getToken(){
        return getStringResultsNotNull(BindConstant.DEVICE_TEMP_TOKEN);
    }
    public boolean isExpired(){
        String expires = getExpires_in();
        String createTime = getCreateTime();
        if (createTime == null|| expires==null){
            APPLog.log("isExpired===11111111111111");
            return false;
        }else {
            long expireTimeValue = Long.parseLong(expires);
            long createTimeValue = Long.parseLong(createTime);
            long current = System.currentTimeMillis();
            long time = createTimeValue + expireTimeValue;
            APPLog.log("isExpired===createTimeValue=="+createTimeValue+"==current==="+current+"==time=="+time);

            if (time != 0 && time < current) {
                //过期了要刷新
                APPLog.log("isExpired===333333333333");
                return  true;
            }
            return  false;
        }
    }

}

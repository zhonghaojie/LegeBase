package com.lege.android.base.util;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.lege.android.base.BaseApp;
import com.lege.android.base.PreferencesManager;
import com.lege.android.base.constants.SettingConstant;

public class TaskSingleInstance {
    private static TaskSingleInstance mTaskSingleInstance = null;
    private static int WHAT_START = 1;
//    private Context mContext;
    private boolean isScreenOn = true;
//    private TaskSingleInstance(Context context) {
//        mContext = context;
//    }

    public static TaskSingleInstance getInstance() {
        if (mTaskSingleInstance == null) {
            synchronized (TaskSingleInstance.class) {
                if (mTaskSingleInstance == null) {
                    mTaskSingleInstance = new TaskSingleInstance();
                }
            }
        }
        return mTaskSingleInstance;

    }

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(WHAT_START == msg.what){
                Intent intent = new Intent();
                if(isScreenOn
                        && !PreferencesManager.getInstance(BaseApp.getAppContext()).getBooleanResults2(SettingConstant.CHILDREN_MODEL, false)){
//                    intent.setClass(mContext, ScreenSaverActivity.class);
                    intent.setAction("com.base.ScreenSaverActivity");
                    BaseApp.getAppContext().startActivity(intent);
                }
                Log.i("DDDDDD","屏保时间到");
                handler.sendEmptyMessageDelayed(WHAT_START,ThemeAndScreenManager.getInstance().getScreenProtectStartTime()*60*1000);
            }
        }
    };


    public void startTimer(){
        if(!PreferencesManager.getInstance().getIsBindScreen()){
            return;
        }
        if(!ThemeAndScreenManager.getInstance().getScreenProtect()){
            return;
        }
        handler.removeMessages(WHAT_START);
        handler.sendEmptyMessageDelayed(WHAT_START,ThemeAndScreenManager.getInstance().getScreenProtectStartTime()*60*1000);
    }

    public void stopTimer(){
        handler.removeMessages(WHAT_START);
    }

    public void setIsScreen(boolean isScreen){
        isScreenOn = isScreen;
    }
}

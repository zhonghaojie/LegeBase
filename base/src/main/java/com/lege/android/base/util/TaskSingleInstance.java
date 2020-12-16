package com.lege.android.base.util;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.lege.android.base.PreferencesManager;
import com.lege.android.base.constants.SettingConstant;

public class TaskSingleInstance {
    private static TaskSingleInstance mTaskSingleInstance = null;
    private static int WHAT_START = 1;
    private Context mContext;
    private boolean isScreenOn = true;
    private TaskSingleInstance(Context context) {
        mContext = context;
    }

    public static TaskSingleInstance getInstance(Context context) {
        if (mTaskSingleInstance == null) {
            synchronized (TaskSingleInstance.class) {
                if (mTaskSingleInstance == null) {
                    mTaskSingleInstance = new TaskSingleInstance(context);
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
                        && !PreferencesManager.getInstance(mContext).getBooleanResults2(SettingConstant.CHILDREN_MODEL, false)){
//                    intent.setClass(mContext, ScreenSaverActivity.class);
                    intent.setAction("com.base.ScreenSaverActivity");
                    mContext.startActivity(intent);
                }
                Log.i("DDDDDD","屏保时间到");
                handler.sendEmptyMessageDelayed(WHAT_START,ThemeAndScreenManager.Companion.get(mContext).getScreenProtectStartTime()*60*1000);
            }
        }
    };


    public void startTimer(){
        if(!PreferencesManager.getInstance().getIsBindScreen()){
            return;
        }
        if(!ThemeAndScreenManager.Companion.get(mContext).getScreenProtect()){
            return;
        }
        Log.i("DDDDDD","开始倒计时");
        handler.removeMessages(WHAT_START);
        handler.sendEmptyMessageDelayed(WHAT_START,ThemeAndScreenManager.Companion.get(mContext).getScreenProtectStartTime()*60*1000);
    }

    public void stopTimer(){
        Log.i("DDDDDD","停止倒计时");
        handler.removeMessages(WHAT_START);
    }

    public void setIsScreen(boolean isScreen){
        isScreenOn = isScreen;
    }
}

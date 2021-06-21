package com.lege.android.base.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.util.Log;

import com.lege.android.base.BaseApp;
import com.lege.android.base.PreferencesManager;
import com.lege.android.base.constants.SettingConstant;
import com.lege.android.base.ui.BaseActivityCollector;

import java.util.ArrayList;
import java.util.List;

public class TaskSingleInstance {
    private static TaskSingleInstance mTaskSingleInstance = null;
    private static int WHAT_START = 1;
    //    private Context mContext;
    private boolean isScreenOn = true;
//    private TaskSingleInstance(Context context) {
//        mContext = context;
//    }

    private List<String> exceptActivity = new ArrayList<>();

    public List<String> getExceptActivity() {
        return exceptActivity;
    }

    public void setExceptActivity(List<String> exceptActivity) {
        this.exceptActivity = exceptActivity;
    }

    public void addExceptPage(String pageName) {
        if (!exceptActivity.contains(pageName)) {
            exceptActivity.add(pageName);
        }
    }

    public void removeExceptPage(String pageName) {
        if (exceptActivity.contains(pageName)) {
            exceptActivity.remove(pageName);
        }
    }

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

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (WHAT_START == msg.what) {
                Intent intent = new Intent();
                if (isNeedToStartScreenSaver() && isScreenOn && !PreferencesManager.getInstance(BaseApp.getAppContext()).getBooleanResults2(SettingConstant.CHILDREN_MODEL, false)) {
//                    intent.setClass(mContext, ScreenSaverActivity.class);
                    intent.setAction("com.base.ScreenSaverActivity");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    BaseApp.getAppContext().startActivity(intent);
                }
                Log.i("DDDDDD", "屏保时间到");
                handler.sendEmptyMessageDelayed(WHAT_START, ThemeAndScreenManager.getInstance().getScreenProtectStartTime() * 60 * 1000);
            }
        }
    };


    private boolean isNeedToStartScreenSaver() {
        String aty;
        for (int i = 0; i < BaseActivityCollector.getInstance().getActivities().size(); i++) {
            aty = BaseActivityCollector.getInstance().getActivities().get(i).getClass().getSimpleName();
            for (int j = 0; j < exceptActivity.size(); j++) {
                if (exceptActivity.get(j).equals(aty)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void startTimer() {
        if (!PreferencesManager.getInstance().getIsBindScreen()) {
            return;
        }
        if (!ThemeAndScreenManager.getInstance().getScreenProtect()) {
            return;
        }
        handler.removeMessages(WHAT_START);
        handler.sendEmptyMessageDelayed(WHAT_START, ThemeAndScreenManager.getInstance().getScreenProtectStartTime() * 60 * 1000);
    }

    public void stopTimer() {
        handler.removeMessages(WHAT_START);
    }

    public void setIsScreen(boolean isScreen) {
        isScreenOn = isScreen;
    }
}

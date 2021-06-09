package com.lege.android.base.ui;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.util.Log;

import com.blankj.utilcode.util.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by loctek on 2020/6/18.
 */
public class BaseActivityCollector implements IActivityCollector {
    private static BaseActivityCollector collector = new BaseActivityCollector();

    public static BaseActivityCollector getInstance() {
        return collector;
    }

    public List<Activity> getActivities(){
        return ActivityUtils.getActivityList();
    }

    @Override
    @Nullable
    public Activity getTop() {
        return ActivityUtils.getTopActivity();
    }

    @Override
    public boolean isActivityExist(Activity activity) {
        return ActivityUtils.isActivityAlive(activity);
    }

    @Override
    public Activity findActivityBySimpleName(String name) {
        List<Activity> list = ActivityUtils.getActivityList();
        Activity aty = null;
        for (int i = 0; i < list.size(); i++) {
            aty = list.get(i);
            if (name.equals(aty.getClass().getSimpleName())) {
                return aty;
            }
        }

        return null;
    }

    @Override
    public void finishActivityExcept(String name) {
        List<Activity> list = ActivityUtils.getActivityList();
        Activity aty = null;
        for (int i = 0; i < list.size(); i++) {
            aty = list.get(i);
            if (!name.equals(aty.getClass().getSimpleName())) {
                ActivityUtils.finishActivity(aty,true);
            }
        }
//        for (int i = activities.size() - 1; i >= 0; i--) {
//            activity = activities.get(i);
//            if (!activity.getClass().getName().equals(name)) {
//                activity.finish();
//                Log.i("生命周期", activity.getClass().getSimpleName() + "   finihs");
//            }
//        }
    }

    @Override
    public void finishActivity(String name) {
        List<Activity> list = ActivityUtils.getActivityList();
        Activity aty = null;
        for (int i = 0; i < list.size(); i++) {
            aty = list.get(i);
            if (name.equals(aty.getClass().getSimpleName())) {
                ActivityUtils.finishActivity(aty,true);
            }
        }
    }
}

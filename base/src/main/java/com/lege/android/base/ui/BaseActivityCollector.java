package com.lege.android.base.ui;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by loctek on 2020/6/18.
 */
public class BaseActivityCollector implements IActivityCollector{
    private static  BaseActivityCollector collector = new BaseActivityCollector();
    public static BaseActivityCollector getInstance(){
        return collector;
    }
    protected List<Activity> activities = new ArrayList<>();

    public List<Activity> getActivities() {
        return activities;
    }
    @Override
    public void addActivity(Activity activity) {
        Log.i("BaseActivityCollector", "addActivity==" + activity.getClass().getName());
        activities.add(activity);
    }
    @Override
    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }
    @Override
    public Activity getTop() {
        return activities.get(activities.size() - 1);
    }
    @Override
    public boolean isActivityExist(Activity activity) {
        boolean res;
        if (activity == null) {
            res = false;
        } else {
            if (activity.isFinishing() || activity.isDestroyed()) {
                res = false;
            } else {
                res = true;
            }
        }
        return res;
    }
    @Override
    public Activity findActivityBySimpleName(String name) {
        for (Activity activity : activities) {
            if (activity.getClass().getSimpleName().equals(name)) {
                return activity;
            }
        }
        return null;
    }
    @Override
    public void finishActivityExcept(String name) {
        Activity activity = null;
        for (int i = activities.size() - 1; i >= 0; i--) {
            activity = activities.get(i);
            if (!activity.getClass().getName().equals(name)) {
                activity.finish();
                Log.i("生命周期",activity.getClass().getSimpleName()+"   finihs");
            }
        }
    }

    @Override
    public void finishActivity(String name) {
        Activity activity = null;
        for (int i = activities.size() - 1; i >= 0; i--) {
            activity = activities.get(i);
            if (activity.getClass().getName().equals(name)) {
                activity.finish();
                Log.i("生命周期",activity.getClass().getSimpleName()+"   finihs");
            }
        }
    }
}

package com.lege.android.base.ui

import android.app.Activity

/**
 * Description:
 * Created by loctek on 2020/11/3.
 */
interface IActivityCollector {
    fun addActivity(activity: Activity)
    fun removeActivity(activity: Activity)
    fun isActivityExist(activity: Activity):Boolean
    fun getTop():Activity
    fun findActivityBySimpleName(name:String):Activity
    fun finishActivityExcept(name:String)
    fun finishActivity(name:String)
}
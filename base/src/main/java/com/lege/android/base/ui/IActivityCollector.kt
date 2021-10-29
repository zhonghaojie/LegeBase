package com.lege.android.base.ui

import android.app.Activity
import androidx.annotation.Nullable

/**
 * Description:
 * Created by loctek on 2020/11/3.
 */
interface IActivityCollector {
    fun isActivityExist(activity: Activity):Boolean
    @Nullable fun getTop():Activity
    fun findActivityBySimpleName(name:String):Activity
    fun finishActivityExcept(name:String)
    fun finishActivity(name:String)
}
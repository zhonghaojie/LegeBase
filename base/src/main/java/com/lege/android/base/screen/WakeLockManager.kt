package com.lege.android.base.screen

import android.content.Context
import android.os.PowerManager
import android.os.PowerManager.WakeLock

/**
 * Description:
 * Created by loctek on 2020/7/21.
 */
object WakeLockManager {
    private const val commonTag = "commonWake"
    private var mWakeLock: WakeLock? = null
    fun acquireWakeLock(context:Context) {
        if (mWakeLock == null) {
            val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            mWakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.SCREEN_DIM_WAKE_LOCK, commonTag)
            mWakeLock?.acquire()
        }
    }
    fun releaseWakeLock() {
        if (mWakeLock != null) {
            mWakeLock!!.release()
            mWakeLock = null
        }
    }
}
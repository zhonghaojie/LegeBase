package com.lege.android.base.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.MotionEvent
import android.view.View
import com.hjq.toast.ToastUtils
import com.lege.android.base.PermissionConstant
import com.lege.android.base.R
import com.lege.android.base.log.APPLog
import com.lege.android.base.screen.WakeLockManager
import com.lege.android.base.util.TaskSingleInstance
import com.lege.android.base.util.ThemeAndScreenManager
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.Utils
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper
import me.yokeyword.fragmentation.SupportActivity


/**
 * Description:
 * Created by loctek on 2020/6/18.
 */
open class BaseActivity : SupportActivity(), SwipeBackActivityBase {
    companion object{
        const val BASE_ACTIVITY_TAG = "生命周期"
    }

    private var mHelper: SwipeBackActivityHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mActionBar = supportActionBar
        mActionBar?.hide()
        val decorView = window.decorView
        var systemUiVisibility = decorView.systemUiVisibility
        val flags = (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                //                 | View.SYSTEM_UI_FLAG_FULLSCREEN       // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        systemUiVisibility = systemUiVisibility or flags
        decorView.systemUiVisibility = systemUiVisibility
        window.statusBarColor = Color.TRANSPARENT
        APPLog.log(BASE_ACTIVITY_TAG, javaClass.simpleName + "   " + "onCreate")
        mHelper = SwipeBackActivityHelper(this)
        mHelper?.onActivityCreate()
        //打开任何界面都判定为人再跟前，重置检测间隔
        //打开任何界面都判定为人再跟前，重置检测间隔
        if (needStartProtectCounter()) {
            TaskSingleInstance.getInstance().startTimer()
        }
    }
    override fun getSwipeBackLayout(): SwipeBackLayout {
        return mHelper!!.swipeBackLayout
    }

    override fun setSwipeBackEnable(enable: Boolean) {
        swipeBackLayout!!.setEnableGesture(enable)
    }

    override fun scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this)
        swipeBackLayout!!.scrollToFinishActivity()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mHelper?.onPostCreate()
    }

    override fun onResume() {
        super.onResume()
        APPLog.log(BASE_ACTIVITY_TAG, javaClass.simpleName + "   " + "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        APPLog.log(BASE_ACTIVITY_TAG, javaClass.simpleName + "   " + "onRestart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        APPLog.log(BASE_ACTIVITY_TAG, javaClass.simpleName + "   " + "onNewIntent")
    }

    override fun onPause() {
        super.onPause()
        APPLog.log(BASE_ACTIVITY_TAG, javaClass.simpleName + "   " + "onPause")
    }

    override fun onStart() {
        super.onStart()
        APPLog.log(BASE_ACTIVITY_TAG, javaClass.simpleName + "   " + "onStart")
    }

    override fun onStop() {
        super.onStop()
        APPLog.log(BASE_ACTIVITY_TAG, javaClass.simpleName + "   " + "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        APPLog.log(BASE_ACTIVITY_TAG, javaClass.simpleName + "   " + "onDestroy")
    }


    /**
     * 申请指定的权限.
     */
    open fun requestPermission(code: Int, vararg permissions: String?) {
        ActivityCompat.requestPermissions(this, permissions, code)
    }

    /**
     * 判断是否有指定的权限
     */
    open fun hasPermission(vararg permissions: String?): Boolean {
        for (permisson in permissions) {
            if (ContextCompat.checkSelfPermission(this, permisson!!)
                    != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionConstant.PERMISSTION_WRITE_READ_EXTERNAL_CODE -> if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                doSDCardPermission()
            }
            PermissionConstant.PERMISSTION_AUDIO_RECORD_CODE -> if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                doAudioRecordPermission()
            }
            PermissionConstant.PERMISSTION_CAMERA_CODE -> if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                doCameraPermission()
            }
            PermissionConstant.PERMISSTION_LOCATION_CODE -> if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                doLocationPermission()
            }
            PermissionConstant.PERMISSTION_PHONE_STATE_CODE -> if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                doPhoneStatePermission()
            }
            PermissionConstant.PERMISSTION_BLUETOOTH_CODE -> {
                var times = 0
                if (grantResults.size == 4) {
                    for (tempRes in grantResults) {
                        if (tempRes != PackageManager.PERMISSION_GRANTED) {
                            times = -1
                            break
                        }
                    }
                }
                if (times == 0) {
                    doBluetoothPermission()
                }
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ThemeAndScreenManager.instance.screenProtect) {
            if (ev.action == MotionEvent.ACTION_UP) {
                if (needStartProtectCounter()) {
                    TaskSingleInstance.getInstance().startTimer()
                }
            }
        } else {
            TaskSingleInstance.getInstance().stopTimer()
        }
        return super.dispatchTouchEvent(ev)
    }

    protected open fun needToAvoidQuicklyClick(): Boolean {
        return true
    }

    protected open fun needStartProtectCounter(): Boolean {
        return true
    }

    /**
     * 处理整个应用用中的SDCard业务
     */
    open fun doSDCardPermission() {}

    /**
     * 处理整个应用用中的AudioRecord业务
     */
    open fun doAudioRecordPermission() {}

    /**
     * 处理整个应用用中的Location业务
     */
    open fun doLocationPermission() {}

    /**
     * 处理整个应用用中的Camera业务
     */
    open fun doCameraPermission() {
        ToastUtils.show(getString(R.string.str_camera_permission_granted))
    }

    /**
     * 处理整个应用用中的PhoneState业务
     */
    open fun doPhoneStatePermission() {}

    /**
     * 处理整个应用中的蓝牙设备控制业务
     */
    open fun doBluetoothPermission() {}

    protected var currentFrg: Fragment? = null

    protected open fun showFragment(id: Int, fragment: Fragment?) {
        if (fragment == null) {
            return
        }
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        if (currentFrg !== fragment) {
            if (currentFrg != null) {
                ft.hide(currentFrg)
            }
            if (fragment.isAdded) {
                ft.show(fragment).commit()
            } else {
                ft.add(id, fragment).show(fragment).commit()
            }
            currentFrg = fragment
        }
    }

    protected open fun acquireWakeLock() {
        WakeLockManager.acquireWakeLock(this)
    }

    protected open fun releaseWakeLock() {
        WakeLockManager.releaseWakeLock()
    }
}
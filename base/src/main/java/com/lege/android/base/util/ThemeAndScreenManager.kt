package com.lege.android.base.util


import android.content.Context
import com.lege.android.base.BaseApp
import com.lege.android.base.PreferencesManager
import com.lege.android.base.constants.SettingConstant
import com.lege.android.base.data.EventUpdateControlStyle
import org.greenrobot.eventbus.EventBus

/**
 * Description:主题和屏保管理类
 * Created by loctek on 2020/7/9.
 */
class ThemeAndScreenManager() {
    companion object {
        @JvmStatic
        val instance = ThemeAndScreenManager()

        //选择智控主题的风格
        const val THEME_DEVICE_CONTROL = 0
        const val THEME_CONTENT = 1

        const val SCREEN_PROTECT_STYLE_PICTURE = 0//图库相册
        const val SCREEN_PROTECT_STYLE_CONTENT = 1//内容资讯
        const val SCREEN_PROTECT_STYLE_E_CLOCK = 2//电子时钟


        //智控主题的风格id
        const val DEVICE_CONTROL_STYLE_ID_MAN = "device_control_style_man"
        const val DEVICE_CONTROL_STYLE_ID_WOMAN = "device_control_style_woman"
        const val DEVICE_CONTROL_STYLE_ID_CHILD = "device_control_style_child"
        const val DEVICE_CONTROL_STYLE_SIT_REMIND = "device_control_style_sit_remind"
        const val DEVICE_CONTROL_STYLE_SIT_REMIND_WOMAN = "device_control_style_sit_remind_woman"
        const val DEVICE_CONTROL_STYLE_ID_OTHER = "other"

        //儿童模式智控风格
        const val CHILD_DEVICE_CONTROL_STYLE_BOY = "boy"
        const val CHILD_DEVICE_CONTROL_STYLE_GIRL = "girl"

        //电子时钟
        const val SCREEN_CLOCK_CARTOON = "screen_clock_cartoon"
        const val SCREEN_CLOCK_FLIP = "screen_clock_flip"
        const val SCREEN_CLOCK_DIAL = "screen_clock_dial"
    }

    private val pm = PreferencesManager.getInstance()
    var theme: Int
        //主题  0智控   1内容
        get() {
            return pm.getIntegerResults("theme", THEME_CONTENT)
        }
        set(value) {
            pm.saveIntegerResults("theme", value)
        }

    //主题风格id
    var themeStyleID: String
        get() {
            return pm.getStringResults("themeStyleID", DEVICE_CONTROL_STYLE_ID_MAN)
        }
        set(value) {
            pm.saveStringResults("themeStyleID", value)
            EventBus.getDefault().postSticky(EventUpdateControlStyle())
        }
    //儿童模式智控风格
    var childStyle:String
        get() {
            return pm.getStringResults("childStyle", CHILD_DEVICE_CONTROL_STYLE_BOY)
        }
        set(value) {
            pm.saveStringResults("childStyle", value)
        }

    //首页内容主题时，内容的切换间隔
    var homeContentChangeTime: Int
        get() {
            return pm.getIntegerResults("homeContentChangeTime", 10)
        }
        set(value) {
            pm.saveIntegerResults("homeContentChangeTime", value)
        }

    //屏保开关
    var screenProtect: Boolean
        get() {
            return pm.getBooleanResults2("screenProtectSwitch", true)
        }
        set(value) {
            pm.saveBooleanResults("screenProtectSwitch", value)
        }

    //屏保为内容资讯时，内容的切换间隔
    var screenSaverContentChangeTime: Int
        get() {
            return pm.getIntegerResults("screenSaverContentChangeTime", 1)
        }
        set(value) {
            pm.saveIntegerResults("screenSaverContentChangeTime", value)
        }

    //整点报时开关
    var integerTimeProtect: Boolean
        get() {
            return pm.getBooleanResults2("integerTimeProtectSwitch", false)
        }
        set(value) {
            pm.saveBooleanResults("integerTimeProtectSwitch", value)
        }

    //时钟样式id
    var themeClockID: String
        get() {
            return pm.getStringResults("themeClockID", SCREEN_CLOCK_FLIP)
        }
        set(value) {
            pm.saveStringResults("themeClockID", value)
        }

    //屏保样式
    var screenProtectSelected: Int
        get() {
            return pm.getIntegerResults("screenProtect", SCREEN_PROTECT_STYLE_E_CLOCK)
        }
        set(value) {
            pm.saveIntegerResults("screenProtect", value)
        }

    //进入屏保时间 分钟
    var screenProtectStartTime: Int
        get() {
            return pm.getIntegerResults("screenProtectStartTime", 5)
        }
        set(value) {
            pm.saveIntegerResults("screenProtectStartTime", value)
        }
}
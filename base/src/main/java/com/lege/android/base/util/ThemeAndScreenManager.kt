package com.lege.android.base.util


import com.lege.android.base.PreferencesManager

/**
 * Description:主题和屏保管理类
 * Created by loctek on 2020/7/9.
 */
open class ThemeAndScreenManager() {
    companion object {
        private val pm = PreferencesManager.getInstance()

        @JvmStatic
        val instance = ThemeAndScreenManager()

        //region 智控主题子主题
        const val THEME_DEVICE_CONTROL_SIMPLE = 0 // 极简智控
        const val THEME_DEVICE_CONTROL_MAN = 1 // 智控男
        const val THEME_DEVICE_CONTROL_WOMAN = 2//智控女
        const val THEME_DEVICE_CONTROL_TEA_TABLE = 4//茶几
        const val THEME_DEVICE_CONTROL_BEDSIDE_CUPBOARD = 5//床头柜
        //endregion

        //region 主题大类
        const val THEME_CONTENT_NEWS = 3//内容资讯
        const val THEME_DEVICE_CONTROL = 4 //智控主题
        //endregion


        const val CHILDREN_DEVICE_CONTROL_THEME_BOY = 1
        const val CHILDREN_DEVICE_CONTROL_THEME_GIRL = 2

        const val SP_NO_SCREEN_PROTECT = -1 //无
        const val SP_CARTOON_CLOCK = 1 //卡通时钟
        const val SP_ELECTRIC_CLOCK = 2//电子时钟
        const val SP_FLIP_CLOCK = 3 // 翻页时钟
        const val SP_ALBUM = 4//家庭相册


        //儿童模式智控风格
        const val CHILD_DEVICE_CONTROL_STYLE_BOY = "boy"
        const val CHILD_DEVICE_CONTROL_STYLE_GIRL = "girl"

        //电子时钟
        const val SCREEN_CLOCK_CARTOON = "screen_clock_cartoon"
        const val SCREEN_CLOCK_FLIP = "screen_clock_flip"
        const val SCREEN_CLOCK_DIAL = "screen_clock_dial"
    }

    init {
        fixTheme()
    }

    //首页主题
    var mTheme: Int
        get() {
            return pm.getIntegerResults("home_theme", THEME_DEVICE_CONTROL)
        }
        set(value) {
            pm.saveIntegerResults("home_theme", value)
        }

    //智控主题
    var deviceControlTheme :Int
        get() {
            return pm.getIntegerResults("deviceControlTheme", THEME_DEVICE_CONTROL_SIMPLE)
        }
        set(value) {
            pm.saveIntegerResults("deviceControlTheme", value)
        }

    //废弃theme字段，改用home_theme
    fun fixTheme() {
        if (pm.getIntegerResults("theme", 1) == -1) {
            return
        }
        if (pm.getIntegerResults("theme", 1) == 1) {
            mTheme = THEME_CONTENT_NEWS
            pm.saveIntegerResults("theme", -1)
        } else if (pm.getIntegerResults("theme", 1) == 0) {
            mTheme = THEME_DEVICE_CONTROL
            if (pm.getStringResults("themeStyleID", "device_control_style_simple") == "device_control_style_simple") {
                deviceControlTheme = THEME_DEVICE_CONTROL_SIMPLE
                pm.saveIntegerResults("theme", -1)
            } else if (pm.getStringResults("themeStyleID", "device_control_style_simple") == "device_control_style_woman") {
                deviceControlTheme = THEME_DEVICE_CONTROL_WOMAN
                pm.saveIntegerResults("theme", -1)
            } else if (pm.getStringResults("themeStyleID", "device_control_style_simple") == "device_control_style_man") {
                deviceControlTheme = THEME_DEVICE_CONTROL_MAN
                pm.saveIntegerResults("theme", -1)
            }
        }
    }


    //儿童模式智控风格
    var childStyle: Int
        get() {
            val oldValue = pm.getStringResults("childStyle", CHILD_DEVICE_CONTROL_STYLE_BOY)
            if (oldValue == CHILD_DEVICE_CONTROL_STYLE_BOY) {
                return CHILDREN_DEVICE_CONTROL_THEME_BOY
            } else {
                return CHILDREN_DEVICE_CONTROL_THEME_GIRL
            }

        }
        set(value) {
            if (value == CHILDREN_DEVICE_CONTROL_THEME_BOY) {
                pm.saveStringResults("childStyle", CHILD_DEVICE_CONTROL_STYLE_BOY)
            } else {
                pm.saveStringResults("childStyle", CHILD_DEVICE_CONTROL_STYLE_GIRL)
            }

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
            return pm.getIntegerResults("screenProtect", SP_ELECTRIC_CLOCK)
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
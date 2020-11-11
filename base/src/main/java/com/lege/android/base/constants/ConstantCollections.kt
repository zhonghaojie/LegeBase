package com.lege.android.base.constants

import com.lege.android.base.PreferencesManager
import com.lege.android.base.string.DateFormatUtil

/**
 * Description:
 * Created by zhonghaojie on 2020-04-10.
 */


class TomatoConstants {
    companion object {
        //选择的背景音乐
        const val FOCUS_MUSIC_SELECTED = "focus_music_selected"
        const val IS_AUTO_NEXT = "is_auto_next"
        const val RELAX_TIME = "relax_time"
        const val TOMATO_TIME = "tomato_time"

        fun getBGM():String{
            return PreferencesManager.getInstance().getStringResults(FOCUS_MUSIC_SELECTED,"")
        }
        fun setBGM(json:String){
            PreferencesManager.getInstance().saveStringResults(FOCUS_MUSIC_SELECTED,json)
        }
        fun setTomatoTime(time:Int){
            PreferencesManager.getInstance().saveIntegerResults(TOMATO_TIME,time)
        }
        fun setTomatoRelaxTime(time:Int){
            PreferencesManager.getInstance().saveIntegerResults(RELAX_TIME,time)
        }
        fun getTomatoRelaxTime(): Int {
            return PreferencesManager.getInstance().getIntegerResults(RELAX_TIME, 5 * 60 * 1000)
        }

        fun getTomatoTime(): Int {
            return PreferencesManager.getInstance().getIntegerResults(TOMATO_TIME, 25 * 60 * 1000)
        }

        fun getTomatoRelaxTimeShow(): String {
            val time = PreferencesManager.getInstance().getIntegerResults(RELAX_TIME, 5 * 60 * 1000)
            return "${time / 1000 / 60}分钟"
        }

        fun getTomatoTimeShow(): String {
            val time = PreferencesManager.getInstance().getIntegerResults(TOMATO_TIME, 25 * 60 * 1000)
            return "${time / 1000 / 60}分钟"
        }
    }
}

/**
 * 权限相关
 */
class PermissionConstant {
    companion object {
        const val PERMISSION_ALL = 0
        const val PERMISSTION_WRITE_READ_EXTERNAL_CODE = 1
        const val PERMISSTION_LOCATION_CODE = 2
        const val PERMISSTION_PHONE_STATE_CODE = 3
        const val PERMISSTION_AUDIO_RECORD_CODE = 4
        const val PERMISSTION_CAMERA_CODE = 5
        const val PERMISSTION_BLUETOOTH_CODE = 6
    }
}

/**
 * 广播的常量
 */
class ReceiverActionConstant {

    companion object {
        //提醒
        const val ACTION_REMINDER_ALARM = "com.lege.reminderalarm"

        //闹钟
        const val ACTION_ALARM_ALARM = "com.lege.callalarm"

        //绑定
        const val ACTION_BIND_STATUS = "com.lege.launcher.bindstatus"

        //通勤
        const val ACTION_COMMUTE_ALARM = "com.lege.commutealarm"

        //日程
        const val ACTION_SCHEDULE_ALARM = "com.lege.schedulealarm"

        //显示状态栏的广播
        const val SHOW_STATUS_BAR = "show.lege.statusbar"

        //当点击查看未读信息的时候，发送广播通知已读信息列表
        const val UPDATE_READED_MESSAGE_ACTION = "update.readed.message.action"

        //发送广播更新未读信息列表
        const val UPDATE_UNREAD_MESSAGE_ACTION = "update.unreaded.message.action"

        //发送广播更新未读任务列表
        const val UPDATE_UNREAD_TASK_ACTION = "update.unreaded.task.action"

        //新版本推送的广播
        const val INTENT_VERSION_UPDATE = "com.lege.launcher.new.version"

        //静默检测开始、结束检测的广播
        const val INTENT_STATIC_DETECT_IS_RELEASE = "com.lege.launcher.static.detect.release"

        //部门人员界面人员收藏改变之后发送广播更新常用联系人和通话记录
        const val UPDATE_CONTACTS_ACTION = "update.contacts.action"

        //组织排序按钮显示
        const val SHOW_REFRESH_SORTVIEW = "show.refresh.sortview"
    }
}

/**
 * 设置里的常量
 */
class SettingConstant {
    companion object {
        //是否开启静默检测
        const val IS_STATIC_DETECT_OPEN = "IS_STATIC_DETECT_OPEN"

        //是否开启异常提醒
        const val IS_UNUSUAL_REMIND_OPEN = "IS_UNUSUAL_REMIND_OPEN"

        //勿扰模式的白名单
        const val WHITE_LIST = "white_list"

        //锁屏声音
        const val LOCK_SOUND = "lockSound"

        //离开锁定时间
        const val LEAVE_LOCK_TIME = "leavelocktime"

        //壁纸循环时间
        const val WALLPAPER_LOOP_TIME = "wallpaperlooptime"

        //闹钟提示音名称
        const val ALARM_SOUND_NAME = "alarmsoundname"

        //消息提示音名称
        const val MESSAGE_SOUND_NAME = "messagesoundname"

        //习惯提醒提示音名称
        const val REMINDER_SOUND_NAME = "remindersoundname"

        //唤醒
        const val WAKEUP_STATE = "wakeupstate"

        //专注模式
        const val ZHUANZHU_STATE = "zhuanzhustate"

        //静音非静音
        const val IS_MUTE = "volumestate"

        //上一次的音量
        const val LAST_TIME_VOLUME = "last_time_volume"

        //壁纸轮播模式
        const val WALLPAPER_LOOP_MODE = "wallpaper_loop_mode"

        //是否显示通讯录入口
        const val ADDRESS_BOOK_STATUS = "ADDRESS_BOOK_STATUS"

        //音量
        const val VOLUME_PROGRESS = "VOLUME_PROGRESS"

        //
        const val CALL_PROGRESS = "CALL_PROGRESS";

        //新闻推送开关
        const val NEWS_PUSH = "NEWS_PUSH"

        //是否开启视频后台播放
        const val IS_PLAY_BACKGROUND = "is_play_background"

        //后台播放提示界面（第一次要显示，后面不显示）
        const val IS_PLAY_BACKGROUND_FIRST = "is_play_background_first"

        //是否第一次加载联系人数据库
        const val FRIST_LOAD_CONTACT_DB = "fristloadcontactdb"


        //通勤上班播报开关  默认开启
        const val MORNING_TTS = "morning_tts_switch"

        //通勤下班播报开关  默认关闭
        const val AFTERNOON_TTS = "afternoon_tts_switch"

        //是否开启天气自动播报
        const val WEATHER_TTS_IS_ON = "weather_tts_is_on"

        //天气自动播报是否有提醒过用户
        const val WEATHER_TTS_IS_ON_IS_REMIND = "weather_tts_is_on_is_remind"

        //儿童模式
        const val CHILDREN_MODEL = "CHILDREN_MODEL"
        //儿童模式 通讯录界面提醒是否展示
        const val CHILDREN_ADDRESSBOOK_TIP = "CHILDREN_ADDRESSBOOK_TIP"
    }
}

/**
 * 本地数据的Key（SP、本地缓存等）
 */
class LocalDataKeyConstant {
    companion object {
        //每日推送的壁纸
        const val DAILY_WALLPAPER = "DAILY_WALLPAPER"

        //每天推送详情的壁纸
        const val DAILY_DETAIL_WALLPAPER = "DAILY_DETAIL_WALLPAPER"

        //个人信息（用于音视频通话）
        const val MY_EMPLOYEE_INFO = "MY_EMPLOYEE_INFO"

        //通勤数据
        const val COMMUTE = "commute"

        //个人信息
        const val USER_INFO = "USER_INFO"

        const val CITY = "city"

        //文章信息
        const val DAILY_ARTICLE_DETAIL = "DAILY_ARTICLE_DETAIL"
        const val TIPS_DETAIL = "TIPS_DETAIL"

        const val USEAPP = "useapp"

        //相册屏保
        const val FAMILY_ALBUM = "FAMILY_ALBUM"
    }
}

/**
 * 绑定相关的常量
 */
class BindConstant {
    companion object {
        //手机端提供的临时token用于请求服务器然后获取真正的token
        const val PHONE_TEMP_TOKEN = "phonetemptoken"

        //token  用于登入和登出使用
        const val DEVICE_TEMP_TOKEN = "devicetemptoken"

        //是否绑定
        const val BIND_SCREEN = "bindscreen"

        //是否发送极光的registrationid给后台
        const val REGISTRATION_ID = "registrationid"
    }
}

class VersionConstant {
    companion object {
        //OTA推送过来的新版本
        const val NEW_VERSION = "otanewversion"

        //OTA推送过来的新版本的备份
        const val VERSION_DONE = "versiondone"
    }
}

class BleDeviceConstant {
    companion object {
        //是否开启久坐提醒播报
        const val SIT_REMIND_TTS_IS_ON = "sit_remind_tts_is_on"
        //蓝牙设备
        const val ALL = "all"

        //第一版蓝牙桌
        const val DESKBLUENAME = "BDT"

        //第二版蓝牙桌
        const val DESKBLUENAMENEW = "BTD"

        //V10健身车
        const val V10BIKE = "V10U"

        //V9健身车
        const val V9BIKE = "V9U"

        //电动电脑架
        const val MOUNTS = "ETM"

        //桌下椭圆机
        const val UEMS = "BET"

        //媒体墙
        const val MEDIAS = "EMW"

        //走步机
        const val TREADMILLS = "FT01"

        //电视升降架（小秘书没接入，现在屏端按照媒体墙临时接入下）
        const val TVSTAND = "MTS"

        //绑定接口传的type字段
        const val DESK = "desk_bluetooth"
        const val BIKE = "desk_bike"
        const val MOUNT = "mounts"
        const val UEM = "uem"
        const val TREADMILL = "treadmill"
        const val MEDIA = "mediawall"


        const val FROM_VOICE = "voice"
        const val FROM_ACTIVITY = "activity"

        //存储蓝牙桌子信息
        const val DB_KEY_BLE_DEVICE = "ble_device"

        //存储已绑定的蓝牙桌子信息
        const val DB_KEY_BINDED_BLE_DEVICE = "binded_ble_device"

        //存储久坐提醒时间配置
        const val DB_KEY_BLE_DEVICE_DESK_REMIND = "ble_device_desk_remind"

        //存储蓝牙桌子界面主题配置
        const val DB_KEY_BLE_DEVICE_DESK_THEME = "ble_device_desk_theme"

        //如果没有设备连接过，进入智能控制时每天只显示一次搜索设备弹出框
        const val DB_KEY_BLE_NO_DEVICE_LINK_EVERYDAY_REMIND_ONCE = "ble_no_device_link_everyday_remind_once"

        //儿童模式，身高提醒开关
        const val CHILD_DEVICE_CONTROL_GROW_REMIND_SWITCH = "CHILD_DEVICE_CONTROL_GROW_REMIND_SWITCH"

        //儿童模式身高提醒时间
        const val CHILD_DEVICE_CONTROL_GROW_REMIND_TIME = "CHILD_DEVICE_CONTROL_GROW_REMIND_TIME"

        //儿童身高
        const val CHILD_HEIGHT = "child_height"

        //身高设置日期，用于提醒用户修改身高
        const val CHILD_HEIGHT_SET_DATE = "child_height_set_date"

        //蓝牙桌高度变化记录，高度存的是毫米
        const val DESK_HEIGHT_CHANGE_RECORD = "desk_height_change_record"

        /**
         * 刷新身高提醒计时
         * 小孩长个很快，设置了提醒时间后，到时间提醒用户更新坐站高度
         */
        fun refreshChildHeightRemindCount() {
            PreferencesManager.getInstance().saveStringResults(CHILD_HEIGHT_SET_DATE, DateFormatUtil.getCurrentDate())
//            PreferencesManager.getInstance().saveStringResults(BleDeviceConstant.CHILD_HEIGHT_SET_DATE, "2020-03-20")
        }


        /**
         * 广播蓝牙升降桌信息
         */
        const val ACTION_NOTIFY_BLEDEVICEDESK = "com.loctek.action.notify.bledevicedesk"
        const val ACTION_CHANGE_MEMORY_HEIGHT = "com.loctek.action.change.memoryheight"

        /**
         * 通知手动断开蓝牙升降桌
         */
        const val ACTION_NOTIFY_MANUAL_DISCONNECT_BLE_DESK = "com.loctek.action.notify.manualdisconnectbledesk"

        /**
         * 通知绑定的蓝牙升降桌信息已更新
         */
        const val ACTION_NOTIFY_UPDATE_BINDED_BLE_DESK_INTO = "com.loctek.action.notify.updatebindedbledeskinfo"
    }
}

/**
 * 新功能引导是否看过
 */
class GuideIsViewConstant {

    companion object {
        //手势引导
        const val GESTURE_GUIDANCE = "GESTURE_GUIDANCE"

        //股票引导
        const val STOCK_HELP = "stock_help"
    }
}

/**
 * 空气消毒机状态
 */
class AirConstant {

    companion object {
        //空气消毒机类型
        const val AIRDEVICE = "airdevice"

        //空气消毒机状态
        const val AIROPENDATA = "airopendata"

        //空气消毒机最后一次档位
        const val AIRLASTDATA = "airlastdata"

        //空气消毒机滤网时间
        const val AIRLW = "airlw"
    }
}

class WifiConstant {
    companion object {
        /**
         * 用于保存连接WiFi名字和密码
         */
        const val WIFI_SSID = "WIFI_SSID"
        const val WIFI_PWSD = "WIFI_PWSD"
    }
}

/**
 * 第三方常量
 */
class ThirdPartyConstant {
    companion object {
        //调用讯飞接口使用的Authorization token
        const val AUTHORIZATION_TOKEN = "AUTHORIZATION_TOKEN"
    }

}

/**
 * 任务常量
 */
class TaskConstant {
    companion object {
        //更新TaskBean表的starttime和endtime字段
        const val UPDATE_TASKBEANTIME = "UPDATE_TASKBEANTIME"
    }

}

/**
 * 番茄专注工作法常量
 */
class FocusWorkConstant {
    companion object {
        //休息时长
        const val FOCUS_RELAX_TIME = "focus_relax_time"

        //专注时长
        const val FOCUS_TIME = "focus_time"

        //专注时是否播放背景音乐
        const val FOCUS_MUSIC_IS_OPEN = "focus_music_is_open"

        //专注时是否播放语音引导
        const val FOCUS_TTS_GUIDE_IS_OPEN = "focus_tts_guide_is_open"

        //选择的背景音乐
        const val FOCUS_MUSIC_SELECTED = "focus_music_selected"

        //每天只显示一次的提示信息
        const val IS_SHOW_ONCE_EVERYDAY = "is_show_once_everyday"

        //控制界面的引导是否显示过
        const val IS_CONTROL_VIEW_GUIDE_SHOWED = "is_control_view_guide_showed"

        //专注记录上滑的引导
        const val IS_PULL_UP_GUIDE_SHOW = "is_pull_up_guide_showed"

    }

    /**
     * APP下载地址 用于生成二维码
     */
    class EWMConstant {
        companion object {
            //休息时长
            const val APP_DOWNLOAD = "https://echo-office.loctek.com/app-download?"

        }
    }
}

/**
 * 先学后玩
 */
class LearnConstant {

    companion object {
        //先学后玩开启
        const val LEARNOPEN = "learn_open"

        //需要学习时间
        const val LEARNTIME = "learn_time"

        //可以娱乐时间
        const val HAPPYTIME = "happy_time"

        //时间限制开启
        const val LIMITOPEN = "limit_open"

        //时间段开始时间
        const val LIMITSTART = "limit_start"

        //时间段结束时间
        const val LIMITEND = "limit_end"

        //每天可用时长
        const val DAYTIME = "dayTime"

        //每天已经用时长
        const val DAYEDTIME = "dayedTime"

        //用来判断是否当天
        const val DAYDATE = "daydate"

        //人脸时间段解除
        const val FACELIMIT = "face_limit"

        //人脸当天时长解除
        const val FACEDAY = "face_day"
    }
}








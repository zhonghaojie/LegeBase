package com.lege.android.base.log;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by zhoushaoqing on 18-9-14.
 */

public class APPLog {
    private static final int LEVEL_V = 1;
    private static final int LEVEL_D = 2;
    private static final int LEVEL_I = 3;
    private static final int LEVEL_W = 4;
    private static final int LEVEL_E = 5;
    private static int mCurrentLevel = LEVEL_V;
    private static final boolean DEBUG = true;
    private static final String TAG = APPLog.class.getSimpleName();

    public static void init(String tag){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(5)
                .tag(tag)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }
    public static void json(Object obj) {
        if (!DEBUG) {
            return;
        }
        Logger.json(JSON.toJSONString(obj));
    }

    public  static void json(String tag, Object obj) {
        if (!DEBUG) {
            return;
        }
        Logger.t(tag);
        Logger.json(JSON.toJSONString(obj));
    }

    public  static void json(String tag, String json) {
        if (!DEBUG) {
            return;
        }
        Logger.t(tag);
        Logger.json(json);
    }

    public  static void json(String json) {
        if (!DEBUG) {
            return;
        }
        Logger.json(json);
    }

    public  static void log(String tag, String msg) {
        if (!DEBUG) {
            return;
        }
        Logger.t(tag);
        log(msg);
    }

    public  static void log(String msg) {
        if (!DEBUG) {
            return;
        }
        if (msg == null) {
            return;
        }
        switch (mCurrentLevel) {
            case LEVEL_V:
                Logger.v(msg);
                break;
            case LEVEL_D:
                Logger.d(msg);
                break;
            case LEVEL_I:
                Logger.i(msg);
                break;
            case LEVEL_W:
                Logger.w(msg);
                break;
            case LEVEL_E:
                Logger.e(msg);
                break;
            default:
                break;
        }
    }


    public  static void v(String tag, String msg) {
        if (!DEBUG) {
            return;
        }
        Logger.t(tag);
        v(msg);
    }

    public  static void v(String msg) {
        if (!DEBUG) {
            return;
        }
        Logger.v(msg);
    }

    public  static void i(String tag, String msg) {
        if (!DEBUG) {
            return;
        }
        Logger.t(tag);
        i(msg);
    }

    public  static void i(String msg) {
        if (!DEBUG) {
            return;
        }
        Logger.i(msg);
    }

    public  static void d(String tag, String msg) {
        if (!DEBUG) {
            return;
        }
        Logger.t(tag);
        d(msg);
    }

    public  static void d(String msg) {
        if (!DEBUG) {
            return;
        }
        Logger.d(msg);
    }

    public  static void e(String tag, String msg) {
        if (!DEBUG) {
            return;
        }
        Logger.t(tag);
        e(msg);
    }

    public  static void e(String msg) {
        if (!DEBUG) {
            return;
        }
        Logger.e(msg);
    }
}

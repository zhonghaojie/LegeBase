package com.lege.android.base;

import android.content.Context;

public class BaseApp {
    private static Context baseAppContext;
    /**
     * 子模块和主模块需要共享全局上下文，故需要在app module初始化时传入
     */
    public static void init(Context appContext) {
        if(baseAppContext == null) {
            baseAppContext = appContext.getApplicationContext();
        }
    }
    public static Context getAppContext() {
        return baseAppContext;
    }
}

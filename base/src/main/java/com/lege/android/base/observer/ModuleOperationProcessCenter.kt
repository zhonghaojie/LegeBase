package com.lege.android.base.observer

import android.app.Activity
import android.util.ArrayMap


/**
 * 模块操作处理中心
 * 主要负责：
 * 子module需要操作app module的情况
 * 子module向处理中心发送操作请求，处理中心收到请求后通知app module执行相应操作
 */
object ModuleOperationProcessCenter {
    interface OnOperationReceivedListener {
        fun onOperationReceived(operation:String,extra:String = "")
    }
    private var observableMap: MutableList<String>? = ArrayList()
    private var observers: ArrayMap<String, OnOperationReceivedListener>? = ArrayMap()

    //注册观察者，只有在处理中心注册过的才能做之后的操作
    fun registerObserver(observerName:String,observer: OnOperationReceivedListener){
        if(observers == null){
            observers = ArrayMap()
        }
        if(!observers!!.containsKey(observerName)){
            observers!![observerName] = observer
        }
    }

    //注册被观察者，只有在处理中心注册过的才能做之后的操作
    fun registerObservable(observableName: String) {
        if (observableMap == null) {
            observableMap = ArrayList()
        }
        if (!observableMap!!.contains(observableName)) {
            observableMap!!.add(observableName)
        }
    }

    /**
     * 由子module调用，告诉处理中心，谁（name）要做什么样的操作（operation）
     */
    fun needToControlAppModule(name:String,operation:String) {
        observableMap?.let {
            if (it.contains(name)) {
                observers?.forEach { it.value.onOperationReceived(operation) }
            }
        }
    }
    //移除被观察者
    fun unregisterObservable(observableName: String){
        observableMap?.let{
            it.remove(observableName)
        }
    }

}
package com.lege.android.base.observer

import android.app.Activity
import android.util.ArrayMap

/**
 * 用来监听Activity的打开和关闭
 */
object ActivityObserver {
    interface IActivityObserver {
        fun onObservableActivityCreate(name:String)
        fun onObservableActivityDestroy(name:String)
    }
    private var observableMap: MutableList<String>? = ArrayList()
    private var observers:ArrayMap<String, IActivityObserver>? = ArrayMap()

    fun release(){
        observers?.clear()
        observableMap?.clear()
    }
    //注册观察者
    fun registerObserver(observerName:String,observer: IActivityObserver){
        if(observers == null){
            observers = ArrayMap()
        }
        if(!observers!!.containsKey(observerName)){
            observers!!.put(observerName,observer)
        }
    }
    //移除观察者
    fun unregisterObserver(observerName:String){
        observers?.let{
            it.remove(observerName)
        }
    }
    //由被观察者调用
    fun onActivityOnCreate(activity: Activity) {
        observableMap?.let {
            if (it.contains(activity.javaClass.simpleName)) {
                observers?.forEach { it.value.onObservableActivityCreate(activity.javaClass.simpleName) }
            }
        }
    }
    //由被观察者调用
    fun onActivityDestroy(activity: Activity){
        observableMap?.let {
            if (it.contains(activity.javaClass.simpleName)) {
                observers?.forEach { it.value.onObservableActivityDestroy(activity.javaClass.simpleName) }
            }
        }
    }

    //注册被观察者
    fun registerObservable(activityName: String) {
        if (observableMap == null) {
            observableMap = ArrayList()
        }
        if (!observableMap!!.contains(activityName)) {
            observableMap!!.add(activityName)
        }
    }

    fun registerObservables(activityName: Collection<String>) {
        if (observableMap == null) {
            observableMap = ArrayList()
        }
        activityName.forEach {
            if (!observableMap!!.contains(it)) {
                observableMap!!.add(it)
            }
        }
    }

    //移除被观察者
    fun unregisterObservable(activityName: String){
        observableMap?.let{
            it.remove(activityName)
        }
    }
}
package com.lege.android.base.observer

import android.app.Activity
import android.util.ArrayMap


object EvsManagerObserver {
    interface IEvsManagerObserver {
        fun onObservaOpenEvsDialog()
    }
    private var observableMap: MutableList<String>? = ArrayList()
    private var observers: ArrayMap<String, IEvsManagerObserver>? = ArrayMap()

    //注册观察者
    fun registerObserver(observerName:String,observer: IEvsManagerObserver){
        if(observers == null){
            observers = ArrayMap()
        }
        if(!observers!!.containsKey(observerName)){
            observers!![observerName] = observer
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

    //由被观察者调用
    fun onActivityOnCreate(activity: Activity) {
        observableMap?.let {
            if (it.contains(activity.javaClass.simpleName)) {
                observers?.forEach { it.value.onObservaOpenEvsDialog() }
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
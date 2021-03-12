package com.lege.android.base.retrofit

/**
 * Description:网络请求监控
 * Created by loctek on 2021/3/9.
 */
object GlobalRequestWatcher {

    interface RequestListener {
        //网络不可用，上层要显示相应dialog
        fun onNetworkUnavailable()
    }

    private var requestListener: ArrayList<RequestListener> = ArrayList()

    fun addRequestListener(listener: RequestListener) {
        if (!requestListener.contains(listener)) {
            requestListener.add(listener)
        }
    }

    fun removeRequestListener(listener: RequestListener) {
        if (requestListener.contains(listener)) {
            requestListener.remove(listener)
        }
    }


    fun networkUnavailable() {
        for (listener in requestListener) {
            listener.onNetworkUnavailable()
        }
    }
}
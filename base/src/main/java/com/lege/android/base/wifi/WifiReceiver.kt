package com.lege.android.base.wifi

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.util.Log
import com.lege.android.base.log.APPLog


/**
 * Description:监听网络状态变化
 * Created by zhonghaojie on 2019-10-19.
 */
class WifiReceiver() : BroadcastReceiver() {

    companion object {
        const val TAG = "WifiReceiver"
        private val wifiReceiver = WifiReceiver()

        @JvmStatic
        fun registerReceiver(context: Application) {
            val intentFilter = IntentFilter()
            // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            // 监听wifi的连接状态,即是否连接上了一个有效wifi
            intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
            // 监听wifi的打开与关闭的5种状态
            intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
            context.registerReceiver(wifiReceiver, intentFilter)
        }

        @JvmStatic
        fun unregistReceiver(context: Application) {
            context.unregisterReceiver(wifiReceiver)
        }

        @JvmStatic
        fun registerObserver(observer: NetStateChangeObserver) {
            if (!wifiReceiver.mObservers.contains(observer)) {
                wifiReceiver.mObservers.add(observer)
            }
        }

        @JvmStatic
        fun unRegisterObserver(observer: NetStateChangeObserver) {
            wifiReceiver.mObservers.remove(observer)
        }
    }

    private val mObservers = ArrayList<NetStateChangeObserver>()
    private var mType: NetworkType? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            when (it.action) {
                ConnectivityManager.CONNECTIVITY_ACTION -> {
                    val networkType = NetworkUtil.getNetworkType(context)
                    Log.i(TAG, "网络类型——${networkType.name}")
                    notifyObservers(networkType)
                }
                WifiManager.NETWORK_STATE_CHANGED_ACTION -> {
                    val info = it.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)
                    val detailState = info.detailedState
                    val state = info.state
                    val type = info.typeName
                    val extra = info.extraInfo
                    Log.e(TAG, "state = ${state.name}")
                    Log.e(TAG, "detailState = ${detailState.name}")
                    Log.e(TAG, "type = $type")
                    Log.e(TAG, "extra = $extra")
                    for (observer in mObservers) {
                        observer.onNetworkStateChange(info)
                    }
                }
                WifiManager.WIFI_STATE_CHANGED_ACTION -> {
                    val wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1111)

                    var desc = when (wifiState) {
                        WifiManager.WIFI_STATE_DISABLED -> {
                            Log.d(TAG, "收到" + "WIFI_STATE_DISABLED")
                            "WIFI_STATE_DISABLED_断开"
                        }
                        WifiManager.WIFI_STATE_DISABLING -> {
                            Log.d(TAG, "收到" + "WIFI_STATE_DISABLING")
                            "WIFI_STATE_DISABLING_正在断开"
                        }
                        WifiManager.WIFI_STATE_ENABLED -> {
                            Log.d(TAG, "收到" + "WIFI_STATE_ENABLED")
                            "WIFI_STATE_ENABLED_已连接"
                        }
                        WifiManager.WIFI_STATE_ENABLING -> {
                            Log.d(TAG, "收到" + "WIFI_STATE_ENABLING")
                            "WIFI_STATE_ENABLING_正在连接"
                        }
                        WifiManager.WIFI_STATE_UNKNOWN -> {
                            Log.d(TAG, "WIFI_STATE_UNKNOWN")
                            "WIFI_STATE_UNKNOWN_未知"
                        }
                        else -> {
                            ""
                        }
                    }
                    val mWifiState = WifiState(wifiState, desc)
                    for (observer in mObservers) {
                        observer.onWifiStateChange(mWifiState)
                    }
                }
            }
        }

    }

    private fun notifyObservers(networkType: NetworkType) {
        mType = networkType
        if (networkType == NetworkType.NETWORK_NO) {
            for (observer in mObservers) {
                observer.onNetDisconnected()
            }
        } else {
            for (observer in mObservers) {
                APPLog.log("网络 连接上")
                observer.onNetConnected(networkType)
            }
        }
    }
}
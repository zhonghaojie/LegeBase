package com.lege.android.base.wifi;

import android.net.NetworkInfo;

/**
 * Description:
 * Created by zhonghaojie on 2019-10-19.
 */
public interface NetStateChangeObserver {
    /**
     * 失去网络连接
     */
    void onNetDisconnected();

    /**
     * 网络连接上
     * @param networkType
     */
    void onNetConnected(NetworkType networkType);

    /**
     * 网络状态改变状态改变
     * @param info
     */
    void onNetworkStateChange(NetworkInfo info);

    /**
     * wifi状态改变
     * @param state
     */
    void onWifiStateChange(WifiState state);
}

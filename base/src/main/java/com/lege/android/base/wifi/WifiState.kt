package com.lege.android.base.wifi

/**
 * Wifi状态
 * Description:
 * Created by zhonghaojie on 2019-10-19.
 */
data class WifiState (val code:Int,val desc:String){
    companion object{

        const val DISCONNECTED="DISCONNECTED"
        const val CONNECTING="CONNECTING"
        const val AUTHENTICATING="AUTHENTICATING"
        const val OBTAINING_IPADDR="OBTAINING_IPADDR"
        const val CONNECTED="CONNECTED"
    }
}

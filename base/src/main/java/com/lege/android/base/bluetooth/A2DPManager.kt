package com.lege.android.base.bluetooth

import android.bluetooth.BluetoothA2dp
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothProfile
import android.content.Context
import java.lang.reflect.Method

/**
 * Description:
 * Created by zhonghaojie on 2019-12-05.
 */

interface A2DPListener {
    fun onProxyServiceConnected(proxy: BluetoothA2dp)
}

class A2DPManager {
    private var a2dp: BluetoothA2dp? = null
    var a2dpListener: A2DPListener? = null
    fun init(blueAdapter: BluetoothAdapter, context: Context) {
        try{
            //有时会报service已绑定的崩溃，暂时找不到解绑service的方法
            blueAdapter.getProfileProxy(context, object : BluetoothProfile.ServiceListener {
                override fun onServiceDisconnected(profile: Int) {
                }

                override fun onServiceConnected(profile: Int, proxy: BluetoothProfile?) {
                    if (profile == BluetoothProfile.A2DP) {
                        a2dp = proxy as BluetoothA2dp //转换
                        a2dp?.let {
                            a2dpListener?.onProxyServiceConnected(it)
                        }

                    }
                }
            }, BluetoothProfile.A2DP)
        }catch (e:Exception){

        }

    }

    fun getConnectionState(device: BluetoothDevice): Int {
        return a2dp?.getConnectionState(device) ?: -1
    }

    private fun setPriority(device: BluetoothDevice?, priority: Int) {
        if (a2dp == null) return
        try { //通过反射获取BluetoothA2dp中setPriority方法（hide的），设置优先级
            val connectMethod: Method = BluetoothA2dp::class.java.getMethod("setPriority",
                    BluetoothDevice::class.java, Int::class.javaPrimitiveType)
            connectMethod.invoke(a2dp, device, priority)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 第一步getProfileProxy
     * 第二步和蓝牙配对
     * 第三步，connectA2dp
     *
     */
    fun connectA2dp(device: BluetoothDevice) {
        setPriority(device, 100) //设置priority
        try { //通过反射获取BluetoothA2dp中connect方法（hide的），进行连接。
            val connectMethod = BluetoothA2dp::class.java.getMethod("connect",
                    BluetoothDevice::class.java)
            connectMethod.invoke(a2dp, device)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    fun disConnectA2dp(device: BluetoothDevice) {
        setPriority(device, 0)
        try { //通过反射获取BluetoothA2dp中connect方法（hide的），断开连接。
            val connectMethod = BluetoothA2dp::class.java.getMethod("disconnect",
                    BluetoothDevice::class.java)
            connectMethod.invoke(a2dp, device)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}
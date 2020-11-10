package com.lege.android.base.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

/**
 * Description:
 * Created by zhonghaojie on 2020-04-21.
 */
class BluetoothListenerReceiver : BroadcastReceiver() {
    interface BluetoothStateChangeObserver {
        fun onBluetoothActionStateChange(state: Int)
    }

    companion object {
        private val receiver = BluetoothListenerReceiver()
        @JvmStatic
        fun registerReceiver(context: Context) {
            context.registerReceiver(receiver, makeFilter())
        }
        @JvmStatic
        fun unregisterReceiver(context: Context, receiver: BluetoothListenerReceiver) {
            context.unregisterReceiver(receiver)
        }
        @JvmStatic
        fun registerObserver(observer: BluetoothStateChangeObserver){
            receiver.mObservers.add(observer)
        }
        @JvmStatic
        fun unregisterObserver(observer: BluetoothStateChangeObserver){
            receiver.mObservers.remove(observer)
        }

        private fun makeFilter(): IntentFilter {
            val filter = IntentFilter()
            filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
            return filter
        }
    }

    private val mObservers = ArrayList<BluetoothStateChangeObserver>()
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            when (it.action) {
                BluetoothAdapter.ACTION_STATE_CHANGED -> {
                    val blueState = it.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0)
                    when (blueState) {
                        BluetoothAdapter.STATE_TURNING_ON -> {
                        }
                        BluetoothAdapter.STATE_ON -> {
                        }
                        BluetoothAdapter.STATE_TURNING_OFF -> {
                        }
                        BluetoothAdapter.STATE_OFF -> {
                        }
                    }
                    mObservers.forEach {
                        it.onBluetoothActionStateChange(blueState)
                    }
                }
                else->{}
            }
        }
    }


}
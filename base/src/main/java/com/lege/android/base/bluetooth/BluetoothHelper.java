package com.lege.android.base.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by zhoushaoqing on 18-10-31.
 */

public class BluetoothHelper {
    public static int DISCOVERY_DURATION = 120;
    private static BluetoothHelper INSTANCE = null;
    private BluetoothManager bluetoothManager = null;
    private BluetoothAdapter bluetoothAdapter = null;
    //获取系统蓝牙适配器管理类

    private BluetoothHelper(Context context) {
        bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public static  void init(Context context){
        if (INSTANCE == null) {
            INSTANCE = new BluetoothHelper(context);
        }
    }
    public static BluetoothHelper getInstance() {

        return INSTANCE;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter;
    }

    public void enableBluetooth() {
        if (bluetoothAdapter != null) {
            boolean result = bluetoothAdapter.enable();
            Log.i("蓝牙","enableBluetooth   result = "+result );
        }
    }

    public void enableBluetooth2(Activity activity,int requestCode){
        Intent enableBleIntent =new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        enableBleIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,DISCOVERY_DURATION);
        Log.i("ACTION",enableBleIntent.getAction());
        activity.startActivityForResult(enableBleIntent,requestCode);
    }

    public void disableBluetooth() {
        if (bluetoothAdapter != null) {
            boolean result =bluetoothAdapter.disable();
            Log.i("蓝牙","disableBluetooth   result = "+result );
        }
    }

    public void cancelDiscovery() {
        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
        }
    }

    public boolean isBluetoothEnable() {
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.isEnabled();
        }
        return false;
    }

    public boolean isDiscovering() {
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.isDiscovering();
        }
        return false;
    }

    public BluetoothDevice getRemoteDeivce(String mac) {
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.getRemoteDevice(mac);
        }
        return null;
    }

    public Set<BluetoothDevice> getBoundDevice() {
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.getBondedDevices();
        }
        return null;
    }

    public void startDiscovery(){
        if (bluetoothAdapter != null) {
            boolean isEnable = bluetoothAdapter.isEnabled();
            boolean result = bluetoothAdapter.startDiscovery();
            Log.i("蓝牙",isEnable+"   startDiscovery   result = "+result );
        }
    }

    /**
     * 清除已配对的设备
     */
    public void removePairDevice(BluetoothDevice device) {
        if (bluetoothAdapter != null) {
            Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
            if (bondedDevices.contains(device)) {
                unpairDevice(device);
            }
        }
    }

    /**
     * 清除已配对的所有设备
     */
    public void removeAllPairDevice() {
        if (bluetoothAdapter != null) {
            Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
            for (BluetoothDevice device : bondedDevices) {
                unpairDevice(device);
            }
        }
    }

    /**
     * 反射来调用BluetoothDevice.removeBond取消设备的配对
     *
     * @param device
     */

    private void unpairDevice(BluetoothDevice device) {
        try {
            Method m = device.getClass().getMethod("removeBond", (Class[]) null);
            m.invoke(device, (Object[]) null);
        } catch (Exception e) {
            Log.e("zhou", e.getMessage());
        }
    }

    public static String getBtAddressByReflection() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Field field = null;
        try {
            field = BluetoothAdapter.class.getDeclaredField("mService");
            field.setAccessible(true);
            Object bluetoothManagerService = field.get(bluetoothAdapter);
            if (bluetoothManagerService == null) {
                return null;
            }
            Method method = bluetoothManagerService.getClass().getMethod("getAddress");
            if (method != null) {
                Object obj = method.invoke(bluetoothManagerService);
                if (obj != null) {
                    return obj.toString();
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


}

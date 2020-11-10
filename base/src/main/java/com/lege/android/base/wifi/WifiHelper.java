package com.lege.android.base.wifi;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import com.lege.android.base.log.APPLog;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by zhoushaoqing on 18-10-30.
 */

public class WifiHelper {
    private static WifiHelper INSTANCE = null;
    private WifiManager wifiManager;
    private static final String TAG="ConfigNetWork_TEST";

    private WifiHelper() {

    }

    public void init(Application context){
        wifiManager = (WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    public static WifiHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WifiHelper();
        }
        return INSTANCE;
    }

    public WifiManager getWifiManager(){
        return  wifiManager;
    }
    /**
     * openWifi
     */
    public void openWifi(){
        if (!wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);
        }
    }

    public boolean isWifiEnable(){
        return wifiManager.isWifiEnabled();
    }
    /**
     * 连接有密码的Wifi热点
     *
     * @param ssid
     * @param pwd
     */
    public boolean connectNeedPwdWifi(String ssid, String pwd) {
        WifiConfiguration wifiConfig;
        wifiConfig = setWifiParams(ssid, pwd);
        int wcgID = wifiManager.addNetwork(wifiConfig);
        boolean flag = wifiManager.enableNetwork(wcgID, true);
        APPLog.log(TAG,"connectWifi===" + ssid + "pwd    "+pwd+"===flag==" + flag);
        return flag;
    }


    /**
     * 连接没有密码的Wifi热点
     *
     * @param ssid
     */
    public void connectNoPwdWifi(String ssid) {
        WifiConfiguration wifiConfig;
        wifiConfig = setWifiParamsNoPassword(ssid);
        int wcgID = wifiManager.addNetwork(wifiConfig);
        boolean flag = wifiManager.enableNetwork(wcgID, true);
        APPLog.log(TAG,"connectWifi===" + ssid);
    }

    /**
     * 进行有密码Wifi连接
     *
     * @param ssid
     * @param pwd
     * @return
     */
    public WifiConfiguration setWifiParams(String ssid, String pwd) {
        String gol_password = pwd;
        WifiConfiguration apConfig = new WifiConfiguration();
        apConfig.SSID = "\"" + ssid + "\"";
        apConfig.preSharedKey = "\"" + gol_password + "\"";
        apConfig.hiddenSSID = true;
        apConfig.status = WifiConfiguration.Status.ENABLED;
        apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        apConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        apConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        return apConfig;
    }

    /**
     * 进行无密码Wifi连接
     *
     * @param ssid
     * @return
     */
    public WifiConfiguration setWifiParamsNoPassword(String ssid) {
        String SSID = ssid;
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";
        // 没有密码，一定要注释这两个值
        // config.wepKeys[0] = "";
        // config.wepTxKeyIndex = 0;
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        return config;
    }

    /**
     * 删除某个已经保存好的Wifi热点
     *
     */
    public void removeNetwork() {
        List<WifiConfiguration> existingConfigs;
        existingConfigs = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration existingConfig : existingConfigs) {
            boolean delete = wifiManager.removeNetwork(existingConfig.networkId);
            Log.d(TAG,"removeNetwork=====delete==="+delete);
            Log.d(TAG,"removeNetwork=====status==="+existingConfig.status);
            Log.d(TAG,"removeNetwork=====SSID==="+existingConfig.SSID);
        }
        boolean result = wifiManager.saveConfiguration();
        Log.d(TAG,"removeNetwork=====isSaveConfiguration==="+result);

    }

    /**
     * 保存到系统中，重启后有效
     */
    private void saveNetWork() {
        wifiManager.saveConfiguration();
    }

    /**
     * 获取连接好的wifi热点
     */
    public String getCurrentWifiSSID() {
        WifiInfo info = wifiManager.getConnectionInfo();
        String ssid = info != null ? info.getSSID() : "null";
        if (ssid != null) {
            if (ssid.length() > 2 && ssid.charAt(0) == '"' && ssid.charAt(ssid.length() - 1) == '"') {
                return ssid.substring(1, ssid.length() - 1);
            }
        }
        return null;
    }


    /**
     * 获取当前连接好的Wifi热点
     *
     * @return
     */
    public String getCurrentWifiState() {
        int wifiState = wifiManager.getWifiState();
        String state = "";
        switch (wifiState) {
            case 0:
//                state = "WIFI_STATE_DISABLING";
                state = "断开连接中";
                break;
            case 1:
//                state = "WIFI_STATE_DISABLED";
                state = "未连接";
                break;
            case 2:
//                state = "WIFI_STATE_ENABLING";
                state = "连接中";
                break;
            case 3:
//                state = "WIFI_STATE_ENABLED";
                state = "已连接";
                break;
            case 4:
//                state = "WIFI_STATE_UNKNOWN";
                state = "未知";
                break;
            default:
                break;
        }
        return state;
    }

    /**
     * 使用 WifiConfiguration 连接
     * @param manager
     * @param config
     */
    public  void connectByConfig(WifiManager manager, WifiConfiguration config) {
        if (manager == null) {
            return;
        }
        try {
            Method connect = manager.getClass().getDeclaredMethod("connect", WifiConfiguration.class, Class.forName("android.net.wifi.WifiManager$ActionListener"));
            if (connect != null) {
                connect.setAccessible(true);
                connect.invoke(manager, config, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 networkId 连接.
     * @param manager
     * @param networkId
     */
    public  void connectByNetworkId(WifiManager manager, int networkId) {
        if (manager == null) {
            return;
        }
        try {
            Method connect = manager.getClass().getDeclaredMethod("connect", int.class, Class.forName("android.net.wifi.WifiManager$ActionListener"));
            if (connect != null) {
                connect.setAccessible(true);
                connect.invoke(manager, networkId, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 保存网络
     * @param manager
     * @param config
     */
    public  void saveNetworkByConfig(WifiManager manager, WifiConfiguration config) {
        if (manager == null) {
            return;
        }
        try {
            Method save = manager.getClass().getDeclaredMethod("save", WifiConfiguration.class, Class.forName("android.net.wifi.WifiManager$ActionListener"));
            if (save != null) {
                save.setAccessible(true);
                save.invoke(manager, config, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加网络
     * @param manager
     * @param config
     * @return
     */
    public  void addNetwork(WifiManager manager, WifiConfiguration config) {
        if (manager != null) {
            manager.addNetwork(config);
        }
    }

    /**
     * 忘记网络
     * @param manager
     * @param networkId
     */
    public  void forgetNetwork(WifiManager manager, int networkId) {
        if (manager == null) {
            return;
        }
        try {
            Method forget = manager.getClass().getDeclaredMethod("forget", int.class, Class.forName("android.net.wifi.WifiManager$ActionListener"));
            if (forget != null) {
                forget.setAccessible(true);
                forget.invoke(manager, networkId, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  禁用网络
     * @param manager
     * @param networkId
     */
    public  void disableNetwork(WifiManager manager, int networkId) {
        if (manager == null) {
            return;
        }
        try {
            Method disable = manager.getClass().getDeclaredMethod("disable", int.class, Class.forName("android.net.wifi.WifiManager$ActionListener"));
            if (disable != null) {
                disable.setAccessible(true);
                disable.invoke(manager, networkId, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  断开连接
     * @param manager
     * @return
     */
    public  boolean disconnectNetwork(WifiManager manager) {
        return manager != null && manager.disconnect();
    }


    /**
     * 禁用短暂网络
     * @param manager
     * @param SSID
     */
    public static void disableEphemeralNetwork(WifiManager manager, String SSID) {
        if (manager == null || TextUtils.isEmpty(SSID)) return;
        try {
            Method disableEphemeralNetwork = manager.getClass().getDeclaredMethod("disableEphemeralNetwork", String.class);
            if (disableEphemeralNetwork != null) {
                disableEphemeralNetwork.setAccessible(true);
                disableEphemeralNetwork.invoke(manager, SSID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 切换到指定wifi
     * @param wifiName  指定的wifi名字
     * @param wifiPwd   wifi密码，如果已经保存过密码，可以传入null
     * @return
     */
    public boolean changeToWifi(String wifiName, String wifiPwd){
        if(wifiManager == null){
            APPLog.log( TAG," ***** init first ***** ");
            return false;
        }

        String __wifiName__ = "\"" + wifiName + "\"";

        List wifiList = wifiManager.getConfiguredNetworks();
        boolean bFindInList = false;
        for (int i = 0; i < wifiList.size(); ++i) {
            WifiConfiguration wifiInfo0 = (WifiConfiguration) wifiList.get(i);

            // 先找到对应的wifi
            if (__wifiName__.equals(wifiInfo0.SSID) || wifiName.equals(wifiInfo0.SSID)) {
                // 1、 先启动，可能已经输入过密码，可以直接启动
                APPLog.log( TAG," set wifi 1 = " + wifiInfo0.SSID);
                return doChange2Wifi(wifiInfo0.networkId);
            }

        }

        // 2、如果wifi还没有输入过密码，尝试输入密码，启动wifi
        if(!bFindInList){
            WifiConfiguration wifiNewConfiguration = createWifiInfo(wifiName, wifiPwd);//使用wpa2的wifi加密方式
            int newNetworkId = wifiManager.addNetwork(wifiNewConfiguration);
            if (newNetworkId == -1) {
                APPLog.log(TAG,"操作失败,需要您到手机wifi列表中取消对设备连接的保存");
            } else {
                return doChange2Wifi(newNetworkId);
            }
        }

        return false;
    }

    private boolean doChange2Wifi(int newNetworkId) {
        // 如果wifi权限没打开（1、先打开wifi，2，使用指定的wifi
        if(!wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);
        }

        boolean enableNetwork = wifiManager.enableNetwork(newNetworkId, true);
        if (!enableNetwork) {
            APPLog.log(TAG,"切换到指定wifi失败");
            return false;
        } else {
            APPLog.log(TAG,"切换到指定wifi成功");
            return true;
        }
    }

    /**
     * 创建 WifiConfiguration，这里创建的是wpa2加密方式的wifi
     *
     * @param ssid     wifi账号
     * @param password wifi密码
     * @return
     */
    private WifiConfiguration createWifiInfo(String ssid, String password) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + ssid + "\"";
        config.preSharedKey = "\"" + password + "\"";
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        config.status = WifiConfiguration.Status.ENABLED;
        return config;
    }

    public boolean isWifiConnected(Application context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
    }
}

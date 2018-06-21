package com.robot.lighting.wifi

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.net.wifi.aware.WifiAwareManager
import android.util.Log
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import org.apache.commons.lang3.StringUtils


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/20
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
class WifiService(val context: Context) {

    @SuppressLint("WifiManagerPotentialLeak")
    private var wifiManager: WifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager

    private var wifiInfo: WifiInfo

    init {
        wifiInfo = wifiManager.connectionInfo
        if (wifiManager.isWifiEnabled)
            wifiManager.startScan()
    }

    fun openWifi() {
        when (wifiManager.wifiState) {
            WifiManager.WIFI_STATE_ENABLED -> {
                ToastUtils.showShort("请勿重新打开!")
            }
            WifiManager.WIFI_STATE_ENABLING -> {
                ToastUtils.showShort("正在打开wifi中!")
            }
            WifiManager.WIFI_STATE_DISABLED -> {
                wifiManager.isWifiEnabled = true
                wifiManager.startScan()
            }
            else -> {
                ToastUtils.showShort("请重新打开Wifi")
            }
        }
    }

    fun closeWifi() {
        when (wifiManager.wifiState) {
            WifiManager.WIFI_STATE_DISABLING -> {
                ToastUtils.showShort("wifi正在关闭中！")
            }
            WifiManager.WIFI_STATE_DISABLED -> {
                ToastUtils.showShort("wifi已经关闭,请勿重复关闭!")
            }
            WifiManager.WIFI_STATE_ENABLED -> {
                wifiManager.isWifiEnabled = false
            }
            else -> {
                ToastUtils.showShort("请重新关闭!")
            }
        }
    }

    fun getDeviceList() = wifiManager.scanResults

    fun getConfiguredDevices() = wifiManager.configuredNetworks

    fun getWifiInfo() = wifiInfo

    // 获取无线AP列表。返回的 MAP 是 SSID->AccessPoint. 注意 SSID 可能重复。
    fun listAccessPoints(context: Context): Map<String, AccessPoint> {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val scanResults = wifiManager.scanResults ?: return HashMap(0)

        var maxLevel = Integer.MIN_VALUE
        var minLevel = Integer.MAX_VALUE

        val accessPoints = HashMap<String, AccessPoint>(scanResults.size)

        for (sr in scanResults) {
            if (StringUtils.isBlank(sr.SSID)) continue

            val ap = AccessPoint(sr.SSID)

            ap.level = sr.level
            ap.levelGrade = WifiManager.calculateSignalLevel(sr.level, 5)

            if (ap.level > maxLevel) {
                maxLevel = ap.level
            }
            if (ap.level < minLevel) {
                minLevel = ap.level
            }

            // 判断加密方式
            ap.security = if (sr.capabilities.contains(AccessPoint.SECURITY_WPA)) {
                AccessPoint.SECURITY_WPA
            } else if (sr.capabilities.contains(AccessPoint.SECURITY_WEP)) {
                AccessPoint.SECURITY_WEP
            } else {
                AccessPoint.SECURITY_NONE
            }
            accessPoints.put(ap.SSID, ap)
        }

//        Log.d(LOG_TAG, "Find APs: " + accessPoints.size)
        // Log.d(LOG_TAG, "信号最大：" + maxLevel + " 信号最小：" + minLevel);
//
//        val configuredNetworks = wifiManager.configuredNetworks // 会null!
//        if (configuredNetworks != null) {
//            for (config in configuredNetworks) {
//                // Log.d(LOG_TAG, "已配置网络：" + config.SSID);
//                val SSID = trimQuoteFromSSID(config.SSID)
//                var accessPoint: AccessPoint? = accessPoints[SSID]
//                if (accessPoint != null) {
//                    accessPoint.configured = true
//                    accessPoint.networkId = config.networkId
//                }
//            }
//        } else {
//            // TODO(yy) refreshWifiAccessPoints(context);
//        }

//        val connection = wifiManager.connectionInfo
//        if (connection != null) {
////            Log.d(LOG_TAG, "Connected AP：" + connection.ssid)
//            val SSID = trimQuoteFromSSID(connection.ssid)
//            val accessPoint = accessPoints[SSID]
//            if (accessPoint != null) {
//                accessPoint.ip = connection.ipAddress
//                accessPoint.connected = true
//
//                // 获取 supplicantState
//                val supplicantState = connection.supplicantState
//                accessPoint.supplicantState = supplicantState?.toString() ?: ""
//
//                // 获取 detailed 状态
//                val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//                val info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
//                if (info != null) {
//                    val state = info.detailedState
//                    accessPoint.detailedState = state?.toString() ?: ""
//                }
//            }
//        }

        return accessPoints
    }


}
package com.robot.design.viewmodel

import android.Manifest
import android.arch.lifecycle.AndroidViewModel
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.databinding.ObservableField
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.widget.Toast
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.robot.design.App
import com.robot.lighting.wifi.AccessPoint
import com.robot.lighting.wifi.WifiService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


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
class WifiViewModel : AndroidViewModel(App.getApplication()) {

    var wifiInfo = ObservableField<WifiInfo>()

    var wifiResult = ObservableField<List<AccessPoint>>()

    fun onStart() {
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)
        getApplication<App>().registerReceiver(wifiReceiver, filter)
    }

    fun fetchWifiInfo() {
        val wifiService = WifiService(getApplication())
        wifiInfo.set(wifiService.getWifiInfo())
    }

    fun startSearchWifi() {
        PermissionUtils.permission(Manifest.permission_group.LOCATION)
                .callback(object : PermissionUtils.SimpleCallback {
                    override fun onGranted() {
                        val wifiService = WifiService(getApplication())
                        Observable.interval(2000, TimeUnit.MILLISECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : Observer<Long> {
                                    override fun onSubscribe(disposable: Disposable) {
                                    }

                                    override fun onNext(number: Long) {
                                        val result = wifiService.listAccessPoints(getApplication()).map {
                                            it.value
                                        }
                                        wifiResult.set(result)
                                    }

                                    override fun onError(e: Throwable) {

                                    }

                                    override fun onComplete() {
                                    }
                                })
                    }

                    override fun onDenied() {
                        ToastUtils.showShort("无法获取Wifi信息，请授权！")
                    }

                }).request()
    }

    override fun onCleared() {
        super.onCleared()
        getApplication<App>().unregisterReceiver(wifiReceiver)
    }

    private val wifiReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent == null) return

            if (intent.action == WifiManager.SUPPLICANT_STATE_CHANGED_ACTION) {
                //wifi连接
            } else if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
                val info = intent.getParcelableExtra<NetworkInfo>(ConnectivityManager.EXTRA_NETWORK_INFO) ?: return

                val detailedState = info.detailedState

                when (detailedState) {
                    NetworkInfo.DetailedState.AUTHENTICATING -> ToastUtils.showShort("密码验证中...")
                    NetworkInfo.DetailedState.BLOCKED -> ToastUtils.showShort("阻塞")
                    NetworkInfo.DetailedState.CONNECTED -> ToastUtils.showShort("已连接")
                    NetworkInfo.DetailedState.CONNECTING -> ToastUtils.showShort("连接中")
                    NetworkInfo.DetailedState.DISCONNECTED -> ToastUtils.showShort("断开")
                    NetworkInfo.DetailedState.DISCONNECTING -> ToastUtils.showShort("断开...")
                    NetworkInfo.DetailedState.FAILED -> ToastUtils.showShort("失败")
                    NetworkInfo.DetailedState.OBTAINING_IPADDR -> ToastUtils.showShort("正在获取IP")
                    NetworkInfo.DetailedState.SCANNING -> ToastUtils.showShort("正在扫描")
                    else -> {
                    }
                }
            }
        }

    }
}
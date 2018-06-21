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
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.robot.design.App
import com.robot.lighting.wifi.AccessPoint
import com.robot.lighting.wifi.WifiService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.*
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

    private var wifiService = WifiService(getApplication())
    private var isInit = true
    private var disposable: Disposable? = null

    var wifiResult = ObservableField<List<AccessPoint>>()

    var checked = ObservableField<Boolean>()


    fun onStart() {
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)
        getApplication<App>().registerReceiver(wifiReceiver, filter)

        //设置Wifi开关按钮
        checked.set(wifiService.isOpen())
    }

    fun onCheckedChange() {
        if (isInit) {
            isInit = false
            return
        }
        if (wifiService.isOpen()) {
            wifiService.closeWifi()
            stopSearch()
            ToastUtils.showShort("关闭")
        } else {
            wifiService.openWifi()
            ToastUtils.showShort("打开")
        }
    }

    fun startSearchWifi() {
        PermissionUtils.permission(Manifest.permission_group.LOCATION)
                .callback(object : PermissionUtils.SimpleCallback {
                    override fun onGranted() {
                        val wifiService = WifiService(getApplication())
                        disposable = Observable.interval(2000, TimeUnit.MILLISECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeBy(
                                        onNext = {
                                            val result = wifiService.listAccessPoints(getApplication()).map {
                                                it.value
                                            }
                                            Collections.sort(result) { p0, p1 ->
                                                return@sort p1.levelGrade - p0.levelGrade
                                            }
                                            wifiResult.set(result)
                                        },
                                        onError = {},
                                        onComplete = {}
                                )
                    }

                    override fun onDenied() {
                        ToastUtils.showShort("无法获取Wifi信息，请授权！")
                    }

                }).request()
    }

    fun stopSearch() {
        if (disposable == null || disposable!!.isDisposed) {
            return
        }
        disposable!!.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        getApplication<App>().unregisterReceiver(wifiReceiver)
    }

    private val wifiReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent == null) return
//            val wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0)
//            if (wifiState == WifiManager.WIFI_STATE_ENABLING) {
//                ToastUtils.showShort("打开中...")
//            } else if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
//                ToastUtils.showShort("关闭中...")
//            }

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
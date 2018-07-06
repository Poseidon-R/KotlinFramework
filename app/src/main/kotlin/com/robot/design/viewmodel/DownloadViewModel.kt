package com.robot.design.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.net.TrafficStats
import com.blankj.utilcode.util.ToastUtils
import com.robot.design.App
import com.robot.lighting.rest.download.DownloadInfo
import com.robot.lighting.rest.download.DownloadListener
import com.robot.lighting.rest.download.DownloadManager
import com.robot.lighting.rest.download.DownloadState
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/14
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */

class DownloadViewModel : ViewModel(), DownloadListener {

    private var lastTotalRxBytes = 0L
    private var lastTimeStamp = 0L
    var mDownloadInfo = ObservableField<DownloadInfo>()
    val downloadManager = DownloadManager.getInstance()
    val netSpeed = ObservableField<String>()

    fun doDownload() {
        downloadManager.startDownload(yingyongbao, App.getApkDownloadDirectory().absolutePath, this)
        getDownloadSpeed()
    }

    override fun onStartDownload() {
        mDownloadInfo.set(DownloadInfo(0, DownloadState.START))
    }

    override fun onProgress(progress: Int) {
        mDownloadInfo.set(DownloadInfo(progress, DownloadState.DOWNLOADING))
    }

    override fun onFinishDownload() {
        mDownloadInfo.set(DownloadInfo(100, DownloadState.FINISH))
    }

    override fun onFail(errorInfo: String) {
        mDownloadInfo.set(DownloadInfo(0, DownloadState.FAILED))
    }


    private fun getDownloadSpeed() {

        var mDisposable: Disposable
        Observable.intervalRange(1, 100000, 0, 100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {
                    override fun onSubscribe(disposable: Disposable) {
                        mDisposable = disposable
                    }

                    override fun onNext(number: Long) {
                        val nowTotalRxBytes = if (TrafficStats.getUidRxBytes(App.getApplication().applicationInfo.uid) == TrafficStats.UNSUPPORTED.toLong()) 0 else TrafficStats.getTotalRxBytes() / 1024
                        val nowTimeStamp = System.currentTimeMillis()
                        val speed = (nowTotalRxBytes - lastTotalRxBytes) * 1000 / (nowTimeStamp - lastTimeStamp)//毫秒转换

                        lastTimeStamp = nowTimeStamp
                        lastTotalRxBytes = nowTotalRxBytes
                        netSpeed.set(formatSize(speed))
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {
                        ToastUtils.showShort("onComplete")
                    }
                })
    }

    private fun formatSize(speed: Long): String {
        if (speed > 1024) {
            return "${speed / 1024} m/s"
        } else {
            return "$speed kb/s"
        }
    }

    companion object {
        const val yingyongbao = "http://imtt.dd.qq.com/16891/7B0BBB8DC7B6F5D469F20432046395C9.apk?fsname=com.tencent.android.qqdownloader_7.2.2_7222130.apk&csr=1bbd"
    }

}
package com.robot.design.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View
import com.robot.design.App
import com.robot.design.component.DaggerAppComponent
import com.robot.design.module.AppModule
import com.robot.lighting.rest.download.DownloadInfo
import com.robot.lighting.rest.download.DownloadListener
import com.robot.lighting.rest.download.DownloadManager
import com.robot.lighting.rest.download.DownloadState
import com.robot.lighting.utils.MainBus
import com.squareup.otto.Subscribe
import javax.inject.Inject


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


    var mDownloadInfo = ObservableField<DownloadInfo>()
    val downloadManager = DownloadManager.getInstance()

    fun doDownload() {
        downloadManager.startDownload(yingyongbao, App.getApkDownloadDirectory().absolutePath, this)
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

    companion object {
        const val yingyongbao = "http://imtt.dd.qq.com/16891/7B0BBB8DC7B6F5D469F20432046395C9.apk?fsname=com.tencent.android.qqdownloader_7.2.2_7222130.apk&csr=1bbd"
    }

}
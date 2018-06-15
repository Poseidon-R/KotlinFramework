package com.robot.lighting.rest.download

import com.robot.lighting.utils.MainBus
import com.squareup.otto.ThreadEnforcer
import javax.inject.Inject


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/1
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
class DownloadListenerImpl : DownloadListener {

    private val bus = MainBus(ThreadEnforcer.ANY)

    override fun onStartDownload() {
        bus.register(this)
        bus.post(DownloadInfo(0, DownloadState.START))
    }

    override fun onProgress(progress: Int) {
        bus.post(DownloadInfo(progress, DownloadState.DOWNLOADING))
    }

    override fun onFinishDownload() {
        bus.post(DownloadInfo(100, DownloadState.FINISH))
        bus.unregister(this)
    }

    override fun onFail(errorInfo: String) {
        bus.post(DownloadInfo(0, DownloadState.FAILED))
        bus.unregister(this)
    }

}
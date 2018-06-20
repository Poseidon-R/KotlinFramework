package com.robot.design.ui.binding

import android.databinding.BindingAdapter
import com.robot.lighting.widget.LightingProgressbar
import com.robot.lighting.rest.download.DownloadInfo
import com.robot.lighting.rest.download.DownloadState


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/15
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
object DownloadBinding {

    @BindingAdapter("app:download_info")
    @JvmStatic
    fun bindDownloadState(progress: LightingProgressbar, downloadInfo: DownloadInfo?) {
        if(downloadInfo == null)return
        when (downloadInfo.state) {
            DownloadState.START -> progress.progress = 0F
            DownloadState.DOWNLOADING -> progress.progress = downloadInfo.progress.toFloat()
            DownloadState.PAUSE -> progress.finishLoad()
            DownloadState.FINISH -> {
                progress.progress = 100f
                progress.finishLoad()
            }
            else -> {
            }
        }
    }

}
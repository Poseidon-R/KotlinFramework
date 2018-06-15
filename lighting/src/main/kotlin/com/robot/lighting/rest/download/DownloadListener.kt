package com.robot.lighting.rest.download

/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/05/10
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
interface DownloadListener {
    fun onStartDownload()

    fun onProgress(progress: Int)

    fun onFinishDownload()

    fun onFail(errorInfo: String)
}
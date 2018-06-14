package com.robot.design.rest.download

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

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
class DownloadInterceptor(private val downloadListener: DownloadListener) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return response.newBuilder().body(
                ResponseBody(response.body()!!, downloadListener)).build()
    }
}
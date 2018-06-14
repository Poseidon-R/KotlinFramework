package com.robot.design.rest

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url


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
interface DownloadService{

    @Streaming
    @GET
    fun dowloadApk(@Url url: String): Observable<ResponseBody>

}
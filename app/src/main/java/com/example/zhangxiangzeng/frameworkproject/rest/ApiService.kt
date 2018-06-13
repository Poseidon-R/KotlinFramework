package com.example.zhangxiangzeng.frameworkproject.rest

import com.example.zhangxiangzeng.frameworkproject.data.User
import com.example.zhangxiangzeng.frameworkproject.model.PlacePO
import io.reactivex.Observable
import retrofit2.http.*


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/11
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
interface ApiService {

    @GET("api/place/list")
    fun queryPlace(@Query("param") key: String, @Query("pageNumber") pageNo: Int,
                   @Query("pageSize") pageSize: Int): Observable<BaseResponseArray<PlacePO>>

    @FormUrlEncoded
    @POST("/api/login")
    fun doLogin(@Field("userName") username: String, @Field("password") password: String,
                @Field("system") system: String): Observable<BaseResponse<User>>

}
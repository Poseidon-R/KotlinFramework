package com.example.zhangxiangzeng.frameworkproject.rest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.zhangxiangzeng.frameworkproject.data.User
import com.example.zhangxiangzeng.frameworkproject.model.PlacePO
import com.example.zhangxiangzeng.frameworkproject.module.NetModule
import com.example.zhangxiangzeng.frameworkproject.rest.req.LoginReq
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


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
class RestService {

    @Inject
    lateinit var apiService: ApiService

    init {
//        DaggerNetComponent.builder()
//                .netModule(NetModule())
//                .build()
//                .inject(this)
    }

    fun fetchPlacesByKey(key: String, pageNo: Int, pageSize: Int, errorAction: () -> Unit): LiveData<List<PlacePO>> {
        val result = MutableLiveData<List<PlacePO>>()
        apiService.queryPlace(key, pageNo, pageSize).async().subscribeBy(
                onNext = {
                    result.postValue(it.results)
                },
                onError = {
                    errorAction()
                }
        )
        return result
    }

    fun login(loginReq: LoginReq, errorAction: () -> Unit) {
        val result = MutableLiveData<User>()
    }

}
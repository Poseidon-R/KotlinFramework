package com.example.zhangxiangzeng.frameworkproject

import android.animation.*
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import com.example.zhangxiangzeng.frameworkproject.rest.ApiService
import com.example.zhangxiangzeng.frameworkproject.rest.async
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : Activity() {

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        (application as App).getNetComponent().inject(this)

        retrofit.create(ApiService::class.java)
                .doLogin("15211168600", "skkey123", "app")
                .async()
                .subscribeBy(
                        onNext = {

                        },
                        onError = {

                        }
                )
//        DaggerNetComponent.builder()
//                .netModule(NetModule())
//                .build()
//                .inject(this)
//        val result = apiService.fetchPlacesByKey("d", 1, 1) {
//            ToastUtils.showShort("请求失败")
//        }
//        result.observe(this, Observer {
//            if (it == null) return@Observer
//            ToastUtils.showShort("搜索到结果:${it.size}条")
//        })
    }
}

package com.robot.design

import android.app.Application
import android.os.Environment
import com.blankj.utilcode.util.Utils
import com.robot.design.component.AppComponent
import com.robot.design.component.DaggerAppComponent
import com.robot.design.component.DaggerNetComponent
import com.robot.design.component.NetComponent
import com.robot.design.module.AppModule
import com.robot.design.module.NetModule
import com.robot.lighting.utils.MainBus
import com.squareup.otto.ThreadEnforcer
import java.io.File


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
class App : Application() {

    private lateinit var mNetComponent: NetComponent
    private val mainBus = MainBus(ThreadEnforcer.ANY)

    override fun onCreate() {
        super.onCreate()
        app = this
        Utils.init(this)
        mNetComponent = DaggerNetComponent.builder()
                .netModule(NetModule("http://192.168.8.148:7204"))
                .build()
        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    fun getBusInstance() = mainBus

    fun getNetComponent(): NetComponent {
        return mNetComponent
    }


    companion object {
        private lateinit var mAppComponent: AppComponent
        private lateinit var app: App

        @JvmStatic
        fun getAppComponent() = mAppComponent

        @JvmStatic
        fun getApkDownloadDirectory(): File {
            return File(Environment.getExternalStorageDirectory(), "yingyongbao.apk")
        }

        @JvmStatic
        fun getApplication() = app

    }
}


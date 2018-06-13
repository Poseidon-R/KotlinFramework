package com.robot.design

import android.app.Application
import com.robot.design.component.DaggerNetComponent
import com.robot.design.component.NetComponent
import com.robot.design.module.AppModule
import com.robot.design.module.NetModule


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

    override fun onCreate() {
        super.onCreate()
        mNetComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule("http://192.168.8.148:7204"))
                .build()
    }

    fun getNetComponent(): NetComponent {
        return mNetComponent
    }
}
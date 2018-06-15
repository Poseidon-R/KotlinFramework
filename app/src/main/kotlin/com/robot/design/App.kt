package com.robot.design

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.robot.design.component.DaggerNetComponent
import com.robot.design.component.NetComponent
import com.robot.design.module.AppModule
import com.robot.design.module.NetModule
import com.robot.lighting.utils.MainBus
import com.squareup.otto.ThreadEnforcer


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
        Utils.init(this)
        mNetComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule("http://192.168.8.148:7204"))
                .build()
    }

    fun getBusInstance() = mainBus

    fun getNetComponent(): NetComponent {
        return mNetComponent
    }
}
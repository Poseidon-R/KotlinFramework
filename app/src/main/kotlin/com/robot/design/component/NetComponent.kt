package com.robot.design.component

import com.robot.design.module.AppModule
import com.robot.design.module.NetModule
import com.robot.design.ui.MainActivity
import dagger.Component
import javax.inject.Singleton


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
@Singleton
@Component(modules = arrayOf(NetModule::class, AppModule::class))
interface NetComponent {

    fun inject(mainActivity: MainActivity)
}
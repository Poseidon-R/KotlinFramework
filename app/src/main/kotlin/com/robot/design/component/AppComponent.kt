package com.robot.design.component

import com.robot.design.App
import com.robot.design.module.AppModule
import com.robot.design.ui.DownloadActivity
import com.robot.design.viewmodel.DownloadViewModel
import dagger.Component
import dagger.Module
import javax.inject.Singleton


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/15
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: DownloadViewModel)
}
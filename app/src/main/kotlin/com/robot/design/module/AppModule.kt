package com.robot.design.module

import android.app.Application
import dagger.Module
import dagger.Provides
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
@Module
class AppModule(var application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }

}
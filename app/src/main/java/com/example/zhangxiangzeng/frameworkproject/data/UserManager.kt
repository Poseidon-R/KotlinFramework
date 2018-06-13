package com.example.zhangxiangzeng.frameworkproject.data

import com.blankj.utilcode.util.ToastUtils
import com.example.zhangxiangzeng.frameworkproject.App
import com.example.zhangxiangzeng.frameworkproject.rest.async
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/05/17
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
class UserManager private constructor() {

    @Inject
    lateinit var app: App

    private var user: User? = null

    init {
        val dao = UserDatabase.getInstance(app).userDao()
        dao.getUsers().async()
                .subscribeBy(
                        onSuccess = {
                            if (it.isEmpty()) return@subscribeBy
                            user = it[0]
                        },
                        onError = {}
                )
    }

    fun isLogin() = user != null

    fun getAccessToken() = user?.accessToken ?: ""

    fun getUser() = user

    fun clear() {
        val dao = UserDatabase.getInstance(app).userDao()
        Observable.create<Boolean> {
            dao.deleteAllUsers()
            it.onNext(true)
        }.async().doOnNext {
            userManager = null
        }.subscribeBy(
                onError = {
                    ToastUtils.showShort("删除失败！")
                }
        )
    }

    fun updateLoginInfo(user: User) {
        val dao = UserDatabase.getInstance(app).userDao()
        Observable.create<Boolean> {
            dao.insertUser(user)
            it.onNext(true)
        }.async().doOnNext { this.user = user }.subscribe()
    }

    companion object {
        private var userManager: UserManager? = null

        @JvmStatic
        fun getInstance(): UserManager {
            if (userManager == null) {
                synchronized(UserManager::class.java) {
                    if (userManager == null) {
                        userManager = UserManager()
                    }
                }
            }
            return userManager!!
        }
    }

}
package com.example.zhangxiangzeng.frameworkproject.data

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson


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
object UserConvert {

    @TypeConverter
    @JvmStatic
    fun formatPermissionJson(permission: PermissionGroup): String {
        return Gson().toJson(permission)
    }

    @TypeConverter
    @JvmStatic
    fun formatPermissionPojo(permission: String): PermissionGroup {
        return Gson().fromJson<PermissionGroup>(permission, PermissionGroup::class.java)
    }

}
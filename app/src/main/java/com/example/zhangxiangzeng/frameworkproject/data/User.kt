package com.example.zhangxiangzeng.frameworkproject.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/01/29
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
@Entity(tableName = "user")
data class User constructor(@PrimaryKey @ColumnInfo(name = "user_id") var userId: String) : Serializable {
    constructor() : this("0")

    @ColumnInfo(name = "accessToken")
    var accessToken: String = ""

    @ColumnInfo(name = "expiresIn")
    var expiresIn: Long = 0

    @ColumnInfo(name = "nickName")
    var nickName: String = ""

    @ColumnInfo(name = "phone")
    var phone: String = ""

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "permissionGroup")
    var permissionGroup: PermissionGroup? = null
}
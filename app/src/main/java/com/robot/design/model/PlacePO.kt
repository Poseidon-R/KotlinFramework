package com.robot.design.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable


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
@Entity(tableName = "place")
data class PlacePO constructor(@PrimaryKey @ColumnInfo(name = "id") var id:String) : Serializable {

    @ColumnInfo(name = "code")
    var code: String= ""

    @ColumnInfo(name = "name")
    var name: String = ""
}
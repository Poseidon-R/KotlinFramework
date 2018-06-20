package com.robot.lighting.rest.download

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable


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
@Entity(tableName = "download_info")
data class DownloadInfoPO(@PrimaryKey @ColumnInfo(name = "download_id") var downloadId: String):Serializable{
    constructor() : this("0")

    @ColumnInfo(name = "save_path")
    var savePath: String = ""

    @ColumnInfo(name = "url")
    var url: String = ""

    @ColumnInfo(name = "countLength")
    var countLength: Long = 0

    @ColumnInfo(name = "readLength")
    var readLenght: Long = 0

//    @ColumnInfo(name = "service")
//    var service: Service?=null
//    var listener: DownloadListener
//    var defaultTimeOut: Int
//    var state: DownloadState
}
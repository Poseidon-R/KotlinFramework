package com.example.zhangxiangzeng.frameworkproject.data

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
data class Permission(var permissionName: String, var permissionCode: String,
                      var pid: Long, var id: String) : Serializable
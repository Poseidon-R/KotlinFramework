package com.robot.design.data
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
data class PermissionGroup(var groupName: String, var groupCode: String,
                           var groupId: String, var permissionArray: List<Permission>) : Serializable
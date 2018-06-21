package com.robot.lighting.wifi

import org.json.JSONException
import org.json.JSONObject


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/21
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
class AccessPoint(var SSID: String) {

    var security = "" // 加密机制，取值见下面定义的常量
    var level: Int = 0 // 信号强度
    var levelGrade = 0 // 信号响度等级，用户显示给用户，取值0、1、2、3、4（5级）
    var configured = false // 已保存
    var networkId: Int = 0 // 已保存的网络有网络ID
    var connected = false // 已连接
    var ip: Int = 0
    var supplicantState = ""
    var detailedState = ""
    var status = ""

    fun toJSON(): JSONObject {
        val jo = JSONObject()
        try {
            jo.put("SSID", SSID)
            jo.put("level", level)
            jo.put("levelGrade", levelGrade)
            jo.put("security", security)
            jo.put("configured", configured)
            jo.put("networkId", networkId)
            jo.put("connected", connected)
            jo.put("ip", ip)
            jo.put("supplicantState", supplicantState)
            jo.put("detailedState", detailedState)
            jo.put("status", status)
        } catch (e: JSONException) {
            // won't happen here
        }
        return jo
    }

    override fun toString(): String {
        return toJSON().toString()
    }

    companion object {

        // 几种加密方式
        val SECURITY_NONE = "none"
        val SECURITY_WEP = "WEP"
        val SECURITY_WPA = "WPA"
    }
}
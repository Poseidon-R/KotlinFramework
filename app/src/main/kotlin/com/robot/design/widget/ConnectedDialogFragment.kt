package com.robot.design.widget

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robot.design.R
import com.robot.lighting.wifi.AccessPoint


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/25
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */

class ConnectedDialogFragment : DialogFragment() {

    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (inflater == null) return null

        rootView = inflater.inflate(R.layout.dialog_wifi_connected, container, false)

        val mWifi = arguments.getSerializable("wifi_item") as AccessPoint

        return rootView
    }

}
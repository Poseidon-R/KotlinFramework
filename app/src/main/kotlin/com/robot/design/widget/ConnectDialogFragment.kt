package com.robot.design.widget

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robot.design.R
import com.robot.design.databinding.DialogWifiConnectBinding
import com.robot.lighting.wifi.AccessPoint


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/22
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */

class ConnectDialogFragment : DialogFragment() {

    private lateinit var rootView: View
    private lateinit var dataBinding: DialogWifiConnectBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (inflater == null) return null
        rootView = inflater.inflate(R.layout.dialog_wifi_connect, container, false)
        dataBinding = DataBindingUtil.bind<DialogWifiConnectBinding>(rootView)
        val mWifi = arguments.getSerializable("wifi_item") as AccessPoint
        dataBinding.apply {
            wifiItem = mWifi
            cancel.setOnClickListener { dismiss() }
            connect.setOnClickListener {  }
        }

        return rootView
    }

}
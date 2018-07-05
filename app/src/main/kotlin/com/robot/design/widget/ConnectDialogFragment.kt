package com.robot.design.widget

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.view.ViewGroup
import com.robot.design.R
import com.robot.design.databinding.DialogWifiConnectBinding
import com.robot.lighting.wifi.AccessPoint
import android.view.Gravity
import android.app.Dialog
import android.util.DisplayMetrics
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog


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

//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        if (inflater == null) return null
//        rootView = inflater.inflate(R.layout.dialog_wifi_connect, container, false)
//        dataBinding = DataBindingUtil.bind<DialogWifiConnectBinding>(rootView)
//        val mWifi = arguments.getSerializable("wifi_item") as AccessPoint
//        dataBinding.apply {
//            wifiItem = mWifi
//            cancel.setOnClickListener { dismiss() }
//            connect.setOnClickListener {  }
//        }
//        return rootView
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity);
        // 设置主题的构造方法
        // AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        val inflater = activity.layoutInflater;
        rootView = inflater.inflate(R.layout.dialog_wifi_connect, null);
        dataBinding = DataBindingUtil.bind<DialogWifiConnectBinding>(rootView)
        val mWifi = arguments.getSerializable("wifi_item") as AccessPoint
        dataBinding.apply {
            wifiItem = mWifi
            cancel.setOnClickListener { dismiss() }
            connect.setOnClickListener { }
        }
        builder.setView(view)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
//        initWindows()
    }

    private fun initWindows() {
        val win = dialog.window
        // 一定要设置Background，如果不设置，window属性设置无效
        win!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.white
        )))

        val dm = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(dm)

        val params = win.attributes
        params.gravity = Gravity.BOTTOM
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        win.attributes = params
    }

}
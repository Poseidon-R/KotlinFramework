package com.robot.design.ui.adapter

import android.net.wifi.ScanResult
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.robot.design.R
import com.robot.design.widget.ConnectDialogFragment
import com.robot.lighting.wifi.AccessPoint


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
class WifiAdapter(layoutResId: Int, data: List<AccessPoint>?, onItemClickListener: (wifiItem: AccessPoint) -> Unit) : BaseQuickAdapter<AccessPoint, BaseViewHolder>(layoutResId, data) {

    init {
        setOnItemClickListener { adapter, view, position ->
            val wifiItems = getData()
            if (wifiItems.isEmpty()) return@setOnItemClickListener

            val wifiItem = wifiItems[position]
            if (!wifiItem.configured) return@setOnItemClickListener
            onItemClickListener(wifiItem)
        }
    }

    override fun convert(helper: BaseViewHolder?, item: AccessPoint?) {
        if (helper == null || item == null) return


        helper.setText(R.id.wifi_ssid, item.SSID)

        val icon = when (item.levelGrade) {
            1 -> R.drawable.wifi_level1
            2 -> R.drawable.wifi_level2
            3 -> R.drawable.wifi_level3
            4 -> R.drawable.wifi_level4
            else -> R.drawable.wifi_level1
        }

        helper.setVisible(R.id.wifit_state, item.connected)
        helper.setImageResource(R.id.wifi_level, icon)
    }

}
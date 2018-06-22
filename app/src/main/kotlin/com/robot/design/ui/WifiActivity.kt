package com.robot.design.ui

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.robot.design.R
import com.robot.design.databinding.ActivityWifiBinding
import com.robot.design.ext.obtainViewModel
import com.robot.design.ui.adapter.WifiAdapter
import com.robot.design.viewmodel.WifiViewModel
import com.robot.design.widget.ConnectDialogFragment
import com.robot.lighting.widget.DividerItemDecoration
import com.robot.lighting.wifi.AccessPoint


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/20
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
class WifiActivity : BaseActivity<ActivityWifiBinding>() {

    private val connectDialog = ConnectDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolBar()
        dataBinding.apply {
            viewModel = obtainViewModel(WifiViewModel::class.java)
            viewModel?.onStart()
        }
        setUpWifiList()
        setUpWifiSwitch()
    }

    private fun setUpWifiList() {
        val viewModel = dataBinding.viewModel ?: return

        dataBinding.wifiList.apply {
            layoutManager = LinearLayoutManager(this@WifiActivity)
            addItemDecoration(DividerItemDecoration(this@WifiActivity, DividerItemDecoration.VERTICAL_LIST,
                    resources.getDimension(R.dimen.dp_1).toInt(),
                    Color.parseColor("#f4f4f4")))
            adapter = WifiAdapter(R.layout.item_wifi, listOf<AccessPoint>()) { wifiItem: AccessPoint ->
                val bundle = Bundle()
                bundle.putSerializable("wifi_item", wifiItem)
                connectDialog.arguments = bundle
                connectDialog.show(supportFragmentManager, "")
            }

            viewModel.startSearchWifi()
        }
    }

    private fun setUpWifiSwitch() {
        val viewModel = dataBinding.viewModel ?: return

        dataBinding.switchComponent.apply {

            setOnCheckedChangeListener { compoundButton, b ->
                viewModel.onCheckedChange()
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.activity_wifi

    override fun getToolbarTitle(): String = "wifi管理"

    override fun getToolbar(): Toolbar = findViewById(R.id.toolbar)


}
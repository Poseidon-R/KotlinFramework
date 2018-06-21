package com.robot.design.ui

import android.databinding.ViewDataBinding
import android.net.wifi.ScanResult
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.robot.design.R
import com.robot.design.databinding.ActivityWifiBinding
import com.robot.design.ext.obtainViewModel
import com.robot.design.ui.adapter.WifiAdapter
import com.robot.design.viewmodel.WifiViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolBar()
        dataBinding.apply {
            viewModel = obtainViewModel(WifiViewModel::class.java)
            viewModel?.onStart()
            viewModel?.fetchWifiInfo()
        }
        setUpWifiList()
    }

    private fun setUpWifiList() {
        val viewModel = dataBinding.viewModel ?: return

        dataBinding.wifiList.apply {
            layoutManager = LinearLayoutManager(this@WifiActivity)
            adapter = WifiAdapter(R.layout.item_wifi, listOf<AccessPoint>())

            viewModel.startSearchWifi()
        }
    }

    override fun getLayoutRes(): Int = R.layout.activity_wifi

    override fun getToolbarTitle(): String = "wifi管理"

    override fun getToolbar(): Toolbar = findViewById(R.id.toolbar)


}
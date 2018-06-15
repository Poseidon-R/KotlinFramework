package com.robot.design.ui

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.robot.design.R
import com.robot.design.component.DaggerAppComponent
import com.robot.design.databinding.ActivityDownloadBinding
import com.robot.design.ext.obtainViewModel
import com.robot.design.module.AppModule
import com.robot.design.viewmodel.DownloadViewModel
import com.robot.lighting.rest.download.DownloadInfo
import com.robot.lighting.rest.download.DownloadState
import com.robot.lighting.utils.MainBus
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_download.*
import javax.inject.Inject


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/14
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
class DownloadActivity : BaseActivity<ActivityDownloadBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolBar()
        dataBinding.apply {
            viewModel = obtainViewModel(DownloadViewModel::class.java)
            download.setOnClickListener {
                viewModel?.doDownload()
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.activity_download

    override fun getToolbarTitle(): String = "下载"

    override fun getToolbar(): Toolbar = findViewById(R.id.toolbar)


}
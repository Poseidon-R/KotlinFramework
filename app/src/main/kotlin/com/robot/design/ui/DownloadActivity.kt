package com.robot.design.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.robot.design.R
import com.robot.design.databinding.ActivityDownloadBinding


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
    }

    override fun getLayoutRes(): Int = R.layout.activity_download

    override fun getToolbarTitle(): String = "下载"

    override fun getToolbar(): Toolbar = findViewById(R.id.toolbar)


}
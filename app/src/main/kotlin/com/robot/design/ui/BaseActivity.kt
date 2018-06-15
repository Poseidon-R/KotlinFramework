package com.robot.design.ui

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.robot.design.R
import com.robot.lighting.widget.snack.Prompt
import com.robot.lighting.widget.snack.TSnackbar


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/15
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var dataBinding: T
    lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView<T>(this, getLayoutRes())

        rootView = findViewById<View>(android.R.id.content).rootView

    }

    abstract fun getLayoutRes(): Int

    abstract fun getToolbarTitle(): String

    abstract fun getToolbar(): Toolbar

    fun initToolBar() = with(getToolbar()) {
        setNavigationIcon(R.mipmap.back_white)
        title = getToolbarTitle()
        setTitleTextColor(android.graphics.Color.WHITE)
        setBackgroundColor(resources.getColor(R.color.colorPrimary))
        setNavigationOnClickListener { finish() }
    }


    fun showSuccessSnackBar(msg: String) {
        TSnackbar.make(rootView, msg, TSnackbar.LENGTH_SHORT).setMessageTextColor(Color.WHITE)
                .setPromptThemBackground(Prompt.SUCCESS)
                .show()
    }

    fun showErrorSnackBar(msg: String) {
        TSnackbar.make(rootView, msg, TSnackbar.LENGTH_SHORT)
                .setPromptThemBackground(Prompt.ERROR)
                .show()
    }

    fun showWarningSnackBar(msg: String) {
        TSnackbar.make(rootView, msg, TSnackbar.LENGTH_SHORT)
                .setPromptThemBackground(Prompt.WARNING)
                .show()
    }
}
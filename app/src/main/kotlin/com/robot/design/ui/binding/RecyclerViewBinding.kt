package com.robot.design.ui.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter


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
object RecyclerViewBinding {

    @BindingAdapter("app:data")
    @JvmStatic
    fun bindDownloadState(recyclerView: RecyclerView, data: List<Any>?) {
        if (data == null) return

        val adapter = recyclerView.adapter

        if (adapter != null) {
            (adapter as BaseQuickAdapter<Any, *>).setNewData(data)
        }
    }
}
package com.robot.lighting.widget.adapter

import android.support.annotation.DrawableRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.robot.lighting.R


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/13
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */

class SheetItemAdapter(layoutResId: Int, data: MutableList<SheetItem>?) : BaseQuickAdapter<SheetItemAdapter.SheetItem, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder?, item: SheetItem?) {
        if (helper == null || item == null) return

        helper.setImageResource(R.id.sheet_icon, item.icon)
        helper.setText(R.id.sheet_name, item.name)
    }

    data class SheetItem(@DrawableRes var icon: Int, var name: String)

}
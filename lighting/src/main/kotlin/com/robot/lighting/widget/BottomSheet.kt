package com.robot.lighting.widget

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robot.lighting.R
import com.robot.lighting.widget.adapter.SheetItemAdapter


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
class BottomSheet : BottomSheetDialogFragment() {

    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (inflater == null) return null
        rootView = inflater.inflate(R.layout.material_bottom_sheets, container, false)
        initView()
        return rootView
    }

    private fun initView() {
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.menu)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val data = mutableListOf<SheetItemAdapter.SheetItem>(
                SheetItemAdapter.SheetItem(R.drawable.share, "SHARE"),
                SheetItemAdapter.SheetItem(R.drawable.copy, "COPY"),
                SheetItemAdapter.SheetItem(R.drawable.cloud_upload, "CLOUD_UPLOAD"),
                SheetItemAdapter.SheetItem(R.drawable.print, "PRINT")
        )
        recyclerView.adapter = SheetItemAdapter(R.layout.sheets_item, data)
    }
}
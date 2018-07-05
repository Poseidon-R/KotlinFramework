package com.robot.design.ui

import android.databinding.ViewDataBinding
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.robot.design.R
import kotlinx.android.synthetic.main.activity_chart.view.*
import com.robot.design.R.id.chart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.utils.ViewPortHandler
import com.github.mikephil.charting.formatter.IValueFormatter
import com.robot.design.utils.BarChartManager


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/7/5
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
class ChartActivity : BaseActivity<ViewDataBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_chart

    override fun getToolbarTitle(): String = "图标控件"

    override fun getToolbar(): Toolbar = findViewById(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolBar()
        initView()
    }

    private fun initView() {
        val chart = findViewById<BarChart>(R.id.chart)

        //设置x轴的数据
        val xValues = arrayListOf<Float>()
        for (i in 0..10) {
            xValues.add(i.toFloat())
        }

        //设置y轴的数据()
        val yValues = arrayListOf<List<Float>>()
        for (i in 0..3) {
            val yValue = arrayListOf<Float>()
            for (j in 0..10) {
                yValue.add((Math.random() * 80).toFloat())
            }
            yValues.add(yValue)
        }

        val barChartManager = BarChartManager(chart)
        barChartManager.setDescription("测试柱状图")
        barChartManager.showBarChart(xValues, yValues[0], "测试柱状图", Color.GREEN)
        barChartManager.setXAxis(11f, 0f, 11) { value, axis ->
            return@setXAxis "${value.toInt()}月"
        }
//        barChartManager.setYAxis(80f,0f,4){value, axis ->
//            return@setYAxis "${value.toInt()}元"
//        }
//        barChartManager.setLowLimitLine(15,"低收益")
    }


}
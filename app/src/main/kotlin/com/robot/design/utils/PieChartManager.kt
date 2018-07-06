package com.robot.design.utils

import android.graphics.Color
import android.support.annotation.ColorRes
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.PieDataSet
import com.robot.lighting.utils.ResUtils
import com.github.mikephil.charting.formatter.PercentFormatter




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

class PieChartManager(pieChart: PieChart) {

    private val mPieChart = pieChart
    private var pieData: PieData? = null
    private var dataSet: PieDataSet? = null

    private val defaultValueTextSize = 12f

    private fun init() {
        mPieChart.setBackgroundColor(Color.WHITE)
    }

    fun showPieChart(strings: List<PieEntry>, label: String, colors: List<Int>) {
        init()
        dataSet = PieDataSet(strings, label)

        dataSet!!.colors = colors

        pieData = PieData(dataSet)
        pieData!!.apply {
            setDrawValues(true)
            setValueTextSize(defaultValueTextSize)
            setDrawValues(true)
            setValueFormatter(PercentFormatter())
        }
        mPieChart.data = pieData
        mPieChart.invalidate()
    }

    fun setValueTextSize(textSize: Float) {
        pieData?.setValueTextSize(textSize)
        mPieChart.invalidate()
    }

    fun setValueTextColor(@ColorRes textColor: Int) {
        pieData?.setValueTextColor(textColor)
        mPieChart.invalidate()
    }

    fun setValueTextColor(@ColorRes textColors: List<Int>) {
        val mTextColors = textColors.map {
            ResUtils.getColor(it)
        }
        pieData?.setValueTextColors(mTextColors)
        mPieChart.invalidate()
    }

    /**
     * 设置为实心圆
     */
    fun setSolidPie() {
        mPieChart.holeRadius = 0f
        mPieChart.transparentCircleRadius = 0f
        mPieChart.invalidate()
    }
}
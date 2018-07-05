package com.robot.design.utils

import android.graphics.Color
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import android.databinding.adapters.TextViewBindingAdapter.setTextSize
import android.databinding.adapters.TextViewBindingAdapter.setText
import com.github.mikephil.charting.components.*


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
class BarChartManager(barChart: BarChart) {

    private val mBarChart: BarChart = barChart

    private val leftAxis: YAxis = barChart.axisLeft
    private val rightAxis: YAxis = barChart.axisRight
    private val xAxis: XAxis = barChart.xAxis

    private val backGroundColor = Color.WHITE

    private fun init() {
        mBarChart.setBackgroundColor(backGroundColor)
        mBarChart.setDrawGridBackground(false)
        mBarChart.setDrawBarShadow(false)
        mBarChart.isHighlightFullBarEnabled = false
        mBarChart.setDrawBorders(false)
        mBarChart.animateX(1000, Easing.EasingOption.Linear)
        mBarChart.animateY(1000, Easing.EasingOption.Linear)

        val legend = mBarChart.legend
        legend.form = Legend.LegendForm.LINE
        legend.textSize = 11f
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)

        xAxis.position = XAxis.XAxisPosition.BOTTOM;
        xAxis.granularity = 1f;
        leftAxis.axisMinimum = 0f;
        rightAxis.axisMinimum = 0f;
    }

    /**
     * 显示柱状图
     * @param xAxisValues x轴点
     * @param yAxisValues y轴点
     * @param label 柱状体Label描述
     * @param color 柱状体颜色
     */
    fun showBarChart(xAxisValues: List<Float>, yAxisValues: List<Float>, label: String, color: Int) {
        init()
        val entries = arrayListOf<BarEntry>()

        for (i in 0 until xAxisValues.size) {
            entries.add(BarEntry(xAxisValues.get(i), yAxisValues.get(i)))
        }

        val barDataSet = BarDataSet(entries, label)
        barDataSet.color = color;
        barDataSet.valueTextSize = 9f;
        barDataSet.formLineWidth = 1f;
        barDataSet.formSize = 15f;

        val dataSets = arrayListOf<IBarDataSet>()
        dataSets.add(barDataSet)
        val data = BarData(dataSets)
        data.barWidth = 1f

        xAxis.setLabelCount(xAxisValues.size - 1, false);
        xAxis.gridColor = backGroundColor
        leftAxis.gridColor = backGroundColor
        rightAxis.gridColor = backGroundColor
        mBarChart.data = data;
        mBarChart.invalidate()
    }

    /**
     * 设置Y轴值
     * @param max 最大值
     * @param min 最小值
     * @param labelCount label数量
     */
    fun setYAxis(max: Float, min: Float, labelCount: Int, format: (value: Float, axisBase: AxisBase) -> String) {
        if (max < min) {
            return
        }
        leftAxis.axisMaximum = max
        leftAxis.axisMinimum = min
        leftAxis.setLabelCount(labelCount, false)
        leftAxis.setValueFormatter(format)

        rightAxis.axisMaximum = max
        rightAxis.axisMinimum = min
        rightAxis.setLabelCount(labelCount, false)
        rightAxis.setValueFormatter(format)
        mBarChart.invalidate()
    }

    /**
     * 设置X轴值
     * @param max 最大值
     * @param min 最小值
     * @param labelCount label数量
     */
    fun setXAxis(max: Float, min: Float, labelCount: Int, format: (value: Float, axisBase: AxisBase) -> String) {
        xAxis.axisMaximum = max
        xAxis.axisMinimum = min
        xAxis.setLabelCount(labelCount, false)
        xAxis.setValueFormatter(format)
        mBarChart.invalidate()
    }

    /**
     * 设置低限制线
     */
    fun setLowLimitLine(low: Int, name: String?) {
        val mName = name ?: "低限制线"
        val heightLimit = LimitLine(low.toFloat(), mName)
        heightLimit.lineWidth = 2f
        heightLimit.lineColor = Color.DKGRAY
        heightLimit.textSize = 10f
        leftAxis.addLimitLine(heightLimit)
        mBarChart.invalidate()
    }

    /**
     * 设置描述信息
     *
     * @param str 描述信息
     */
    fun setDescription(str: String) {
        val description = Description()
        description.text = str
        mBarChart.description = description
        mBarChart.invalidate()
    }
}
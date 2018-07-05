package com.robot.lighting.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/26
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
class CustomView : View {

    constructor(context: Context) : super(context){

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet){}

    constructor(context: Context, attributeSet: AttributeSet, style: Int) : super(context, attributeSet, style)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}
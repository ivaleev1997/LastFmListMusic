package com.lastfmlistmusic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import com.lastfmlistmusic.R
import com.lastfmlistmusic.view.data.AnimationValue
import com.lastfmlistmusic.view.data.DrawData

class Drawer(context: Context) {
    private val linePaint: Paint = Paint()
    private val strokePaint: Paint = Paint()
    private var animationValue: AnimationValue? = null
    var drawDataList: List<DrawData> = mutableListOf()

    init {
        linePaint.strokeWidth = context.resources.getDimension(R.dimen.line_width)
        linePaint.isAntiAlias = false
        linePaint.color = context.resources.getColor(R.color.colorCurveLine)

        strokePaint.strokeWidth = context.resources.getDimension(R.dimen.line_width)
        strokePaint.style = Paint.Style.STROKE
        strokePaint.isAntiAlias = true
        strokePaint.color = context.resources.getColor(R.color.colorCurveLine)
    }

    fun draw(canvas: Canvas?) {
        if (drawDataList.isNotEmpty() && canvas != null) {

            drawLine(canvas)
        }
    }

    private fun drawLine(canvas: Canvas) {
        val currentRunningPos = animationValue?.currentAnimationPosition ?: AnimationManager.NONE_VALUE
        for (i in 0 until currentRunningPos) {
            drawLine(canvas, i, false)
        }

        if (currentRunningPos > AnimationManager.NONE_VALUE)
            drawLine(canvas, currentRunningPos, true)
    }

    private fun drawLine(canvas: Canvas, position: Int, isAnimation: Boolean) {
        val startX = drawDataList[position].startX
        val startY = drawDataList[position].startY
        val stopX = if (isAnimation) animationValue!!.x else drawDataList[position].stopX
        val stopY = if (isAnimation) animationValue!!.y else drawDataList[position].stopY

        //println("drawChart isAnimation: $isAnimation")
        canvas.drawLine(startX, startY, stopX, stopY, linePaint)

        if (position > 0 && position < drawDataList.size - 1)
            canvas.drawCircle(startX, startY, 0.5f, strokePaint)
    }

    fun updateAnimationValue(value: AnimationValue) {
        animationValue = value
    }
}
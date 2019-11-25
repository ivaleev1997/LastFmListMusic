package com.lastfmlistmusic.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.lastfmlistmusic.R
import com.lastfmlistmusic.view.data.DrawData

class CurveLfView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet), CurveLineManager.AnimationListener {
    private lateinit var drawDataList: List<DrawData>
    private val curveLineManager = CurveLineManager(context, this)
    private var isInverse = checkInverse(attributeSet)
    private var viewHeight = 0
    private var viewWidth = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        viewHeight = MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        curveLineManager.drawer.draw(canvas)
    }

    fun checkInverse(attrs: AttributeSet): Boolean {
        val result: Boolean
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CurveLfView,
            0, 0)
        result = typedArray.getBoolean(R.styleable.CurveLfView_inverse, false)

        return result
    }

    override fun animationUpdate() {
        invalidate()
    }

    fun start() {
        post {
            initDrawDataList()
            curveLineManager.drawAnimation(drawDataList)
        }
    }

    fun initDrawDataList() {
        drawDataList = createDrawDataList(viewWidth, viewHeight, isInverse)
    }
}
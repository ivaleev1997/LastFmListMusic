package com.education.songslist.customView

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.education.songslist.R
import com.education.songslist.customView.data.DrawData

class CurveLfView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet), CurveLineManager.AnimationListener {
    private lateinit var drawDataList: List<DrawData>
    private val curveLineManager: CurveLineManager

    private var isInverse: Boolean = false
    private var viewHeight = 0
    private var viewWidth = 0
    private var animationDuration = 200L

    init {
        getSetupedAttributes(attributeSet)
        curveLineManager = CurveLineManager(context, animationDuration, this)
    }

    private fun getSetupedAttributes(attrs: AttributeSet) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CurveLfView,
            0, 0)
        isInverse = typedArray.getBoolean(R.styleable.CurveLfView_inverse, false)

        animationDuration = typedArray.getInt(R.styleable.CurveLfView_duration, 200).toLong()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        viewHeight = MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        curveLineManager.drawer.draw(canvas)
    }


    override fun animationUpdate() {
        invalidate()
    }

    private fun initDrawDataList() {
        drawDataList = createDrawDataList(viewWidth, viewHeight, isInverse)
    }

    fun start() {
        post {
            initDrawDataList()
            curveLineManager.drawAnimation(drawDataList)
        }
    }
}
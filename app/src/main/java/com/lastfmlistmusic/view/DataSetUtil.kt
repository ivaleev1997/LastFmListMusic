package com.lastfmlistmusic.view

import com.lastfmlistmusic.view.data.DrawData

const val X_STEP_PERCENT = 0.09
const val REDUCER = 0.8

fun createDrawDataList(width: Int, height: Int, inverse: Boolean): List<DrawData> {
    val resultList = mutableListOf<DrawData>()
    val center = height/2
    var startX = if(inverse) 0f else width.toFloat()
    var startY = center
    var stopX = if(inverse) width - getStepX(width.toFloat()) else getStepX(startX)
    var stopY = 0
    val lastX = if (inverse) width else 0
    var tmp: Float
    val extremumList = getExtremumList(startY)

    resultList.add(DrawData(startX, startY.toFloat(), stopX, stopY.toFloat()))

    for((index, element) in extremumList.withIndex()) {
        //startX = stopX

        if (stopX < 0 || stopX > width) break
        tmp = stopX
        stopX = if (inverse) {
            getStepXInverse(startX, stopX)
        }
        else {
            getStepX(stopX)
        }
        startX = tmp

        if (stopX <= 0 || stopX > width) break

        startY = stopY

        stopY = if ( index.rem(2) == 0)
            center + element
        else
            center - element

        resultList.add(DrawData(startX, startY.toFloat(), stopX, stopY.toFloat()))
    }
    if (startX > 0)
        resultList.add(DrawData(startX, center.toFloat(), lastX.toFloat(), center.toFloat()))

    return resultList
}


private fun getStepX(width: Float): Float {
    return (width - (width * X_STEP_PERCENT)).toFloat()
}

private fun getStepXInverse(start: Float, stop: Float): Float {
    return ((stop + (stop - start) * (1 - X_STEP_PERCENT)).toFloat())
}

fun getExtremumList(height: Int): List<Int> {
    var y = (height * REDUCER).toInt()
    val resultList = mutableListOf<Int>()
    while(y > 1) {
        resultList.add(y)
        y = (y * REDUCER).toInt()
    }

    resultList.add(0)

    return resultList
}
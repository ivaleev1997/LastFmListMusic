package com.lastfmlistmusic.view

import android.content.Context
import com.lastfmlistmusic.view.data.AnimationValue
import com.lastfmlistmusic.view.data.DrawData

class CurveLineManager(context: Context, private val animationListener: AnimationListener): AnimationManager.AnimationListener {

    private val animationManager = AnimationManager(this)
    val drawer = Drawer(context)

    fun drawAnimation(drawDataList: List<DrawData>) {
        if (drawDataList.isNotEmpty()) {
            drawer.drawDataList = drawDataList
            animationManager.animate(drawDataList)
        }
    }

    interface AnimationListener {
        fun animationUpdate()
    }

    override fun animationUpdate(animationValue: AnimationValue) {
        drawer.updateAnimationValue(animationValue)
        animationListener.animationUpdate()
    }
}
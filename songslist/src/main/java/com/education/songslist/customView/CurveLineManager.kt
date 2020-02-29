package com.education.songslist.customView


import android.content.Context
import com.education.songslist.customView.data.AnimationValue
import com.education.songslist.customView.data.DrawData

class CurveLineManager(context: Context, private val animationDuration: Long , private val animationListener: AnimationListener): AnimationManager.AnimationListener {

    private val animationManager = AnimationManager(this, animationDuration)
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
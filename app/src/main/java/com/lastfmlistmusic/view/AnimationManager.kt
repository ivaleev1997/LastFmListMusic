package com.lastfmlistmusic.view

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import com.lastfmlistmusic.view.data.AnimationValue
import com.lastfmlistmusic.view.data.DrawData

class AnimationManager(val animationListener: AnimationListener) {

    private val ANIMATION_DURATION = 200L
    private val PROPERTY_X = "propertyX"
    private val PROPERTY_Y = "propertyY"
    private val interpolator = AccelerateDecelerateInterpolator()
    private val animatorSet: AnimatorSet = AnimatorSet()
    private lateinit var lastValue: AnimationValue
    companion object {
        const val NONE_VALUE = -1
    }

    fun animate(drawList: List<DrawData>) {
        //TODO create animator list
        animatorSet.playSequentially(createAnimatorList(drawList))
        animatorSet.start()
    }

    private fun createAnimatorList(drawList: List<DrawData>): List<Animator> {
        val animatorList = mutableListOf<Animator>()
        drawList.forEach {
            animatorList.add(createValueAnimator(it))
        }

        return animatorList
    }

    private fun createValueAnimator(drawData: DrawData): ValueAnimator {
        val propertyX = PropertyValuesHolder.ofFloat(PROPERTY_X, drawData.startX, drawData.stopX)
        val propertyY = PropertyValuesHolder.ofFloat(PROPERTY_Y, drawData.startY, drawData.stopY)

        return ValueAnimator().apply {
            setValues(propertyX, propertyY)
            duration = ANIMATION_DURATION
            interpolator = this@AnimationManager.interpolator
            addUpdateListener {
                onAnimatorUpdate(it)
            }
        }
    }

    private fun onAnimatorUpdate(valueAnimator: ValueAnimator?) {
        //save coordinates from valueAnimator
        if (valueAnimator == null)
            return
        lastValue = AnimationValue(
            valueAnimator.getAnimatedValue(PROPERTY_X) as Float,
            valueAnimator.getAnimatedValue(PROPERTY_Y) as Float,
            getCurrentAnimationPosition()
        )

        animationListener.animationUpdate(lastValue)
    }

    private fun getCurrentAnimationPosition(): Int {
        val childAnimatorSet = animatorSet.childAnimations
        for ((index, child) in childAnimatorSet.withIndex()) {
            if (child.isRunning)
                return index
        }

        return NONE_VALUE
    }

    interface AnimationListener {
        fun animationUpdate(animationValue: AnimationValue)
    }

}
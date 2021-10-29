package com.lege.android.base.util.animate

import android.animation.ObjectAnimator
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout

/**
 * Description:
 * Created by loctek on 2020/12/8.
 */
fun hide(v: View?, duration: Long, isGoneAfterAnim: Boolean = false) {
    if (v == null) {
        return
    }
    val alphaAnimation = AlphaAnimation(1f, 0f)
    alphaAnimation.duration = duration
    alphaAnimation.interpolator = LinearInterpolator()
    alphaAnimation.fillAfter = false
    alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            if (isGoneAfterAnim) {
                v.visibility = View.GONE
            } else {
                v.visibility = View.INVISIBLE
            }
        }

        override fun onAnimationStart(animation: Animation?) {
        }

    })
    v.startAnimation(alphaAnimation)
}

fun show(v: View?, duration: Long) {
    if (v == null) {
        return
    }
    val alphaAnimation = AlphaAnimation(0f, 1f)
    alphaAnimation.duration = duration
    alphaAnimation.interpolator = LinearInterpolator()
    alphaAnimation.fillAfter = false
    v.startAnimation(alphaAnimation)
    v.visibility = View.VISIBLE
}

fun translate(v: View?, duration: Long, from: Float, to: Float) {
    v?.let {
        val anim = ObjectAnimator.ofFloat(0f, to - from)
        anim.duration = duration
        anim.interpolator = DecelerateInterpolator()
        val lp = when (it.layoutParams) {
            is LinearLayout.LayoutParams -> {
                it.layoutParams as LinearLayout.LayoutParams
            }
            is ConstraintLayout.LayoutParams -> {
                it.layoutParams as ConstraintLayout.LayoutParams
            }
            is RelativeLayout.LayoutParams -> {
                it.layoutParams as RelativeLayout.LayoutParams
            }
            is FrameLayout.LayoutParams -> {
                it.layoutParams as FrameLayout.LayoutParams
            }
            else -> {
                null

            }
        }
        anim.addUpdateListener { value ->
            lp?.topMargin = (value.animatedValue as Float).toInt()
            it.layoutParams = lp
        }
        anim.start()

    }


}
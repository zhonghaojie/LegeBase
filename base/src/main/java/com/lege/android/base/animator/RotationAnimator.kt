package com.lege.android.base.animator

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * Description:
 * Created by loctek on 2020/11/4.
 */
class RotationAnimator {
    private var mObjectAnimator: ObjectAnimator? = null
    private var mCurrentPlayTime: Long=-1
    /**
     * 设置旋转的动画
     */
    fun bindView(view: View): RotationAnimator {
        mObjectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, -360f)
        mObjectAnimator?.setInterpolator(LinearInterpolator())
        mObjectAnimator?.setRepeatCount(ValueAnimator.INFINITE)
        return this
    }

    /**
     * 暂停旋转
     */
    fun stopAnimation() {
        mObjectAnimator?.let {

            if (it.isStarted || it.isRunning) {

                mCurrentPlayTime = it.currentPlayTime
                it.cancel()
            }
        }
    }
    fun resetAnimator(){
        mObjectAnimator?.let {
                mCurrentPlayTime = 0
                it.cancel()
        }
    }

    /**
     * 开始旋转
     */
    fun startAnimation() {
        mObjectAnimator?.let {

            if (it.isStarted || it.isRunning) {
                return
            }

            it.start()
            it.setCurrentPlayTime(mCurrentPlayTime)
        }
    }
    fun setDuration(duration: Long): RotationAnimator {
        mObjectAnimator?.setDuration(duration.toLong())
        return this
    }

    fun setInterpolator(value: TimeInterpolator): RotationAnimator {
        mObjectAnimator?.setInterpolator(value)
        return this
    }

    fun setRepeat(repeat: Int): RotationAnimator {
        mObjectAnimator?.setRepeatCount(repeat)
        return this
    }
}
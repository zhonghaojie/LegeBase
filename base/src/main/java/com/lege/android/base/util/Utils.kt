package com.lege.android.base.util

import android.view.View

/**
 * Description:
 * Created by loctek on 2020/11/10.
 */
fun View.disable(isDisable: Boolean) {
    isEnabled = !isDisable
    alpha = if (isDisable) {
        0.7f
    } else {
        1f
    }
}
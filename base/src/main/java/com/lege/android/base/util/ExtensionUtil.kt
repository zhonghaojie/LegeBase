package com.lege.android.base.util

import com.lege.legecommonview.pagecontrol.BackView
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase

/**
 * Description:
 * Created by loctek on 2021/6/3.
 */
fun SwipeBackActivityBase.initBackView(backView: BackView) {
    swipeBackLayout.addSwipeListener(object : SwipeBackLayout.SwipeListener {
        override fun onScrollStateChange(state: Int, scrollPercent: Float) {
            if (state == 1) {
                backView.onExpand()
            } else {
                backView.onClose()
            }
        }

        override fun onEdgeTouch(edgeFlag: Int) {
        }

        override fun onScrollOverThreshold() {
        }

    })
}
package com.lege.android.base.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lege.android.base.R
import com.lege.android.base.util.disable
import java.util.*

/**
 * Description:
 * Created by zhonghaojie on 2019-11-08.
 */
object DialogUtil {


    @JvmStatic
    fun showConfirmDialog(
        context: Context,
        title: String,
        content: String = "",
        cancelText: String = "取消",
        okText: String = "确定",
        onCancel: (d: DialogInterface) -> Unit,
        onOk: (d: DialogInterface) -> Unit,
        onDismiss: (() -> Unit)? = null, isGoneTopIcon: Boolean = false, topIcon: Int = -1
    ) {
        val dialogview = LayoutInflater.from(context).inflate(R.layout.layout_setting_popup, null)
        val width = context.resources.getDimensionPixelOffset(R.dimen.x620)
        val height = context.resources.getDimensionPixelOffset(R.dimen.x340)
        dialogview?.let {
            val tvTitle = it.findViewById<TextView>(R.id.tv_title)
            val tvContent = it.findViewById<TextView>(R.id.tv_content)
            val ivTopIcon = it.findViewById<ImageView>(R.id.img_setup)
            if (isGoneTopIcon) {
                ivTopIcon.visibility = View.GONE
            } else {
                if (topIcon != -1) {
                    ivTopIcon.setImageResource(topIcon)
                }
                ivTopIcon.visibility = View.VISIBLE
            }
            tvTitle.text = title
            tvContent.text = content
            if (content.isEmpty()) {
                tvContent.visibility = View.GONE
            }
            val canclebtn = it.findViewById<TextView>(R.id.tv_refuse)
            canclebtn.text = cancelText
            val btnOk = it.findViewById<TextView>(R.id.tv_allow)
            btnOk.text = okText
            val dialog = AlertDialog.Builder(context)
                .setView(dialogview)
                .setCancelable(true)
                .create()
            dialog.setOnDismissListener {
                onDismiss?.invoke()
            }
            canclebtn.setOnClickListener {
                dialog.dismiss()
                onCancel.invoke(dialog)
            }
            btnOk.setOnClickListener {
                dialog.dismiss()
                onOk.invoke(dialog)
            }

            val window = dialog.window
            val lp = window.attributes
            lp.width = width
            lp.width = height
            lp.windowAnimations = R.style.statusbar_pop_animation
            lp.dimAmount = 0.75f
            window.attributes = lp
            window.setBackgroundDrawableResource(R.color.color_lock_bg)
            dialog.show()
        }
    }
    @JvmStatic
    fun showConfirmDialog(context: Context,
                          title: String,
                          content: String = "",
                          cancelText: String = "取消",
                          okText: String = "确定",
                          onCancel: (d: DialogInterface) -> Unit,
                          onOk: (d: DialogInterface) -> Unit,
                          onDismiss: (() -> Unit)? = null, isGoneTopIcon: Boolean = false) {
        val dialogview = LayoutInflater.from(context).inflate(R.layout.layout_setting_popup, null)
        val width = context.resources.getDimensionPixelOffset(R.dimen.x620)
        val height = context.resources.getDimensionPixelOffset(R.dimen.x340)
        dialogview?.let {
            val tvTitle = it.findViewById<TextView>(R.id.tv_title)
            val tvContent = it.findViewById<TextView>(R.id.tv_content)
            val ivTopIcon = it.findViewById<ImageView>(R.id.img_setup)
            if (isGoneTopIcon) {
                ivTopIcon.visibility = View.GONE
            } else {
                ivTopIcon.visibility = View.VISIBLE
            }
            tvTitle.text = title
            tvContent.text = content
            if (content.isEmpty()) {
                tvContent.visibility = View.GONE
            }
            val canclebtn = it.findViewById<TextView>(R.id.tv_refuse)
            canclebtn.text = cancelText
            val btnOk = it.findViewById<TextView>(R.id.tv_allow)
            btnOk.text = okText
            val dialog = AlertDialog.Builder(context)
                    .setView(dialogview)
                    .setCancelable(true)
                    .create()
            dialog.setOnDismissListener {
                onDismiss?.invoke()
            }
            canclebtn.setOnClickListener {
                dialog.dismiss()
                onCancel.invoke(dialog)
            }
            btnOk.setOnClickListener {
                dialog.dismiss()
                onOk.invoke(dialog)
            }

            val window = dialog.window
            val lp = window.attributes
            lp.width = width
            lp.width = height
            lp.windowAnimations = R.style.statusbar_pop_animation
            lp.dimAmount = 0.75f
            window.attributes = lp
            window.setBackgroundDrawableResource(R.color.color_lock_bg)
            dialog.show()
        }
    }

    @JvmStatic
    fun showCountDownDialog(context: Context,
                          title: String,
                          content: String, numCont:Int,
                          okText: String = "确定",
                          onOk: (d: DialogInterface) -> Unit,
                          onDismiss: (() -> Unit)? = null) {
        val dialogview = LayoutInflater.from(context).inflate(R.layout.layout_setting_popup, null)
        val width = context.resources.getDimensionPixelOffset(R.dimen.x620)
        val height = context.resources.getDimensionPixelOffset(R.dimen.x340)
        dialogview?.let {
            val tvTitle = it.findViewById<TextView>(R.id.tv_title)
            val tvContent = it.findViewById<TextView>(R.id.tv_content)
            val ivTopIcon = it.findViewById<ImageView>(R.id.img_setup)
            ivTopIcon.setImageResource(R.drawable.player_pop_ico)
            tvTitle.text = title
            tvContent.text = content
            val canclebtn = it.findViewById<TextView>(R.id.tv_refuse)
            canclebtn.visibility = View.GONE
            val btnOk = it.findViewById<TextView>(R.id.tv_allow)
            val dialog = AlertDialog.Builder(context)
                    .setView(dialogview)
                    .setCancelable(true)
                    .create()
            dialog.setOnDismissListener {
                onDismiss?.invoke()
            }
            btnOk.setOnClickListener {
                dialog.dismiss()
                onOk.invoke(dialog)
            }
            val timer = Timer()
            var a=numCont
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    if (a>0){
                        btnOk.text = "$okText（${a}S）"
                        a--
                    }else{
                        dialog.dismiss()
                        onOk.invoke(dialog)
                    }
                }
            }, 0, 2000)

            val window = dialog.window
            val lp = window.attributes
            lp.gravity= Gravity.CENTER
            lp.width = width
            lp.width = height
            lp.windowAnimations = R.style.statusbar_pop_animation
            lp.dimAmount = 0.75f
            window.attributes = lp
            window.setBackgroundDrawableResource(R.color.color_lock_bg)
            dialog.show()
        }
    }


    @JvmStatic
    fun showCountDownDialogV2(context: Context,
                            title: String,
                            content: String, numCont:Int,
                            okText: String = "确定",
                            onOk: (d: DialogInterface) -> Unit,
                            onDismiss: (() -> Unit)? = null,
                            onTimer: ((btn: TextView,tvContent:TextView,timer: Timer) -> Unit)? = null) {
        val dialogview = LayoutInflater.from(context).inflate(R.layout.layout_setting_popup, null)
        val width = context.resources.getDimensionPixelOffset(R.dimen.x620)
        val height = context.resources.getDimensionPixelOffset(R.dimen.x340)
        dialogview?.let {
            val tvTitle = it.findViewById<TextView>(R.id.tv_title)
            val tvContent = it.findViewById<TextView>(R.id.tv_content)
            val ivTopIcon = it.findViewById<ImageView>(R.id.img_setup)
            ivTopIcon.setImageResource(R.drawable.player_pop_ico)
            tvTitle.text = title
            tvContent.text = content
            val canclebtn = it.findViewById<TextView>(R.id.tv_refuse)
            canclebtn.visibility = View.GONE
            val btnOk = it.findViewById<TextView>(R.id.tv_allow)
            val dialog = AlertDialog.Builder(context)
                    .setView(dialogview)
                    .setCancelable(false)
                    .create()
            dialog.setOnDismissListener {
                onDismiss?.invoke()
            }
            btnOk.setOnClickListener {
                dialog.dismiss()
                onOk.invoke(dialog)
            }
            btnOk.disable(true)
            val timer = Timer()
            var a=numCont
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    if (a>0){
                        btnOk.text = "$okText（${a}S）"
                        a--
                    }else{
                        btnOk.disable(false)
                        btnOk.text = "超时失败"
                    }

                    onTimer?.invoke(btnOk,tvContent, timer)
                }
            }, 0, 1000)

            val window = dialog.window
            val lp = window.attributes
            lp.gravity= Gravity.CENTER
            lp.width = width
            lp.width = height
            lp.windowAnimations = R.style.statusbar_pop_animation
            lp.dimAmount = 0.75f
            window.attributes = lp
            window.setBackgroundDrawableResource(R.color.color_lock_bg)
            dialog.show()
        }
    }
    //儿童番茄的确认弹窗
    fun showConfirmDialogForChildrenTomato(context: Context,
                          title: String,
                          content: String = "",
                          cancelText: String = "取消",
                          okText: String = "确定",
                          onCancel: (d: DialogInterface) -> Unit,
                          onOk: (d: DialogInterface) -> Unit,
                          onDismiss: (() -> Unit)? = null, isGoneTopIcon: Boolean = false) {
        val dialogview = LayoutInflater.from(context).inflate(R.layout.dialog_children_confirm, null)
        val width = context.resources.getDimensionPixelOffset(R.dimen.x620)
        val height = context.resources.getDimensionPixelOffset(R.dimen.x340)
        dialogview?.let {
            val tvTitle = it.findViewById<TextView>(R.id.tv_title)
            val tvContent = it.findViewById<TextView>(R.id.tv_content)
            val ivTopIcon = it.findViewById<ImageView>(R.id.img_setup)
            if (isGoneTopIcon) {
                ivTopIcon.visibility = View.GONE
            } else {
                ivTopIcon.visibility = View.VISIBLE
            }
            tvTitle.text = title
            tvContent.text = content
            if (content.isEmpty()) {
                tvContent.visibility = View.GONE
            }
            val canclebtn = it.findViewById<TextView>(R.id.tv_refuse)
            canclebtn.text = cancelText
            val btnOk = it.findViewById<TextView>(R.id.tv_allow)
            btnOk.text = okText
            val dialog = AlertDialog.Builder(context)
                .setView(dialogview)
                .setCancelable(true)
                .create()
            dialog.setOnDismissListener {
                onDismiss?.invoke()
            }
            canclebtn.setOnClickListener {
                dialog.dismiss()
                onCancel.invoke(dialog)
            }
            btnOk.setOnClickListener {
                dialog.dismiss()
                onOk.invoke(dialog)
            }

            val window = dialog.window
            val lp = window.attributes
            lp.width = width
            lp.width = height
            lp.windowAnimations = R.style.statusbar_pop_animation
            lp.dimAmount = 0.75f
            window.attributes = lp
            window.setBackgroundDrawableResource(R.color.color_lock_bg)
            dialog.show()
        }
    }
}
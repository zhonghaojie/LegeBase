package com.lege.android.base.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Description:
 * Created by zhonghaojie on 2019-07-04.
 */


abstract class BaseAdapter<T>(var datas: MutableList<T>, protected val context: Context) : RecyclerView.Adapter<BaseViewHolder>() {
    interface OnItemClickListener<T> {
        fun onItemClick(position: Int, data: T)
    }
    var onItemClickListener: OnItemClickListener<T>? = null
    abstract fun layoutRes(): Int
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val v = LayoutInflater.from(context).inflate(layoutRes(), parent, false)
        return BaseViewHolder(v)
    }

    fun update(list: List<T>) {
        datas = list as MutableList<T>
        notifyDataSetChanged()
    }

    fun setOnItemClickLs(ls: OnItemClickListener<T>){
         onItemClickListener = ls
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder?.let {
            bindData(it, datas[it.adapterPosition], it.adapterPosition)
            it.view.setOnClickListener {v->
                onItemClickListener?.onItemClick(it.layoutPosition, datas[it.layoutPosition])
            }
        }

    }

    abstract fun bindData(holder: BaseViewHolder, data: T, position: Int)
}

class BaseViewHolder(val view: View) : RecyclerView.ViewHolder(view){
}
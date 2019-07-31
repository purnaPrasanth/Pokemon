package com.purna.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * A Handy RecyclerView Adapter for the case of Single ViewHolder Type
 * This class greatly reduces the boiler plate code for a [RecyclerView.Adapter] with single type of [RecyclerView.ViewHolder]
 * @param BINDING the of view binding that this [RecyclerView.Adapter] uses
 * @param layoutId the layout id for creating ViewHolder in [onCreateViewHolder]
 *
 * This uses [AsyncListDiffer] for calculating changes between successive lists give to this adapter in [setData]
 * @property onItemClickListener listener for item clicks
 * @property onItemLongClickListener listener for item long clicks
 */

abstract class PaginatedSingleTypeBaseRvAdapter<BINDING : ViewDataBinding, DATA>(
    val mContext: Context, @LayoutRes val layoutId: Int,
    diffCallback: DiffUtil.ItemCallback<DATA>
) : PagedListAdapter<DATA, BaseHolder<BINDING>>(diffCallback) {
    var onItemClickListener: AdapterView.OnItemClickListener? = null
    var onItemLongClickListener: AdapterView.OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<BINDING> {
        val view =
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BaseHolder(view)
    }

    override fun onBindViewHolder(holder: BaseHolder<BINDING>, position: Int) {
        onBindViewHolder(holder.binding(), position)
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener { v ->
                onItemClickListener?.onItemClick(
                    null,
                    v,
                    holder.adapterPosition,
                    holder.itemId
                )
            }
        }

        holder.itemView.setOnLongClickListener { v ->
            onItemLongClickListener?.onItemLongClick(
                null,
                v,
                holder.adapterPosition,
                holder.itemId
            ) == false
        }
    }

    /**
     * to bind data to the view [binding]
     */
    protected abstract fun onBindViewHolder(binding: BINDING, position: Int)
}

abstract class SingleTypeBaseRvAdapter<BINDING : ViewDataBinding, DATA>(
    val mContext: Context, @LayoutRes val layoutId: Int,
    diffCallback: DiffUtil.ItemCallback<DATA>
) : ListAdapter<DATA, BaseHolder<BINDING>>(diffCallback) {
    var onItemClickListener: AdapterView.OnItemClickListener? = null
    var onItemLongClickListener: AdapterView.OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<BINDING> {
        val view =
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BaseHolder(view)
    }

    override fun onBindViewHolder(holder: BaseHolder<BINDING>, position: Int) {
        onBindViewHolder(holder.binding(), position)
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener { v ->
                onItemClickListener?.onItemClick(
                    null,
                    v,
                    holder.adapterPosition,
                    holder.itemId
                )
            }
        }

        holder.itemView.setOnLongClickListener { v ->
            onItemLongClickListener?.onItemLongClick(
                null,
                v,
                holder.adapterPosition,
                holder.itemId
            ) == false
        }
    }

    /**
     * to bind data to the view [binding]
     */
    protected abstract fun onBindViewHolder(binding: BINDING, position: Int)
}

class BaseHolder<out T : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding: T = DataBindingUtil.bind(itemView)!!

    fun binding(): T {
        return binding
    }
}
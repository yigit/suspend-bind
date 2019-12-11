package com.birbit.suspendbind

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlinx.coroutines.CoroutineScope

abstract class SuspendAdapter<T>(
    private val viewScope: CoroutineScope,
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, SuspendViewHolder<T>>(diffCallback) {
    override fun onBindViewHolder(holder: SuspendViewHolder<T>, position: Int) {
        holder.doSuspendBind(viewScope, getItem(position))
    }
}
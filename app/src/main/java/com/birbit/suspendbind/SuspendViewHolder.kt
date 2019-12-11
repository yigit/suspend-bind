package com.birbit.suspendbind

import android.view.View
import androidx.recyclerview.widget.BindAwareViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class SuspendViewHolder<T>(itemView: View) : BindAwareViewHolder(
    itemView
) {
    private var job : Job? = null
    fun doSuspendBind(parentScope:CoroutineScope, item : T) {
        job = parentScope.launch(start = CoroutineStart.UNDISPATCHED) {
            bind(item)
        }
    }
    override fun onBind() {
        super.onBind()
    }

    override fun onUnbind() {
        job?.cancel()
    }

    abstract suspend fun bind(item : T)
}
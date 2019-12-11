package com.birbit.suspendbind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import com.birbit.suspendbind.databinding.ActivityMainBinding
import com.birbit.suspendbind.databinding.ItemViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content = ActivityMainBinding.inflate(LayoutInflater.from(this))
        content.list.adapter = MyAdapter(lifecycleScope).also {
            it.submitList((0..100).map {
                "Item $it"
            })
        }
        setContentView(content.root)

    }
}

class MyAdapter(
    scope: CoroutineScope
) : SuspendAdapter<String>(
    scope,
    object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuspendViewHolder<String> {
        return MyViewHolder(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }
}

class MyViewHolder(
    private val binding: ItemViewBinding
) : SuspendViewHolder<String>(
    binding.root
) {
    override suspend fun bind(item: String) {
        binding.content.text = "loading"
        delay(1_000)
        binding.content.text = "$item"
    }

}
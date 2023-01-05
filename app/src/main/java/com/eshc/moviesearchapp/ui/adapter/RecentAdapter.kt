package com.eshc.moviesearchapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eshc.moviesearchapp.databinding.ItemRecentBinding
import com.eshc.moviesearchapp.ui.model.RecentUiModel

class RecentAdapter(
    private val recentClickListener : (String) -> Unit
) : ListAdapter<RecentUiModel, RecentAdapter.RecentViewHolder>(RecentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        return RecentViewHolder(
            ItemRecentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),recentClickListener
        )
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecentViewHolder(
        val binding: ItemRecentBinding,
        val recentClickListener: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recent: RecentUiModel) {
            binding.recent = recent
            binding.root.setOnClickListener {
                recentClickListener(recent.title)
            }
        }
    }

    private class RecentDiffCallback : DiffUtil.ItemCallback<RecentUiModel>() {
        override fun areItemsTheSame(oldItem: RecentUiModel, newItem: RecentUiModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: RecentUiModel, newItem: RecentUiModel): Boolean {
            return oldItem == newItem
        }
    }
}
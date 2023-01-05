package com.eshc.moviesearchapp.ui.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eshc.moviesearchapp.R
import com.eshc.moviesearchapp.databinding.ItemMovieBinding
import com.eshc.moviesearchapp.ui.model.MovieUiModel

class MovieAdapter(
    private val movieClickListener : (String) -> Unit
) : ListAdapter<MovieUiModel, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),movieClickListener
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder(
        val binding: ItemMovieBinding,
        val movieClickListener: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieUiModel) {
            binding.movie = movie
            binding.root.setOnClickListener {
                movieClickListener(movie.link)
            }
        }
    }

    private class MovieDiffCallback : DiffUtil.ItemCallback<MovieUiModel>() {
        override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
            return oldItem.link == newItem.link
        }

        override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
            return oldItem == newItem
        }
    }
}
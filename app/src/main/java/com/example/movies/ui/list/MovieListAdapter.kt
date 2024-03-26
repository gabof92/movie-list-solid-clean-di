package com.example.movies.ui.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.movies.R
import com.example.movies.databinding.MovieListItemBinding
import com.example.movies.data.server.BASE_URL_IMG
import com.example.movies.ui.common.toHttpsUrl

class MovieListAdapter(
    private val onItemClicked: (com.example.domain.Movie) -> Unit
) : ListAdapter<com.example.domain.Movie, MovieListAdapter.MovieViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val viewHolder = MovieViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        viewHolder.itemView.setOnClickListener{
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }

        return viewHolder

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class MovieViewHolder(private var binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: com.example.domain.Movie) {
            binding.movieName.text = movie.name
            binding.isWatchedIcon.isVisible = movie.isWatched

            val moviePoster = binding.moviePoster
            val noPosterIcon = ContextCompat.getDrawable(moviePoster.context, R.drawable.ic_movie)
            if(movie.image==null) {
                moviePoster.setImageDrawable(noPosterIcon)
                return
            }
            val url = (BASE_URL_IMG + movie.image!!).toHttpsUrl()
            moviePoster.load(url){
                placeholder(R.drawable.ic_downloading)
                error(R.drawable.ic_no_internet)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<com.example.domain.Movie>() {
            override fun areItemsTheSame(oldItem: com.example.domain.Movie, newItem: com.example.domain.Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: com.example.domain.Movie, newItem: com.example.domain.Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

}
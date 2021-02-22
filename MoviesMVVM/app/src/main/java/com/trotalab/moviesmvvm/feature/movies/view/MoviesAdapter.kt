package com.trotalab.moviesmvvm.feature.movies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trotalab.moviesmvvm.R
import com.trotalab.moviesmvvm.data.MovieDetails
import com.vn.fsoft.gstlib.core.extensions.loadFromUrl
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(private val onClickListener: (MovieDetails) -> Unit) :
    ListAdapter<MovieDetails, MoviesViewHolder>(MOVIES_COMPARATOR) {

    companion object {
        private val MOVIES_COMPARATOR = object : DiffUtil.ItemCallback<MovieDetails>() {
            override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }
}

class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: MovieDetails, onClickListener: (MovieDetails) -> Unit) = with(itemView) {
        itemView.setOnClickListener {
                onClickListener.invoke(item)
        }
        moviePoster.loadFromUrl(item.poster)
    }

}
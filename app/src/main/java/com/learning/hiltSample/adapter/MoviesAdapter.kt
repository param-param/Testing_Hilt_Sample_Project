package com.learning.hiltSample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learning.hilt.R
import com.learning.hilt.databinding.MoviesItemLayoutBinding
import com.learning.hiltSample.models.Movie
import com.learning.hiltSample.utils.CommonUtils
import com.learning.hiltSample.utils.Constants


class MoviesAdapter(
    private var context: Context?,
    private var movies: List<Movie>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setMovieList(movies: List<Movie>) {
        val startIndexForUpdateData = this.movies.size
        this.movies = movies
        notifyItemRangeInserted(startIndexForUpdateData, movies.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val moviesItemLayoutBinding: MoviesItemLayoutBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.movies_item_layout, parent, false
            )
        return ViewHolder(moviesItemLayoutBinding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        try{
            val movie = movies[position]

            context?.let {
                Glide.with(it)
                    .load(Constants.IMAGE_BASE_URL + movie.poster_path)
                    .error(R.drawable.no_image)
                    .into((holder as ViewHolder).binding.ivPoster)

                holder.binding.tvTitle.text = movie.title
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: MoviesItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.getRoot()) {
        var binding: MoviesItemLayoutBinding

        init {
            binding = itemView
            binding.ivPoster.layoutParams = CommonUtils.getPosterLayoutParams(context!!)
        }
    }
}
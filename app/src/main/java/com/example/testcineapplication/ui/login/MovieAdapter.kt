package com.example.testcineapplication.ui.login

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testcineapplication.R
import com.example.testcineapplication.core.BaseViewHolder
import com.example.testcineapplication.data.remote.Movie
import com.example.testcineapplication.data.remote.Routes
import com.example.testcineapplication.data.remote.RoutesSize
import com.example.testcineapplication.databinding.ItemMovieRowBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_row.view.*

class MovieAdapter(
    private val routesSize: RoutesSize,
    private val movieList: List<Movie>,
    private val movieClickListener: OnMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {


    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ItemMovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding, parent.context)
        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            movieClickListener.onMovieClick(movieList[position])
        }

        return holder;
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MoviesViewHolder -> holder.bind(movieList[position])
        }
    }

    override fun getItemCount(): Int = movieList.size

    inner class MoviesViewHolder(var binding: ItemMovieRowBinding, var context: Context) :
        BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie) {
            var url = routesSize.large + item.media[0].resource

            Log.d("URL", url)
            Glide.with(context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(binding.imvMovie)
            //binding.imvMovie.setImageResource(R.drawable.uno)
            binding.tvTitleMovie.text = item.name
        }
    }
}
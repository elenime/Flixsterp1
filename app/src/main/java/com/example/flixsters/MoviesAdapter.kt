package com.example.flixsters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter (
    private val data: MutableList<Movie> = mutableListOf()
) : RecyclerView.Adapter<MoviesAdapter.MovieVH>() {

    fun submit(newData: List<Movie>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPoster: ImageView = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle: TextView = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview: TextView = itemView.findViewById<TextView>(R.id.tvOverview)

        fun bind(m: Movie) {
            tvTitle.text = m.title
            tvOverview.text = m.overview
            val url = m.posterUrl()
            if (url != null) {
                Glide.with(itemView).load(url).into(ivPoster)
            } else {
                ivPoster.setImageDrawable(null)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieVH(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MovieVH, position: Int){
        holder.bind(data[position])
    }
}
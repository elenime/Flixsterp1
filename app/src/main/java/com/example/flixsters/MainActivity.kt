package com.example.flixsters // Corrected package name

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var progress: ProgressBar
    private val adapter = MoviesAdapter()

    private val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rv = findViewById(R.id.rvMovies)
        // It's better to define the progress bar in your XML layout
        progress = ProgressBar(this).apply { visibility = View.GONE }

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        fetchNowPlaying()
    }

    private fun fetchNowPlaying() {
        progress.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                val resp = RetrofitInstance.api.getNowPlaying(API_KEY)
                if (resp.isSuccessful) {
                    // Explicitly define the type of the 'movies' variable
                    val movies: List<Movie> = resp.body()?.results.orEmpty()
                    adapter.submit(movies)
                } else {
                    Toast.makeText(this@MainActivity,
                        "Error: ${resp.code()}",
                        Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity,
                    "Network error: ${e.localizedMessage}",
                    Toast.LENGTH_SHORT).show()
            } finally {
                progress.visibility = View.GONE
            }
        }
    }
}

package com.learning.hiltSample.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.learning.hilt.R
import com.learning.hilt.databinding.ActivityMoviesBinding
import com.learning.hiltSample.adapter.MoviesAdapter
import com.learning.hiltSample.models.Movie
import com.learning.hiltSample.repository.ResponseType
import com.learning.hiltSample.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint


import java.util.ArrayList

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val itemsArrayList: List<Movie> = ArrayList<Movie>()
    lateinit var moviesViewModel: MoviesViewModel
    lateinit var adapter: MoviesAdapter
    lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies)

        initViews()

    }

    private fun initViews() {
        initRecycleViews()

        moviesViewModel =
            ViewModelProvider(this).get(MoviesViewModel::class.java)

        binding.progressBar.visibility = View.VISIBLE

        moviesViewModel.movies.observe(this, Observer {
            when (it) {
                is ResponseType.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResponseType.Success -> {
                    binding.progressBar.visibility = View.GONE

                    it.data?.let {
                        adapter.setMovieList(it)
                    }

                }

                is ResponseType.Error -> {
                    binding.progressBar.visibility = View.GONE
                    it.error?.let {
                        Log.e("databaseerror", "-->" + it)
                        Toast.makeText(this@MoviesActivity, "Error -> " + it, Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }
        })

    }

    private fun initRecycleViews() {
        adapter = MoviesAdapter(this, itemsArrayList)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.setAdapter(adapter)
    }


}
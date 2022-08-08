package com.iglo.moviedb.activity.discover_movie


import android.view.View
import androidx.paging.LoadState
import com.iglo.moviedb.activity.discover_movie.DiscoverMovieActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun DiscoverMovieActivity.observeLiveData()= with(vm) {
    data.observe(this@observeLiveData){
        adapter.addLoadStateListener {
            if (it.refresh is LoadState.Error){
                binding.retry.visibility = View.VISIBLE
            } else {
                binding.retry.visibility = View.GONE
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }

    dataGenre.observe(this@observeLiveData){
        vm.getGenreId(it)
    }


}

fun DiscoverMovieActivity.initBinding() = with(vm){
    binding.recycler.adapter = adapter
    binding.retry.setOnClickListener{
        dataGenre.observe(this@initBinding){
                vm.getGenreId(it)
            }
        }
    dataGenre.value = intent.getIntegerArrayListExtra("EXTRA_DATA")
}




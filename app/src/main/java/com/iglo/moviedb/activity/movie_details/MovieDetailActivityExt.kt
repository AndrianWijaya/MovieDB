package com.iglo.moviedb.activity.movie_details

import android.app.ProgressDialog
import android.widget.Toast
import com.iglo.common.ResponseApps
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun MovieDetailActivity.observeLiveData() = with(vm) {


    dataPaging.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }

    movie.observe(this@observeLiveData){
        vm.getMovieDetail(it)
    }

    var dialog : ProgressDialog? = null
    data.observe(this@observeLiveData){
        when(it.condition){
            ResponseApps.ERROR ->{
                dialog?.dismiss()
                Toast.makeText(this@observeLiveData, "Network Error(${it.code.toString()})",
                    Toast.LENGTH_SHORT).show()

            }
            ResponseApps.SUCCESS -> {
                dialog?.dismiss()

            }
            ResponseApps.LOADING ->{
                dialog?.dismiss()
                dialog = ProgressDialog(this@observeLiveData).apply {
                    setCancelable(false)
                    setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    setMessage("Memproses ..")
                    show()
                }


            }
        }

    }

    vm.video.observe(this@observeLiveData){
        val listener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                val videoId = it
                videoId?.let { youTubePlayer.cueVideo(it, 0f) }

                val defaultPlayerUiController =
                    DefaultPlayerUiController(binding.video, youTubePlayer)
                binding.video.setCustomPlayerUi(defaultPlayerUiController.rootView)
            }
        }

        val option = IFramePlayerOptions.Builder().controls(0).build()
        binding.video.initialize(listener, option)
    }

}

fun MovieDetailActivity.initBinding() = with(vm){
    binding.retry.setOnClickListener{
        movie.observe(this@initBinding){
            vm.getMovieDetail(it)
        }
    }



    binding.recycler.adapter = adapter
    movie.value = intent.getIntExtra("EXTRA_DATA",-1)
}

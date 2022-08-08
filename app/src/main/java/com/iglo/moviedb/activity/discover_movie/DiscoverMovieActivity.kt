package com.iglo.moviedb.activity.discover_movie

import android.os.Bundle
import androidx.activity.viewModels
import com.iglo.common.BaseActivity
import com.iglo.moviedb.R
import com.iglo.moviedb.databinding.LayoutDiscoverActivityListBinding
import com.iglo.moviedb.view_model.DiscoverMovieViewModel


class DiscoverMovieActivity : BaseActivity<LayoutDiscoverActivityListBinding, DiscoverMovieViewModel>() {
    override val layoutResourceId: Int = R.layout.layout_discover_activity_list
    override val vm: DiscoverMovieViewModel by viewModels{
        vmFactory
    }
    val adapter = DiscoverMoviePagingAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
        initBinding()
    }
}
package com.iglo.moviedb.activity.movie_details

import android.os.Bundle
import androidx.activity.viewModels
import com.iglo.common.BaseActivity
import com.iglo.moviedb.R
import com.iglo.moviedb.databinding.LayoutDetailActivityItemBinding
import com.iglo.moviedb.view_model.MovieDetailViewModel

class MovieDetailActivity: BaseActivity<LayoutDetailActivityItemBinding, MovieDetailViewModel>() {
    override val layoutResourceId: Int = R.layout.layout_detail_activity_item
    override val vm: MovieDetailViewModel by viewModels {
        vmFactory
    }

    val adapter = MovieReviewPagingAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
        initBinding()
    }

}
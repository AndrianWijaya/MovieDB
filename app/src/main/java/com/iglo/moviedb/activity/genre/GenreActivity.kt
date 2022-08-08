package com.iglo.moviedb.activity.genre

import android.os.Bundle
import androidx.activity.viewModels
import com.iglo.common.BaseActivity
import com.iglo.moviedb.R
import com.iglo.moviedb.databinding.LayoutGenreListBinding
import com.iglo.moviedb.view_model.GenreMovieViewModel

class GenreActivity : BaseActivity<LayoutGenreListBinding, GenreMovieViewModel>() {
    override val layoutResourceId: Int = R.layout.layout_genre_list
    override val vm: GenreMovieViewModel by viewModels { vmFactory }

    val adapter = GenreAdapter(::startActionMode, {
        vm.selection.value.orEmpty()
    }) {
        toggleClick(it)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
        initBinding()
    }

    override fun onDestroy() {
        super.onDestroy()
        actionMode = null
    }

}
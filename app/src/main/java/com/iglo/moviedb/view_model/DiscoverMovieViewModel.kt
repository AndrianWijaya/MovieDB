package com.iglo.moviedb.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.iglo.api_service.use_case.GetDiscoverMovieUseCase
import com.iglo.common.entity.discover_movie.ResultDiscoverMovie
import kotlinx.coroutines.launch


class DiscoverMovieViewModel(
    private val useCase: GetDiscoverMovieUseCase,
    application: Application
) : AndroidViewModel(
    application
) {
    val data = MutableLiveData<PagingData<ResultDiscoverMovie>>()
    val dataGenre = MutableLiveData<ArrayList<Int>>()

    fun getGenreId(id: ArrayList<Int>) {
        viewModelScope.launch {
            useCase.invoke(id).collect {
                data.postValue(it)
            }
        }
    }

}
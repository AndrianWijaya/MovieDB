package com.iglo.moviedb.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iglo.api_service.use_case.GetGenreUseCase
import com.iglo.common.ResponseApps
import com.iglo.common.entity.genre.Genre
import kotlinx.coroutines.launch

class GenreMovieViewModel(
    private val genreUseCase: GetGenreUseCase,
    application: Application
): AndroidViewModel(application) {

    val genreData = MutableLiveData<ResponseApps<List<Genre>>>()
    val selection = MutableLiveData<List<Genre>>()


    init {
        getGenre()
    }

    fun getGenre(){
        viewModelScope.launch {
            genreUseCase().collect{
                genreData.postValue(it)
            }
        }
    }
}